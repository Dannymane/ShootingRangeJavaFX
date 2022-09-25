package com.example.dyshootingrangeproject;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderChooseGunController implements Initializable {
    public ComboBox CBgun;
    public TextField TFshots;
    public Label LpricePerShot;
    public Label Ltotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CBgun.getItems().addAll(ShootingRange.Company.getGunList());

    }
}
