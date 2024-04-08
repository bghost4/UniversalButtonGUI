package com.derpderphurr.button.action;

import com.derpderphurr.button.ui.ActionEditor;
import com.derpderphurr.button.ui.ConsumerEditor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsumerControl extends Action {
    public ConsumerControlAction getAction() {
        return action;
    }

    //Magic Number: 0xA6
    private final ConsumerControlAction action;

    public ConsumerControl(ConsumerControlAction a) {
        this.action = a;
    }
    @Override
    public Stream<Byte> toBytes() {
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put((byte)0xA6);
        bb.putShort((short)action.v);
        return IntStream.range(0,bb.array().length).mapToObj(i -> Byte.valueOf(bb.array()[i]));
    }

    @Override
    public ActionEditor<?> createEditor() {
        ConsumerEditor ce = new ConsumerEditor();
        ce.setValue(this);
        return ce;
    }

    public enum ConsumerControlAction {
        POWER(0x0030),RESET(0x0031),SLEEP(0x0032),BRIGHTNESS_INCREMENT(0x006F),BRIGHTNESS_DECREMENT(0x0070),PLAY_PAUSE(0x00CD),SCAN_NEXT(0x00B5),SCAN_PREVIOUS(0x00B6),STOP(0x00B7),VOLUME(0x00E0),MUTE(0x00E2),BASS(0x00E3),TREBLE(0x00E4),VOLUME_INCREMENT(0x00E9),VOLUME_DECREMENT(0x00EA);

        private final int v;
        private ConsumerControlAction(int value) {
            this.v = value;
        }
        public int getValue() { return v;}
    }
}
