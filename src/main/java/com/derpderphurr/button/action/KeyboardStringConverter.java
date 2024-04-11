package com.derpderphurr.button.action;

import com.derpderphurr.button.action.keyboard.KeyPress;
import com.derpderphurr.button.action.keyboard.KeySequenceElement;
import com.derpderphurr.button.action.keyboard.Modifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyboardStringConverter {
    public record HIDcode(int c, String descr ,boolean shiftRequired, int hid_code) {
        @Override
        public String toString() {
            return descr();
        }
    }

    /* For More clarification see hid.h from TinyUSB Project */
    //I Suspect these are all associated with US Keyboard Layout


    //Keys Not printable or not "seen" in a TextArea/TextField
    public static final HIDcode[] specialKeys  = {
        new HIDcode(0,"\u2190BS",false,0x2a), //Backspace
        new HIDcode(0,"TAB",false,0x2B), //TAB
        new HIDcode(0,"\u21b5",false,0x28), //Enter
        new HIDcode(0,"ESC",false,0x29), //Escape
        new HIDcode(0,"CapsLock",false,0x39),
        new HIDcode(0,"F1",false,0x3A),
        new HIDcode(0,"F2",false,0x3B),
        new HIDcode(0,"F3",false,0x3C),
        new HIDcode(0,"F4",false,0x3D),
        new HIDcode(0,"F5",false,0x3E),
        new HIDcode(0,"F6",false,0x3F),
        new HIDcode(0,"F7",false,0x40),
        new HIDcode(0,"F8",false,0x41),
        new HIDcode(0,"F9",false,0x42),
        new HIDcode(0,"F10",false,0x43),
        new HIDcode(0,"F11",false,0x44),
        new HIDcode(0,"F12",false,0x45),
            new HIDcode(0,"F13",false,0x68),
            new HIDcode(0,"F14",false,0x69),
            new HIDcode(0,"F15",false,0x6A),
            new HIDcode(0,"F16",false,0x6B),
            new HIDcode(0,"F17",false,0x6C),
            new HIDcode(0,"F18",false,0x6D),
            new HIDcode(0,"F19",false,0x6E),
            new HIDcode(0,"F20",false,0x6F),
            new HIDcode(0,"F21",false,0x70),
            new HIDcode(0,"F22",false,0x71),
            new HIDcode(0,"F23",false,0x72),
            new HIDcode(0,"F24",false,0x73),

        new HIDcode(0,"PrintScreen",false,0x46),
        new HIDcode(0,"SysReq",false,0x9A),
        new HIDcode(0,"ScrLK",false,0x47),
        new HIDcode(0,"Pause",false,0x48),
        new HIDcode(0,"Ins",false,0x49),
        new HIDcode(0,"Home",false,0x4A),
        new HIDcode(0,"PgUp",false,0x4B),
        new HIDcode(0,"Delete",false,0x4C),
        new HIDcode(0,"End",false,0x4D),
        new HIDcode(0,"PgDn",false,0x4E),
        new HIDcode(0,"\u2192",false,0x4F), //Arrow Right
        new HIDcode(0,"\u2190",false,0x50), //Arrow Left
        new HIDcode(0,"\u2193",false,0x51), //Arrow Down
        new HIDcode(0,"\u2191",false,0x52) //Arrow Up
    };

// Used for all String -> HIDcode conversions
    public static final HIDcode[] HIDCodeBlock = {
            //Punctuation & Num Keys
            new HIDcode('\t',"\t",false,0x2B), //TAB
            new HIDcode(' '," ",false,0x2C), //Space
            new HIDcode('`',"`",false,0x35),
            new HIDcode('~',"~",true,0x35),
            new HIDcode('!',"!",true,0x1E),
            new HIDcode('@',"@",true,0x1F),
            new HIDcode('#',"#",true,0x20),
            new HIDcode('$',"$",true,0x21),
            new HIDcode('%',"%",true,0x22),
            new HIDcode('^',"^",true,0x23),
            new HIDcode('&',"&",true,0x24),
            new HIDcode('*',"*",true,0x25),
            new HIDcode('(',"(",true,0x26),
            new HIDcode(')',")",true,0x27),
            new HIDcode('-',"-",false,0x2D),
            new HIDcode('_',"_",true,0x2D),

            new HIDcode('=',"=",false,0x2E),
            new HIDcode('+',"+",true,0x2E),

            new HIDcode('[',"[",false,0x2F),
            new HIDcode('{',"{",true,0x2F),

            new HIDcode(']',"{",false,0x30),
            new HIDcode('}',"}",true,0x30),

            new HIDcode('\\',"\\",false,0x31),
            new HIDcode('|',"|",true,0x31),

            new HIDcode(':',":",true,0x33),
            new HIDcode(';',";",false,0x33),

            new HIDcode('\'',"'",false,0x34),
            new HIDcode('\"',"\"",true,0x34),

            new HIDcode(',',",",false,0x36),
            new HIDcode('<',"<",true,0x36),

            new HIDcode('.',".",false,0x37),
            new HIDcode('>',">",true,0x37),

            new HIDcode('/',"/",false,0x38),
            new HIDcode('?',"?",true,0x38),

            new HIDcode('0',"0",false,0x27),
            new HIDcode('1',"1",false,0x1E),
            new HIDcode('2',"2",false,0x1F),
            new HIDcode('3',"3",false,0x20),
            new HIDcode('4',"4",false,0x21),
            new HIDcode('5',"5",false,0x22),
            new HIDcode('6',"6",false,0x23),
            new HIDcode('7',"7",false,0x24),
            new HIDcode('8',"8",false,0x25),
            new HIDcode('9',"9",false,0x26),

            new HIDcode('a',"a",false,0x04),
            new HIDcode('b',"b",false,0x05),
            new HIDcode('c',"c",false,0x06),
            new HIDcode('d',"d",false,0x07),
            new HIDcode('e',"e",false,0x08),
            new HIDcode('f',"f",false,0x09),
            new HIDcode('g',"g",false,0x0a),
            new HIDcode('h',"h",false,0x0b),
            new HIDcode('i',"i",false,0x0c),
            new HIDcode('j',"j",false,0x0d),
            new HIDcode('k',"k",false,0x0e),
            new HIDcode('l',"l",false,0x0f),
            new HIDcode('m',"m",false,0x10),
            new HIDcode('n',"n",false,0x11),
            new HIDcode('o',"o",false,0x12),
            new HIDcode('p',"p",false,0x13),
            new HIDcode('q',"q",false,0x14),
            new HIDcode('r',"r",false,0x15),
            new HIDcode('s',"s",false,0x16),
            new HIDcode('t',"t",false,0x17),
            new HIDcode('u',"u",false,0x18),
            new HIDcode('v',"v",false,0x19),
            new HIDcode('w',"w",false,0x1a),
            new HIDcode('x',"x",false,0x1b),
            new HIDcode('y',"y",false,0x1c),
            new HIDcode('z',"z",false,0x1d),

            new HIDcode('A',"A",true,0x04),
            new HIDcode('B',"B",true,0x05),
            new HIDcode('C',"C",true,0x06),
            new HIDcode('D',"D",true,0x07),
            new HIDcode('E',"E",true,0x08),
            new HIDcode('F',"F",true,0x09),
            new HIDcode('G',"G",true,0x0a),
            new HIDcode('H',"H",true,0x0b),
            new HIDcode('I',"I",true,0x0c),
            new HIDcode('J',"J",true,0x0d),
            new HIDcode('K',"K",true,0x0e),
            new HIDcode('L',"L",true,0x0f),
            new HIDcode('M',"M",true,0x10),
            new HIDcode('N',"N",true,0x11),
            new HIDcode('O',"O",true,0x12),
            new HIDcode('P',"P",true,0x13),
            new HIDcode('Q',"Q",true,0x14),
            new HIDcode('R',"R",true,0x15),
            new HIDcode('S',"S",true,0x16),
            new HIDcode('T',"T",true,0x17),
            new HIDcode('U',"U",true,0x18),
            new HIDcode('V',"V",true,0x19),
            new HIDcode('W',"W",true,0x1a),
            new HIDcode('X',"X",true,0x1b),
            new HIDcode('Y',"Y",true,0x1c),
            new HIDcode('Z',"Z",true,0x1d),
    };

    public static KeySequenceElement setShift() {
        return new Modifier(false,true,false,false,false,false,false,false,true);
    }
    public static KeySequenceElement clrShift() {
        return new Modifier(false,false,false,false,false,false,false,false,false);
    }


    public static List<KeySequenceElement> fromString(String s) {
        List<HIDcode> e = s.chars().mapToObj(i -> lookupByChar((char) i)).flatMap(Optional::stream).toList();
        KeyPress kp = new KeyPress();
        kp.elements.addAll(e);
        return Stream.of(kp).collect(Collectors.toList());
    }

    private static Optional<HIDcode> lookupByChar(Character character) {
        return Arrays.stream(HIDCodeBlock).filter(c -> c.c== character).findFirst();
    }

    public static String fromKeySequence(List<KeySequenceElement> items) {
        //TODO implement me
        return "Not Yet Implemented";
    }

}
