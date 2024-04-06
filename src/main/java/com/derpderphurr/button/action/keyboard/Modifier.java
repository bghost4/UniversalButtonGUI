package com.derpderphurr.button.action.keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Modifier extends KeySequenceElement {
    //Magic Number 0xA2(press)0xA3(release)
    private boolean LEFT_CTRL,LEFT_SHIFT,LEFT_ALT,LEFT_GUI,RIGHT_CTRL,RIGHT_SHIFT,RIGHT_ALT,RIGHT_GUI;
    private boolean set = false;

    public Modifier(boolean LEFT_CTRL, boolean LEFT_SHIFT, boolean LEFT_ALT, boolean LEFT_GUI, boolean RIGHT_CTRL, boolean RIGHT_SHIFT, boolean RIGHT_ALT, boolean RIGHT_GUI, boolean set) {
        this.LEFT_CTRL = LEFT_CTRL;
        this.LEFT_SHIFT = LEFT_SHIFT;
        this.LEFT_ALT = LEFT_ALT;
        this.LEFT_GUI = LEFT_GUI;
        this.RIGHT_CTRL = RIGHT_CTRL;
        this.RIGHT_SHIFT = RIGHT_SHIFT;
        this.RIGHT_ALT = RIGHT_ALT;
        this.RIGHT_GUI = RIGHT_GUI;
        this.set = set;
    }

    @Override
    byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public String toString() {
        String mode = set ? "SET" : "CLR";
        List<String> items = new ArrayList<>();
        if(LEFT_CTRL) { items.add("LCtrl"); }
        if(LEFT_ALT) { items.add("LAlt"); }
        if(LEFT_GUI) { items.add("LGUI"); }
        if(LEFT_SHIFT) { items.add("LShift"); }
        if(RIGHT_ALT) { items.add("RAlt"); }
        if(RIGHT_GUI) { items.add("RGUI"); }
        if(RIGHT_CTRL) { items.add("RCtrl"); }
        if(RIGHT_SHIFT) { items.add("RShift"); }
        return String.format("%s - %s",mode,items.stream().collect(Collectors.joining(",")));
    }
}
