package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.Action;
import javafx.scene.layout.Pane;

public abstract class ActionEditor<T extends Action> extends Pane {

    public abstract void setValue(T value);
    public abstract T getValue();
}
