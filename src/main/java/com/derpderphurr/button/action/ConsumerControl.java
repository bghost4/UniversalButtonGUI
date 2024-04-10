package com.derpderphurr.button.action;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsumerControl extends Action {
    //Magic Number: 0xA6
    private final int value;
    private final String description;

    @Override
    public String toString() {
        return "ConsumerControl{"+description+"}";
    }

    private ConsumerControl(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Stream<Byte> toBytes() {
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.put((byte)0xA6);
        bb.putShort((short)value);
        return IntStream.range(0,bb.array().length).mapToObj(i -> bb.array()[i]);
    }

    public static final ConsumerControl POWER = new ConsumerControl(0x0030,"POWER");
    public static final ConsumerControl RESET = new ConsumerControl(0x0031,"RESET");
    public static final ConsumerControl SLEEP = new ConsumerControl(0x0032,"SLEEP");
    public static final ConsumerControl BRIGHTNESS_INCREMENT = new ConsumerControl(0x006F,"Brightness Increment");
    public static final ConsumerControl BRIGHTNESS_DECREMENT = new ConsumerControl(0x0070,"Brightness Decrement");
    public static final ConsumerControl PLAY_PAUSE = new ConsumerControl(0x00CD,"Play/Pause");
    public static final ConsumerControl SCAN_NEXT = new ConsumerControl(0x00B5,"Scan Next");
    public static final ConsumerControl SCAN_PREVIOUS = new ConsumerControl(0x00B6,"Scan Previous");
    public static final ConsumerControl STOP = new ConsumerControl(0x00B7,"Stop");
    public static final ConsumerControl VOLUME = new ConsumerControl(0x00E0,"Volume");
    public static final ConsumerControl MUTE = new ConsumerControl(0x00E2,"Mute");
    public static final ConsumerControl BASS = new ConsumerControl(0x00E3,"BASS");
    public static final ConsumerControl TREBBLE = new ConsumerControl(0x00E4,"Treble");
    public static final ConsumerControl VOLUME_INCREMENT = new ConsumerControl(0x00E9,"Volume Increment");
    public static final ConsumerControl VOLUME_DECREMENT = new ConsumerControl(0x00EA,"Volume Decrement");

    public static final List<ConsumerControl> values = new ArrayList<>() {{
        add(POWER);add(RESET);add(SLEEP);add(BRIGHTNESS_INCREMENT);add(BRIGHTNESS_DECREMENT);
        add(PLAY_PAUSE);add(SCAN_NEXT);add(SCAN_PREVIOUS);add(STOP);add(VOLUME);add(MUTE);add(BASS);
        add(TREBBLE);add(VOLUME_INCREMENT);add(VOLUME_DECREMENT);
    }};
}
