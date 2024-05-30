package com.derpderphurr.button.action;

import java.util.stream.Stream;

public class KeyboardToggle extends Action {
    private KeyboardStringConverter.HIDcode code;

    public KeyboardStringConverter.HIDcode getCode() {
        return code;
    }

    public void setCode(KeyboardStringConverter.HIDcode code) {
        this.code = code;
    }

    @Override
    public Stream<Byte> toBytes() {
        return Stream.of((byte)0xAA,(byte)code.hid_code());
    }

    @Override
    public String toString() {
        return "KeyboardToggle{" +
                "code=" + code +
                '}';
    }
}
