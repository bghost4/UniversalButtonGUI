package com.derpderphurr.button.action.keyboard;

import com.derpderphurr.button.action.KeyboardStringConverter;

import java.util.stream.Stream;

public abstract class KeySequenceElement {
    public abstract Stream<Byte> toBytes();
}
