package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;

public class Callback extends Action {
    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public ActionEditor<?> createEditor() {
        return null;
    }
    //There is no Data With this Action, use is based off from what "slot" it is put in
}
