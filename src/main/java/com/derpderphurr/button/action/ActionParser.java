package com.derpderphurr.button.action;

import com.derpderphurr.button.action.keyboard.KeyPress;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActionParser {

    record ActionLength(Action a,int length) {}

    static List<Action> parse(byte[] data){
        int position = 0;
        ArrayList<Action> actions = new ArrayList<>();

        while(position < data.length) {
            ActionLength a = parseKeyboard(data,position).orElse(parseConsumer(data,position).orElse(parseMouse(data,position).orElse(null)));
            if(a == null) {
                break;
            }
            actions.add(a.a);
            position += a.length;
        }
        return actions;
    }


    static Optional<ActionLength> parseKeyboard(byte[] data,int offset) {

        if(data[0] == (byte)0xA1) { //Key Press
            byte size = data[1];
            ArrayList<KeyboardStringConverter.HIDcode> sequence = new ArrayList<>();
            ArrayList<KeyboardStringConverter.HIDcode> codelist = new ArrayList<>();
            codelist.addAll(List.of(KeyboardStringConverter.HIDCodeBlock));
            codelist.addAll(List.of(KeyboardStringConverter.specialKeys));
            for(int i=0; i < size; i++) {
                int off = offset+2+i;
                codelist.stream().filter(c -> c.hid_code() == data[off]).findFirst().ifPresent(sequence::add);
            }
            KeyPress kp = new KeyPress();
            kp.elements.addAll(codelist);
            //return Optional.of(new ActionLength(kp,2+size));
        } else if(data[0] == (byte)0xA2) { //Set Modifiers

        } else if(data[0] == (byte)0xA3) { //Clear Modifiers

        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    static Optional<ActionLength> parseMouse(byte[] data,int offset) {
        if(data[offset] != (byte)0xA0) {
            return Optional.empty();
        }
        MouseAction ma = new MouseAction();
        int length = 6;
        int buttons = data[offset+1];
        int x = data[offset+2];
        int y = data[offset+3];
        int sx = data[offset+4];
        int sy = data[offset+5];

        boolean left = (buttons & 0x01) > 0;
        boolean right = (buttons & 0x02) > 0;
        boolean middle = (buttons & 0x04) > 0;
        boolean back = (buttons & 0x08) > 0;
        boolean forward = (buttons & 0x10) > 0;

        ma.middle_button = middle;
        ma.left_button = left;
        ma.right_button = right;
        ma.back_button = back;
        ma.forward_button = forward;

        ma.x = x;
        ma.y = y;
        ma.sx = sx;
        ma.sy = sy;

        return Optional.of(new ActionLength(ma,length));
    }

    static Optional<ActionLength> parseConsumer(byte[] data, int offset) {
        //verify:
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.put(data[offset]);
        bb.put(data[offset+1]);
        bb.put(data[offset+2]);
        byte magic = bb.rewind().get();
        short value = bb.getShort();

        Optional<ConsumerControl> occ = ConsumerControl.values.stream().filter(cc -> cc.getValue() == value).findFirst();
        Optional<ActionLength> al = occ.map(cc -> Optional.of(new ActionLength(cc,3))).orElse(Optional.empty());
        return al;
    }
}
