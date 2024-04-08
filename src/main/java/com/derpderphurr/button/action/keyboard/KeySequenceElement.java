package com.derpderphurr.button.action.keyboard;

import java.util.stream.Stream;

public abstract class KeySequenceElement {
    public abstract Stream<Byte> toBytes();
}
