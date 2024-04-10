package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.Action;
import com.derpderphurr.button.action.ConsumerControl;
import com.derpderphurr.button.action.KeyboardSequence;
import com.derpderphurr.button.action.MouseAction;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseButton;

public class ActionListCell extends ListCell<Action> {
    public ActionListCell() {
        super();
        this.setOnMouseClicked(eh -> {
            if(eh.getClickCount() == 2 && eh.getButton() == MouseButton.PRIMARY) {
                showEditDialog();
            }
        });
    }

    private void showEditDialog() {
        if(getItem() != null) {
            if (this.getItem() instanceof KeyboardSequence ks) {
                KeyboardEditor ke = new KeyboardEditor();
                ke.setValue(ks);
                ke.showAndWait().ifPresent(this::setItem);
            } else if (this.getItem() instanceof ConsumerControl cc) {
                new ChoiceDialog<>(cc,ConsumerControl.values).showAndWait().ifPresent(this::setItem);
            } else if (this.getItem() instanceof MouseAction ma) {
                MouseEditor me = new MouseEditor();
                me.setValue(ma);
                me.showAndWait().ifPresent(this::setItem);
            } else {
                //Do Nothing
            }
        } else {
            //DO Nothing
        }
    }

    @Override
    protected void updateItem(Action item, boolean empty) {
        super.updateItem(item, empty);

        setGraphic(null);
        if(item == null || empty) {
            setText(null);
        } else {
            setText(item.toString());
        }

    }
}
