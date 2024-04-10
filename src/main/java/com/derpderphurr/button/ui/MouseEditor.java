package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.MouseAction;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MouseEditor extends Dialog<MouseAction> {
    private final Spinner<Integer>
            spnX = new Spinner<>(),
            spnY = new Spinner<>(),
            spnSX = new Spinner<>(),
            spnSY = new Spinner<>();

    final SpinnerValueFactory<Integer> smX = new SpinnerValueFactory.IntegerSpinnerValueFactory(-127,127,0);
    final SpinnerValueFactory<Integer> smY = new SpinnerValueFactory.IntegerSpinnerValueFactory(-127,127,0);
    final SpinnerValueFactory<Integer> smSX = new SpinnerValueFactory.IntegerSpinnerValueFactory(-127,127,0);
    final SpinnerValueFactory<Integer> smSY = new SpinnerValueFactory.IntegerSpinnerValueFactory(-127,127,0);

    private final ToggleButton
            btnLeftClick = new ToggleButton("L"),
            btnMiddleClick = new ToggleButton("M"),
            btnRightClick = new ToggleButton("R"),
            btnBack = new ToggleButton("Back"),
            btnForward = new ToggleButton("Forward");

    public MouseEditor() {

        spnX.setValueFactory(smX);
        spnY.setValueFactory(smY);
        spnSX.setValueFactory(smSX);
        spnSY.setValueFactory(smSY);

        spnX.setEditable(true);
        spnY.setEditable(true);
        spnSX.setEditable(true);
        spnSY.setEditable(true);

        HBox hbX = new HBox();
        hbX.getChildren().addAll(new Label("Delta X:"),spnX,new Label("Delta Scroll Pan:"),spnSX);
        HBox hbY = new HBox();
        hbY.getChildren().addAll(new Label("Delta Y:"),spnY,new Label("Delta Scroll:"),spnSY);
        HBox hbButtons = new HBox();
        hbButtons.getChildren().addAll(btnLeftClick,btnMiddleClick,btnRightClick,btnBack,btnForward);

        VBox vbContainer = new VBox();
        vbContainer.getChildren().addAll(hbX,hbY,hbButtons);

        getDialogPane().setContent(vbContainer);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);

        setResultConverter(btn -> {

            if(btn == ButtonType.OK) {
                MouseAction result = new MouseAction();
                result.x = spnX.getValue();
                result.y = spnY.getValue();
                result.sx = spnSX.getValue();
                result.sy = spnSY.getValue();
                result.forward_button = btnForward.isSelected();
                result.back_button = btnBack.isSelected();
                result.left_button = btnLeftClick.isSelected();
                result.right_button = btnRightClick.isSelected();
                result.middle_button = btnMiddleClick.isSelected();
                return result;
            } else {
                return null;
            }
        });


    }

    public void setValue(MouseAction ma) {
        smX.setValue(ma.x);
        smY.setValue(ma.y);
        smSX.setValue(ma.sx);
        smSY.setValue(ma.sy);

        btnLeftClick.setSelected(ma.left_button);
        btnRightClick.setSelected(ma.right_button);
        btnMiddleClick.setSelected(ma.middle_button);
        btnForward.setSelected(ma.forward_button);
        btnBack.setSelected(ma.back_button);
    }



}
