package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;

import java.util.stream.Stream;

public class MouseAction extends Action {
    boolean left_button,right,button,middle_button,forward_button,back_button;
    int x,y,sx,sy; //Delta X,Y , Delta Scroll X,Y

    @Override
    public Stream<Byte> toBytes() {
        //TODO Implement Me
        return Stream.empty();
    }

    @Override
    public ActionEditor<?> createEditor() {
        return null;
    }
}
