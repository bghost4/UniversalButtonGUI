package com.derpderphurr.button.action;

import com.derpderphurr.button.action.keyboard.KeySequenceElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class KeyboardSequence extends Action {
    private List<KeySequenceElement> elements;

    public List<KeySequenceElement> getElements() {
        return elements;
    }

    public void setElements(List<KeySequenceElement> elements) {
        this.elements = new ArrayList<>(elements);
    }

    @Override
    public Stream<Byte> toBytes() {
        return elements.stream().flatMap(KeySequenceElement::toBytes);
    }
}
