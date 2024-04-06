package com.derpderphurr.button.action;

import com.derpderphurr.button.action.keyboard.KeySequenceElement;
import com.derpderphurr.button.ui.ActionEditor;

import java.util.List;

public class KeyboardSequence extends Action {
    private List<KeySequenceElement> elements;

    public List<KeySequenceElement> getElements() {
        return elements;
    }

    public void setElements(List<KeySequenceElement> elements) {
        this.elements = elements;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public ActionEditor<?> createEditor() {
        return null;
    }
}
