package com.example.dyshootingrangeproject;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientList implements Initializable{

    public ListView LVclientList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (String s : ShootingRange.Company.getClientList()){
            LVclientList.getItems().add(s);
        }

    }
}
