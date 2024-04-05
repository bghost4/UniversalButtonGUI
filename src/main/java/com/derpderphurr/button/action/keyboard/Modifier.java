package com.derpderphurr.button.action.keyboard;

public class Modifier extends KeySequenceElement {
    //Magic Number 0xA2(press)0xA3(release)
    private boolean LEFT_CTRL,LEFT_SHIFT,LEFT_ALT,LEFT_GUI,RIGHT_CTRL,RIGHT_SHIFT,RIGHT_ALT,RIGHT_GUI;

    @Override
    byte[] toBytes() {
        return new byte[0];
    }
}
