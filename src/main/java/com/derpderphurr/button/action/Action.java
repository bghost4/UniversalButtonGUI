package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;

public abstract class Action {
    public abstract byte[] toBytes();

    public abstract ActionEditor<?> createEditor();

}
