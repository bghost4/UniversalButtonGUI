package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.KeyboardSequence;
import com.derpderphurr.button.action.KeyboardStringConverter;
import com.derpderphurr.button.action.keyboard.KeyPress;
import com.derpderphurr.button.action.keyboard.KeySequenceElement;
import com.derpderphurr.button.action.keyboard.Modifier;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.util.Collections;
import java.util.function.Consumer;


public class KeyboardSubEditor extends Dialog<KeyboardSequence> {
    private final ListView<KeySequenceElement> lstElements = new ListView<>();
    private final TextArea txtEntry = new TextArea();

    //Toggle Buttons for Modifiers
    private final ToggleButton
            tglLCtrl = new ToggleButton("LCtrl"),
            tglLShift = new ToggleButton("LShift"),
            tglLAlt = new ToggleButton("LAlt"),
            tglLGUI = new ToggleButton("LGUI"),
            tglRAlt = new ToggleButton("Ralt"),
            tglRCtrl = new ToggleButton("RCtrl"),
            tglRShift = new ToggleButton("RShift"),
            tglRGUI = new ToggleButton("RGUI");

    public KeyboardSubEditor() {

        VBox vb = new VBox();
        GridPane gp = new GridPane();

        Button btnAddText = new Button("Add");
        btnAddText.setOnAction(a -> lstElements.getItems().addAll(KeyboardStringConverter.fromString(txtEntry.getText())) );
        HBox hbTextEntry = new HBox();
        hbTextEntry.getChildren().addAll(txtEntry,btnAddText);

        HBox hbToggle = new HBox();
        Button btnSetModifiers = new Button("Set Modifiers");
            btnSetModifiers.setOnAction(eh -> lstElements.getItems().add(new Modifier(tglLCtrl.isSelected(),tglLShift.isSelected(),tglLAlt.isSelected(),tglLGUI.isSelected(),tglRCtrl.isSelected(),tglRShift.isSelected(),tglRAlt.isSelected(),tglRGUI.isSelected(),true)));
        Button btnClrModifiers = new Button("Clr Modifiers");
            btnClrModifiers.setOnAction(eh -> lstElements.getItems().add(new Modifier(tglLCtrl.isSelected(),tglLShift.isSelected(),tglLAlt.isSelected(),tglLGUI.isSelected(),tglRCtrl.isSelected(),tglRShift.isSelected(),tglRAlt.isSelected(),tglRGUI.isSelected(),false)));
        hbToggle.getChildren().addAll(tglLShift,tglLCtrl,tglLGUI,tglLAlt,tglRAlt,tglRGUI,tglRCtrl,tglRShift,btnSetModifiers,btnClrModifiers);

        HBox hbListControls = new HBox();
        Button btnUp = new Button("Up");
        Button btnDown= new Button("Down");
        Button btnDelete = new Button("Delete");

        btnUp.setOnAction(eh ->{
            int selectedIndex = lstElements.getSelectionModel().getSelectedIndex();
            if( selectedIndex > 1) {
                Collections.swap(lstElements.getItems(), selectedIndex,selectedIndex-1);
            }
        });

        btnDown.setOnAction( eh ->{

                int selectedIndex = lstElements.getSelectionModel().getSelectedIndex();
                if(selectedIndex == -1) { return; }
                if( selectedIndex < lstElements.getItems().size() - 1) {
                    Collections.swap(lstElements.getItems(), selectedIndex,selectedIndex+1);
                }

        });

        btnDelete.setOnAction(eh -> {
            int selectedIndex = lstElements.getSelectionModel().getSelectedIndex();
            if(selectedIndex != -1) {
                lstElements.getItems().remove(selectedIndex);
            }
        });

        hbListControls.getChildren().addAll(btnUp,btnDown,btnDelete);

        vb.getChildren().add(lstElements);
        vb.getChildren().add(hbListControls);
        vb.getChildren().add(hbTextEntry);
        vb.getChildren().add(hbToggle);
        vb.getChildren().add(createSpecial());
        vb.getChildren().add(gp);
        TextFlow txtFlow = new TextFlow();
        vb.getChildren().add(txtFlow);


        this.getDialogPane().setContent(vb);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        this.setResultConverter(btn -> {
            if(btn == ButtonType.OK) {
                KeyboardSequence seq = new KeyboardSequence();
                seq.setElements(lstElements.getItems());
                return seq;
            } else {
                return null;
            }
        });
    }

    private static Button createKeyButton(KeyboardStringConverter.HIDcode code, Consumer<KeyboardStringConverter.HIDcode> action) {
        Button b = new Button(code.descr());
        b.setOnAction(eh -> action.accept(code));
        return b;
    }

    private GridPane createSpecial() {

        Consumer<KeyboardStringConverter.HIDcode> action = code -> {
            KeyPress kp = new KeyPress();
            kp.elements.add(code);
            this.lstElements.getItems().add(kp);
        };

        GridPane gp = new GridPane();
        int width = 8;
        int y = 0;
        for(int i=0; i < KeyboardStringConverter.specialKeys.length; i++) {
            if(i != 0 && i%width==0) {
                y++;
            }
            Button special = createKeyButton(KeyboardStringConverter.specialKeys[i],action);
            gp.add(special,i % width,y);
        }
        return gp;
    }

    public void setValue(KeyboardSequence keyboardSequence) {
        if(keyboardSequence != null) {
            System.err.println("Keyboard Elements Size: "+keyboardSequence.getElements().size());
            System.out.println("Elements: "+keyboardSequence.getElements());
            if(!lstElements.getItems().setAll(keyboardSequence.getElements())) {
                System.err.println("Could Not Add Items");
                lstElements.getItems().addAll(keyboardSequence.getElements());
            }
            System.out.println("Element Size: "+lstElements.getItems().size());

        } else {
            System.err.println("Sent Key Sequence Was Null");
        }
    }
}
