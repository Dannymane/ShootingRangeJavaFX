package com.example.dyshootingrangeproject;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewGunController implements Initializable {

    public Label Lid;
    public TextField TFname;
    public ComboBox CBammoName;
    public TextField TFpricePerShot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                CBammoName.getItems().addAll(ShootingRange.Company.getAmmunitionList());

    }
}
