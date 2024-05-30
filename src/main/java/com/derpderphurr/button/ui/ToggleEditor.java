package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.KeyboardStringConverter;
import com.derpderphurr.button.action.KeyboardToggle;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import java.util.Optional;

public class ToggleEditor extends Dialog<KeyboardToggle> {
    private final TextField txt = new TextField();

    public ToggleEditor(){
        getDialogPane().setContent(txt);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        setResultConverter(this::converter);
    }
    private KeyboardToggle converter(ButtonType t) {
        if(t == ButtonType.OK) {
            Optional<KeyboardStringConverter.HIDcode> code = KeyboardStringConverter.lookupByChar(txt.getText().charAt(0));
            if(code.isPresent()) {
                KeyboardToggle toggle = new KeyboardToggle();
                toggle.setCode(code.get());
                return toggle;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
