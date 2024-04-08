package com.derpderphurr.button.action;

import com.derpderphurr.button.action.keyboard.KeyPress;
import com.derpderphurr.button.action.keyboard.KeySequenceElement;
import com.derpderphurr.button.action.keyboard.Modifier;
import com.derpderphurr.button.ui.ActionEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class KeyboardSequence extends Action {
    private List<KeySequenceElement> elements;

    public List<KeySequenceElement> getElements() {
        return elements;
    }

    public void setElements(List<KeySequenceElement> elements) {
        //Make a copy of the elements
        this.elements = new ArrayList<>(elements);
    }

    @Override
    public Stream<Byte> toBytes() {
        return elements.stream().flatMap(e -> e.toBytes());
    }

    @Override
    public ActionEditor<?> createEditor() {
        return null;
    }
}
