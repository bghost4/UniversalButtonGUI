package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.KeyboardSequence;
import com.derpderphurr.button.action.KeyboardStringConverter;
import com.derpderphurr.button.action.keyboard.KeySequenceElement;
import com.derpderphurr.button.action.keyboard.Modifier;
import javafx.beans.InvalidationListener;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.util.Collections;


public class KeyboardSubEditor extends Dialog<KeyboardSequence> {
    private ListView<KeySequenceElement> lstElements = new ListView<>();
    private final TextArea txtEntry = new TextArea();
    private final TextFlow txtFlow = new TextFlow();

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

        lstElements.getItems().addListener( (InvalidationListener)  il -> generateTextFlow() );

        Button btnAddText = new Button("Add");
        btnAddText.setOnAction(a -> lstElements.getItems().addAll(KeyboardStringConverter.fromString(txtEntry.getText())) );
        HBox hbTextEntry = new HBox();
        hbTextEntry.getChildren().addAll(txtEntry,btnAddText);

        HBox hbToggle = new HBox();
        Button btnSetModifiers = new Button("Set Modifiers");
            btnSetModifiers.setOnAction(eh -> {
                lstElements.getItems().add(new Modifier(tglLCtrl.isSelected(),tglLShift.isSelected(),tglLAlt.isSelected(),tglLGUI.isSelected(),tglRCtrl.isSelected(),tglRShift.isSelected(),tglRAlt.isSelected(),tglRGUI.isSelected(),true));
            });
        Button btnClrModifiers = new Button("Clr Modifiers");
            btnClrModifiers.setOnAction(eh -> {
                lstElements.getItems().add(new Modifier(tglLCtrl.isSelected(),tglLShift.isSelected(),tglLAlt.isSelected(),tglLGUI.isSelected(),tglRCtrl.isSelected(),tglRShift.isSelected(),tglRAlt.isSelected(),tglRGUI.isSelected(),false));
            });
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
        vb.getChildren().add(gp);
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



    private void generateTextFlow() {

    }

    public void setValue(KeyboardSequence keyboardSequence) {
        if(keyboardSequence != null) {
            System.err.println("Keyboard Elements Size: "+keyboardSequence.getElements().size());
            System.out.println("Elements: "+keyboardSequence.getElements());
//            lstElements.getItems().clear();
//            lstElements.getItems().addAll(keyboardSequence.getElements());
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
