package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;

import java.util.stream.Stream;

public abstract class Action {
    public abstract Stream<Byte> toBytes();

    public abstract ActionEditor<?> createEditor();

}
