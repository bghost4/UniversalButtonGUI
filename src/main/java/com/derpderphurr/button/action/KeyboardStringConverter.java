package com.derpderphurr.button.action;

public class KeyboardStringConverter {
    public record HIDcode(int c, boolean shiftRequired, int hid_code) { }
    public record SpecialKey(String name,boolean shift,int hidCode) { }

    /* For More clarification see hid.h from TinyUSB Project */
    //I Suspect these are all associated with US Keyboard Layout


    //Keys Not printable
    public static final SpecialKey[] specialKeys  = {
        new SpecialKey("Backspace",false,0x2a), //Backspace
        new SpecialKey("TAB",false,0x2B), //TAB
        new SpecialKey("LF",false,0x28), //Line Feed
        new SpecialKey("CR",false,0x28), //Carrige Return
        new SpecialKey("ESC",false,0x29), //Escape
        new SpecialKey("CapsLock",false,0x39),
        new SpecialKey("F1",false,0x3A),
        new SpecialKey("F2",false,0x3B),
        new SpecialKey("F3",false,0x3C),
        new SpecialKey("F4",false,0x3D),
        new SpecialKey("F5",false,0x3E),
        new SpecialKey("F6",false,0x3F),
        new SpecialKey("F7",false,0x40),
        new SpecialKey("F8",false,0x41),
        new SpecialKey("F9",false,0x42),
        new SpecialKey("F10",false,0x43),
        new SpecialKey("F11",false,0x44),
        new SpecialKey("F12",false,0x45),
        new SpecialKey("PrintScreen",false,0x46),
        new SpecialKey("SysReq",false,0x9A),
        new SpecialKey("ScrLK",false,0x47),
        new SpecialKey("Pause",false,0x48),
        //new SpecialKey("Break",true,0x48), Not sure if this is actually Implemented In HID Keycodes, as SysReq is it's own key
        new SpecialKey("Ins",false,0x49),
        new SpecialKey("Home",false,0x4A),
        new SpecialKey("PgUp",false,0x4B),
        new SpecialKey("Delete",false,0x4C),
        new SpecialKey("End",false,0x4D),
        new SpecialKey("PgDn",false,0x4E),
        new SpecialKey("AR",false,0x4F), //Arrow Right
        new SpecialKey("AL",false,0x50), //Arrow Left
        new SpecialKey("AD",false,0x51), //Arrow Down
        new SpecialKey("AU",false,0x52) //Arrow Up
    };

    public static final HIDcode[] HIDCodeBlock = {
            //Punctuation & Num Keys
            new HIDcode(' ',false,0x2C), //Space
            new HIDcode('`',false,0x35),
            new HIDcode('~',true,0x35),
            new HIDcode('!',true,0x1E),
            new HIDcode('@',true,0x1F),
            new HIDcode('#',true,0x20),
            new HIDcode('$',true,0x21),
            new HIDcode('%',true,0x22),
            new HIDcode('^',true,0x23),
            new HIDcode('&',true,0x24),
            new HIDcode('*',true,0x25),
            new HIDcode('(',true,0x26),
            new HIDcode(')',true,0x27),
            new HIDcode('-',false,0x2D),
            new HIDcode('_',true,0x2D),

            new HIDcode('=',false,0x2E),
            new HIDcode('+',true,0x2E),

            new HIDcode('[',false,0x2F),
            new HIDcode('{',true,0x2F),

            new HIDcode(']',false,0x30),
            new HIDcode('}',true,0x30),

            new HIDcode('\\',false,0x31),
            new HIDcode('|',true,0x31),

            new HIDcode(':',true,0x33),
            new HIDcode(';',false,0x33),

            new HIDcode('\'',false,0x34),
            new HIDcode('\"',true,0x34),

            new HIDcode(',',false,0x36),
            new HIDcode('<',true,0x36),

            new HIDcode('.',false,0x37),
            new HIDcode('>',true,0x37),

            new HIDcode('/',false,0x38),
            new HIDcode('?',true,0x38),

            new HIDcode('0',false,0x27),
            new HIDcode('1',false,0x1E),
            new HIDcode('2',false,0x1F),
            new HIDcode('3',false,0x20),
            new HIDcode('4',false,0x21),
            new HIDcode('5',false,0x22),
            new HIDcode('6',false,0x23),
            new HIDcode('7',false,0x24),
            new HIDcode('8',false,0x25),
            new HIDcode('9',false,0x26),

            new HIDcode('a',false,0x04),
            new HIDcode('b',false,0x05),
            new HIDcode('c',false,0x06),
            new HIDcode('d',false,0x07),
            new HIDcode('e',false,0x08),
            new HIDcode('f',false,0x09),
            new HIDcode('g',false,0x0a),
            new HIDcode('h',false,0x0b),
            new HIDcode('i',false,0x0c),
            new HIDcode('j',false,0x0d),
            new HIDcode('k',false,0x0e),
            new HIDcode('l',false,0x0f),
            new HIDcode('m',false,0x10),
            new HIDcode('n',false,0x11),
            new HIDcode('o',false,0x12),
            new HIDcode('p',false,0x13),
            new HIDcode('q',false,0x14),
            new HIDcode('r',false,0x15),
            new HIDcode('s',false,0x16),
            new HIDcode('t',false,0x17),
            new HIDcode('u',false,0x18),
            new HIDcode('v',false,0x19),
            new HIDcode('w',false,0x1a),
            new HIDcode('x',false,0x1b),
            new HIDcode('y',false,0x1c),
            new HIDcode('z',false,0x1d),

            new HIDcode('A',true,0x04),
            new HIDcode('B',true,0x05),
            new HIDcode('C',true,0x06),
            new HIDcode('D',true,0x07),
            new HIDcode('E',true,0x08),
            new HIDcode('F',true,0x09),
            new HIDcode('G',true,0x0a),
            new HIDcode('H',true,0x0b),
            new HIDcode('I',true,0x0c),
            new HIDcode('J',true,0x0d),
            new HIDcode('K',true,0x0e),
            new HIDcode('L',true,0x0f),
            new HIDcode('M',true,0x10),
            new HIDcode('N',true,0x11),
            new HIDcode('O',true,0x12),
            new HIDcode('P',true,0x13),
            new HIDcode('Q',true,0x14),
            new HIDcode('R',true,0x15),
            new HIDcode('S',true,0x16),
            new HIDcode('T',true,0x17),
            new HIDcode('U',true,0x18),
            new HIDcode('V',true,0x19),
            new HIDcode('W',true,0x1a),
            new HIDcode('X',true,0x1b),
            new HIDcode('Y',true,0x1c),
            new HIDcode('Z',true,0x1d),
    };
}
