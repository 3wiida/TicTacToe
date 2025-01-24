/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe.model;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author OmarAbdulaziz
 */

public class RecordsScanner {
    public static ObservableList<String> getRecordedGameFiles() {
        File folder = new File("records");
        ObservableList<String> fileList = FXCollections.observableArrayList();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist: ");
        }

        return fileList;
    }
}
