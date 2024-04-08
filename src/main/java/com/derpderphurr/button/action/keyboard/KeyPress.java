package com.derpderphurr.button.action.keyboard;

import java.util.ArrayList;

public class KeyPress extends KeySequenceElement {
    public ArrayList<Character> elements = new ArrayList<>();

    @Override
    byte[] toBytes() {
        return new byte[0];
    }
    //Magic Number 0xA1


    @Override
    public String toString() {
        return "KeyPress{" +
                "elements=" + elements +
                '}';
    }
}
