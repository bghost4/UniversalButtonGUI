package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;

import java.util.stream.Stream;

public class Callback extends Action {
    @Override
    public Stream<Byte> toBytes() {
        //TODO IMPLEMENT ME
        return Stream.empty();
    }

    @Override
    public ActionEditor<?> createEditor() {
        return null;
    }
    //There is no Data With this Action, use is based off from what "slot" it is put in
}
