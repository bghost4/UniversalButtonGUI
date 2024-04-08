package com.derpderphurr.button.action.keyboard;

import com.derpderphurr.button.action.KeyboardStringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyPress extends KeySequenceElement {
    public ArrayList<KeyboardStringConverter.HIDcode> elements = new ArrayList<>();

    @Override
    public Stream<Byte> toBytes() {

        List<Byte> e = new ArrayList<>();
        boolean shiftActive = false;
        List<Byte> fragment = new ArrayList<>();
        for(int i=0; i < elements.size(); i++) {
            boolean shiftRequired = elements.get(i).shiftRequired();
            if( shiftRequired && !shiftActive) {
                if(fragment.size() > 0) {
                    e.add((byte)0xA1);
                    e.add((byte)fragment.size());
                    e.addAll(fragment);
                    fragment = new ArrayList<>();
                }
                e.addAll(Modifier.setShift().toBytes().collect(Collectors.toList()));
                shiftActive = true;
            } else if( shiftActive && !shiftRequired) {
                if(fragment.size() > 0) {
                    e.add((byte)0xA1);
                    e.add((byte)fragment.size());
                    e.addAll(fragment);
                    fragment = new ArrayList<>();
                }
                e.addAll(Modifier.clrShift().toBytes().collect(Collectors.toList()));
                shiftActive = false;
            }
            fragment.add((byte)elements.get(i).hid_code());
        }
        e.addAll(fragment);

        return e.stream();
    }
    //Magic Number 0xA1


    @Override
    public String toString() {
        return "KeyPress{" +
                "elements=" + elements +
                '}';
    }
}
