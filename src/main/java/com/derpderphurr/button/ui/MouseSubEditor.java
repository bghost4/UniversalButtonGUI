package com.derpderphurr.button.ui;

import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class MouseSubEditor extends Pane {
    private final Spinner<Integer>
            spnX = new Spinner<>(),
            spnY = new Spinner<>(),
            spnSX = new Spinner<>(),
            spnSY = new Spinner<>();
    private final ToggleButton
            btnLeftClick = new ToggleButton("L"),
            btnMiddleClick = new ToggleButton("M"),
            btnRightClick = new ToggleButton("R");

}
