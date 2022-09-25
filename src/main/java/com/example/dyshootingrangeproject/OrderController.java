package com.example.dyshootingrangeproject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class OrderController implements Initializable {
    public Label Ltotal;
    public Label LpricePerShot;
    public TextField TFshots;
    public Label LchosenGun;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
