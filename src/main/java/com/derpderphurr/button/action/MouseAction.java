package com.derpderphurr.button.action;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MouseAction extends Action {
    public boolean left_button,right_button,middle_button,forward_button,back_button;
    public int x,y,sx,sy; //Delta X,Y , Delta Scroll X,Y

    @Override
    public Stream<Byte> toBytes() {
        ByteBuffer bb = ByteBuffer.allocate(6);
        bb.put((byte)0xA0); //Mouse Magic

        int button_value = (left_button ? 0x01 : 0x00) | ( right_button ? 0x02 : 0x00) | ( middle_button ? 0x04 : 0x00) | ( back_button ? 0x08 : 0x00) | (forward_button ? 0x10 : 0x00);
        bb.put((byte)button_value);

        bb.put((byte)x);
        bb.put((byte)y);
        bb.put((byte)sy);
        bb.put((byte)sx);

        return IntStream.range(0,bb.array().length).mapToObj(i -> bb.array()[i]);
    }

}
