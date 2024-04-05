package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.MouseAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class MouseEditor extends ActionEditor<MouseAction> {

    private final Label lblDisplay = new Label();

    public MouseEditor() {
        HBox hbLayout = new HBox();
        Button btnEdit = new Button("Edit");
        hbLayout.getChildren().addAll(lblDisplay,btnEdit);
    }

    @Override
    public void setValue(MouseAction value) {
        //set the Display String for this Event
        //set the editor content to this event
    }

    @Override
    public MouseAction getValue() {
        //get event from sub editor
        return null;
    }
}
