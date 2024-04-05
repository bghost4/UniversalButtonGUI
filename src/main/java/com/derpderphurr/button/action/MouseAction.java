package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;

public class MouseAction extends Action {
    boolean left_button,right,button,middle_button,forward_button,back_button;
    int x,y,sx,sy; //Delta X,Y , Delta Scroll X,Y

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public ActionEditor<?> createEditor() {
        return null;
    }
}
