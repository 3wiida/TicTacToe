/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.view.records;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;
/**
 *
 * @author OmarAbdulaziz
 */
public class RecordListCell extends ListCell<String>{
    private HBox hbox;
    private NewRecordController controller;

    public RecordListCell() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewRecord.fxml"));
            hbox = loader.load();
            controller = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
         if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            controller.setData(item);
            setGraphic(hbox);
        }
    }
}
