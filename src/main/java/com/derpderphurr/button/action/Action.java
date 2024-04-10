package com.derpderphurr.button.action;

import java.util.stream.Stream;

public abstract class Action {
    public abstract Stream<Byte> toBytes();
}
