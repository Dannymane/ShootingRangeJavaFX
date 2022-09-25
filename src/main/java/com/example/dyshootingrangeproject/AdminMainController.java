package com.example.dyshootingrangeproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AdminMainController implements Initializable {
    @FXML
    public Button Bregister;
    public Button BaddNewAmmunition;
    public Label L1name;
    public Label L2name;
    public Label L3name;
    public Label L4name;
    public ProgressBar PB1;
    public ProgressBar PB2;
    public ProgressBar PB3;
    public ProgressBar PB4;
    public Label L1amount;
    public Label L2amount;
    public Label L3amount;
    public Label L4amount;
    public Button BAddNewGun;
    public Button B1A;
    public Button B2A;
    public Button B3A;
    public Button B4A;
    public ListView LVorderList;
//    public ComboBox CBammoName = new ComboBox<>();

    @FXML

    public void BregisterAct(ActionEvent actionEvent) {

        try {

            FXMLLoader fxmlLoaderAdd = new FXMLLoader(ShootingRange.class.getResource("Registration.fxml"));

            Parent parent = fxmlLoaderAdd.load();

            Alert alRegister = new Alert(Alert.AlertType.NONE);
            alRegister.setTitle("Registration");
            alRegister.getDialogPane().setContent(parent);

            RegisterController con = fxmlLoaderAdd.<RegisterController>getController();

            ButtonType dialogRegister = new ButtonType("Register");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alRegister.getButtonTypes().setAll(dialogRegister, dialogCancel);
            Optional<ButtonType> result = alRegister.showAndWait();

            if (result.get() == dialogRegister) {
                if (!con.TFname.getText().isEmpty() && !con.TFsurname.getText().isEmpty() && !con.TFdocumentId.getText().isEmpty()) {
                    ShootingRange.Company.Clients.add(new ShootingRange.Client(con.TFname.getText(),
                            con.TFsurname.getText(), con.TFdocumentId.getText()));
                    System.out.println(con.TFname.getText() + con.TFsurname.getText());
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Please fill all fields.");
                    errorAlert.show();
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        CBammoName.getItems().addAll("Lisiniec","Warszawa","Olsztyn","Kraków");



    }

    public void BAddNewGunAct(ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoaderAddGun = new FXMLLoader(ShootingRange.class.getResource("AddNewGun.fxml"));

            Parent parent = fxmlLoaderAddGun.load();

            Alert alRegister = new Alert(Alert.AlertType.NONE);
            alRegister.setTitle("New gun");
            alRegister.getDialogPane().setContent(parent);

            AddNewGunController con2 = fxmlLoaderAddGun.<AddNewGunController>getController();
            con2.Lid.setText(String.valueOf(ShootingRange.Gun.id));
            ButtonType dialogRegister = new ButtonType("Add");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alRegister.getButtonTypes().setAll(dialogRegister, dialogCancel);
            Optional<ButtonType> result = alRegister.showAndWait();
//            con2.CBammoName.getItems().addAll("Fuck");

            if (result.get() == dialogRegister) {
                if (!con2.TFname.getText().isEmpty() && con2.CBammoName.getValue() != null
                && !con2.TFpricePerShot.getText().isEmpty()) {
                    ShootingRange.Company.Guns.add(new ShootingRange.Gun(con2.TFname.getText(),
                            con2.CBammoName.getValue().toString(),Double.parseDouble(con2.TFpricePerShot.getText())));

                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Please fill all fields.");
                    errorAlert.show();
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }

    public void BaddNewAmmunitionAct(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoaderAddAmmunition = new FXMLLoader(ShootingRange.class.getResource("AddNewAmmunition.fxml"));

            Parent parent = fxmlLoaderAddAmmunition.load();

            Alert alNewAmmo = new Alert(Alert.AlertType.NONE);
            alNewAmmo.setTitle("New ammunition");
            alNewAmmo.getDialogPane().setContent(parent);

            AddNewAmmoController con3 = fxmlLoaderAddAmmunition.<AddNewAmmoController>getController();
            con3.LidAMMO.setText(String.valueOf(ShootingRange.Ammunition.id));
            ButtonType dialogRegister = new ButtonType("Add");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alNewAmmo.getButtonTypes().setAll(dialogRegister, dialogCancel);
            Optional<ButtonType> result = alNewAmmo.showAndWait();

            if (result.get() == dialogRegister) {
                if (!con3.TFnameAMMO.getText().isEmpty()) {
                    ShootingRange.Ammunition a = new ShootingRange.Ammunition(con3.TFnameAMMO.getText());
                    ShootingRange.Company.Ammunitions.add(a);
//                    String m = ShootingRange.Company.AmmoLabels[1];
//                    DoubleProperty barUpdater = new SimpleDoubleProperty(a.percentAmount);
//                    if (con3.CBammoName != null)
//                        CBammoName.getItems().add(a.name);
//                        con3.CBammoName.getItems().add(a.name);
//                        else con3.CBammoName.setItems(a.name);

                    switch (a.own_id){
                        case 1:

                            L1name.setText(a.name);
                            PB1.progressProperty().bind(a.barUpdater);
                            L1amount.textProperty().bind(a.labelUpdater.asString());
                            PB1.setVisible(true);
                            L1name.setVisible(true);
                            B1A.setVisible(true);
                            L1amount.setVisible(true);
                            break;
                        case 2:
                            L2name.setText(a.name);
                            PB2.progressProperty().bind(a.barUpdater);
                            L2amount.textProperty().bind(a.labelUpdater.asString());
                            PB2.setVisible(true);
                            L2name.setVisible(true);
                            B2A.setVisible(true);
                            L2amount.setVisible(true);
                            break;
                        case 3:
                            L3name.setText(a.name);
                            PB3.progressProperty().bind(a.barUpdater);
                            L3amount.textProperty().bind(a.labelUpdater.asString());
                            PB3.setVisible(true);
                            L3name.setVisible(true);
                            B3A.setVisible(true);
                            L3amount.setVisible(true);
                            break;
                        case 4:
                            L4name.setText(a.name);
                            PB4.progressProperty().bind(a.barUpdater);
                            L4amount.textProperty().bind(a.labelUpdater.asString());
                            PB4.setVisible(true);
                            L4name.setVisible(true);
                            B4A.setVisible(true);
                            L4amount.setVisible(true);
                            break;
                        default:
                            throw new RuntimeException();
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Please fill all fields.");
                    errorAlert.show();
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }
    public void createAndShowAmmunitionDialog(int id_){
        try {
            String name_ = ShootingRange.Company.getAmmunitionById(id_).name;
            FXMLLoader fxmlLoaderChangeAmmu = new FXMLLoader(ShootingRange.class.getResource("ChangeAmount.fxml"));
            Parent parent = fxmlLoaderChangeAmmu.load();

            Alert alChangeAmmu = new Alert(Alert.AlertType.NONE);
            alChangeAmmu.setTitle("Change amount");
            alChangeAmmu.getDialogPane().setContent(parent);

            AddNewAmmoController con2 = fxmlLoaderChangeAmmu.<AddNewAmmoController>getController();
            con2.Lname.setText(name_);
            ButtonType dialogRegister = new ButtonType("Confirm");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alChangeAmmu.getButtonTypes().setAll(dialogRegister, dialogCancel);
            Optional<ButtonType> result = alChangeAmmu.showAndWait();

            if (result.get() == dialogRegister) {
                if (!con2.TFammuAmount.getText().isEmpty()){
//                    ShootingRange.Company.getAmmunitionById(id_).amount = parseInt(con2.TFammuAmount.getText());
                    ShootingRange.Company.getAmmunitionById(id_).setAmount(parseInt(con2.TFammuAmount.getText()));
//                    System.out.println(ShootingRange.Company.getAmmunitionById(id_).percentAmount);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }

    public void B1Act(ActionEvent actionEvent) {
        createAndShowAmmunitionDialog(1);
    }
    public void B2Act(ActionEvent actionEvent) {
        createAndShowAmmunitionDialog(2);
    }

    public void B3Act(ActionEvent actionEvent) {
        createAndShowAmmunitionDialog(3);
    }

    public void B4Act(ActionEvent actionEvent) {
        createAndShowAmmunitionDialog(4);
    }

    public void BclientListAct(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoaderClientList = new FXMLLoader(ShootingRange.class.getResource("ClientList.fxml"));
            Parent parent = fxmlLoaderClientList.load();

            Alert alClientList = new Alert(Alert.AlertType.NONE);
            alClientList.setTitle("Client list");
            alClientList.getDialogPane().setContent(parent);

//            AddNewAmmoController con2 = fxmlLoaderClientList.<AddNewAmmoController>getController();
//            con2.Lname.setText(name_);
            ButtonType dialogRegister = new ButtonType("Confirm");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alClientList.getButtonTypes().setAll(dialogRegister, dialogCancel);
            alClientList.showAndWait();
//            Optional<ButtonType> result = alClientList.showAndWait();

//            if (result.get() == dialogRegister) {
//                if (!con2.TFammuAmount.getText().isEmpty()){
////                    ShootingRange.Company.getAmmunitionById(id_).amount = parseInt(con2.TFammuAmount.getText());
//                    ShootingRange.Company.getAmmunitionById(id_).setAmount(parseInt(con2.TFammuAmount.getText()));
////                    System.out.println(ShootingRange.Company.getAmmunitionById(id_).percentAmount);
//                }
//            }
        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }

    public void BreturnAct(ActionEvent actionEvent) {
//        stage = new Stage();
//        Scene scene = new Scene(fxmlLoaderClient.load());
//        stage.setTitle("Authorization");
//        stage.setScene(scene);
//        stage.show();
//        Stage AdminStage = (Stage) BAddNewGun.getScene().getWindow();
        ShootingRange.AdminStage.hide();
        ShootingRange.stage1.show();
    }

    public void BrefreshAct(ActionEvent actionEvent) {
        LVorderList.getItems().setAll();
        for (String s : ShootingRange.Company.orderList){
            LVorderList.getItems().add(s);
        }
    }
}