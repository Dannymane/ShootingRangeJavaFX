package com.example.dyshootingrangeproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ChooseAccountController implements Initializable {

    public Button adminButton;
    public String NameAndUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void adminClick(ActionEvent actionEvent) {
        Stage stage = (Stage) adminButton.getScene().getWindow();
        stage.hide();
        ShootingRange.AdminStage.show();

    }

    public void BclientAct(ActionEvent actionEvent) {

        try{
            FXMLLoader fxmlLoaderClient = new FXMLLoader(ShootingRange.class.getResource("Authorization.fxml"));
            Parent parent = fxmlLoaderClient.load();
            Alert alAuthorization = new Alert(Alert.AlertType.NONE);
            alAuthorization.setTitle("Authorization");
            alAuthorization.getDialogPane().setContent(parent);

            AuthorizationController contr = fxmlLoaderClient.<AuthorizationController>getController();

            ButtonType dialogDodaj = new ButtonType("Confirm");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alAuthorization.getButtonTypes().setAll(dialogDodaj, dialogCancel);
            Optional<ButtonType> result = alAuthorization.showAndWait();
            if (result.get() == dialogDodaj)
            {
                NameAndUsername = contr.TFname.getText()+" "+contr.TFsurname.getText();
                boolean ok = false;
                for(String s : ShootingRange.Company.getClientList()) {
                    if (s.indexOf(NameAndUsername) != -1) {
                        Stage stage = (Stage) adminButton.getScene().getWindow();
                        stage.hide();
                        makeOrder();
                        ok = true;
                    }
                }
                if(!ok){
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("You have to make a registration first!");
                    errorAlert.show();
                }


            }else{
                ShootingRange.stage1.show();
            }

        } catch (Exception e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }
    public void makeOrder(){
        try {
//            String name_ = ShootingRange.Company.getAmmunitionById(id_).name;
            FXMLLoader fxmlLoaderOrder = new FXMLLoader(ShootingRange.class.getResource("OrderChooseGun.fxml"));
            Parent parent = fxmlLoaderOrder.load();

            Alert alOrder = new Alert(Alert.AlertType.NONE);
            alOrder.setTitle("Making order");
            alOrder.getDialogPane().setContent(parent);

            OrderChooseGunController con2 = fxmlLoaderOrder.<OrderChooseGunController>getController();

            ButtonType dialogConfirm = new ButtonType("Confirm");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alOrder.getButtonTypes().setAll(dialogConfirm, dialogCancel);
            Optional<ButtonType> result = alOrder.showAndWait();
            if (result.get() == dialogConfirm) {
                if (con2.CBgun != null) {
                    ShootingRange.Gun gun = ShootingRange.Company.getGunByName(con2.CBgun.getValue().toString());
                    completeAmount(gun);
                }
            }else{
                ShootingRange.stage1.show();
            }
        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }
    public void completeAmount(ShootingRange.Gun gun){
        try {
            FXMLLoader fxmlLoaderCompleteAmount = new FXMLLoader(ShootingRange.class.getResource("Order.fxml"));
            Parent parent = fxmlLoaderCompleteAmount.load();

            Alert alOrder = new Alert(Alert.AlertType.NONE);
            alOrder.setTitle("Choosing amount");
            alOrder.getDialogPane().setContent(parent);

            OrderController con2 = fxmlLoaderCompleteAmount.<OrderController>getController();
            con2.LpricePerShot.setText(Double.toString(gun.PricePerShot));
            con2.LchosenGun.setText(gun.name);

            ButtonType dialogConfirm = new ButtonType("Confirm");
            ButtonType dialogCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alOrder.getButtonTypes().setAll(dialogConfirm, dialogCancel);
            Optional<ButtonType> result = alOrder.showAndWait();
            if (result.get() == dialogConfirm) {
                if (con2.TFshots != null) {
                    if (parseInt(con2.TFshots.getText()) < ShootingRange.Company.getAmmunitionAmount(gun.ammoName)) {
                        ShootingRange.Company.takeAmmunitionAmount(gun.ammoName, parseInt(con2.TFshots.getText()));
                        ShootingRange.Company.orderList.add(NameAndUsername+" "+gun.name+" "+gun.ammoName+" "+con2.TFshots.getText()+
                                " "+Double.toString(parseInt(con2.TFshots.getText()) * gun.PricePerShot)+" PLN");


                        ShootingRange.stage1.show();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setTitle("Confirmed");
                        errorAlert.setHeaderText("Total amount of your order:" + Double.toString(parseInt(con2.TFshots.getText()) * gun.PricePerShot));
                        errorAlert.show();
                    }else{
                        ShootingRange.stage1.show();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setTitle("Rejected");
                        errorAlert.setHeaderText("Not enought ammunition in storage for your order :(");
                        errorAlert.show();
                    }


                } else {
                    completeAmount(gun);
                }
            }else{
                ShootingRange.stage1.show();
            }
        } catch (Exception e) {
            System.out.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
            errorAlert.show();
        }
    }
}

