package com.example.dyshootingrangeproject;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ShootingRange extends Application {
    public static Stage stage1;
    public static Stage AdminStage;
    @Override
    public void start(Stage stage) throws IOException {
        stage1 = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ShootingRange.class.getResource("ChooseAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 100);
        stage.setTitle("Choose account");
        stage.setScene(scene);
        stage.show();
        stage1 = stage;
        try{
            AdminStage = new Stage();
            FXMLLoader fxmlLoaderAdmin = new FXMLLoader(ShootingRange.class.getResource("AdminMain.fxml"));
            Scene scene2 = new Scene(fxmlLoaderAdmin.load(), 600, 400);
            AdminStage.setTitle("Admin menu");
            AdminStage.setScene(scene2);
        } catch (Exception e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while uploading an xml file");
        }

    }
    public class Company{
        public static List<Client> Clients = new ArrayList<Client>();
        public static List<Gun> Guns = new ArrayList<Gun>();
        public static List<Ammunition> Ammunitions = new ArrayList<Ammunition>();
        public static List<String> orderList = new ArrayList<String>();

//        public static List<String> AmmoLabels = Arrays.asList("Fake","L1name","L2name","L3name","L4name");

        public static boolean check_registration(String name_, String surname_){
            for (Client c : Clients){
                if (c.name.equals(name_) && c.surname.equals(surname_)){
                    return true;
                }
            }
            return false;
        }
        public static Ammunition getAmmunitionById(int id_)
        {
            for (Ammunition a : Ammunitions){
                if (a.own_id == id_) return a;
            }
            return null;
        }
        public static List<String> getAmmunitionList(){
            List<String> l = new ArrayList<String>();
            for (Ammunition a : Ammunitions){
                l.add(a.name);
            }
            return l;
        }
        public static List<String> getClientList(){
            List<String> l = new ArrayList<String>();
            String clientData;
            for (Client c : Clients){
                clientData = c.name+" "+c.surname+" "+c.document;
                l.add(clientData);
            }
            return l;
        }

        public static List<String> getGunList(){
            List<String> l = new ArrayList<String>();
            for (Gun g : Guns){
                l.add(g.name);
            }
            return l;
        }
        public static Gun getGunByName(String name_){
            for (Gun g : Guns){
                if (g.name.indexOf(name_) != -1) return g;
            }
            return null;
        }
        public static int getAmmunitionAmount(String name_){
            for (Ammunition a : Ammunitions){
                if (a.name.equals(name_)) return a.amount;
            }
            return 0;
        }
    public static int takeAmmunitionAmount(String name_, int take_){
        for (Ammunition a : Ammunitions){
            if (a.name.equals(name_)){
                a.setAmount(a.amount - take_);
            }
        }
        return 0;
    }
}
public static class Client{
        public String name;
        public String surname;
        public String document;
        public Client(String name_,String surname_, String document_){
            name = name_;
            surname = surname_;
            document = document_;
        }
}
    public static class Gun{
        public static int id = 1;
        public int own_id;
        public String name;
        public String ammoName;
        public double PricePerShot;
        public StringProperty PricePerShotProperty;
        public Gun(String name_, String ammoName_,double PricePerShot_){
            name = name_;
            ammoName = ammoName_;
            PricePerShot = PricePerShot_;
            PricePerShotProperty = new SimpleStringProperty();
            PricePerShotProperty.set(String.valueOf(PricePerShot_));
            own_id = id;
            id++;
        }
    }
public static class Ammunition{
    public String name;
    public static int id = 1;
    public int own_id;
    public int amount;
//    public double percentAmount;
    public DoubleProperty barUpdater;
    public DoubleProperty labelUpdater;
    public Ammunition(String name_){
        name = name_;
        amount = 0;
//        percentAmount = 0;
        own_id = id;
        id++;
        barUpdater = new SimpleDoubleProperty(0);
        labelUpdater = new SimpleDoubleProperty(0);
    }
    public void setAmount(int amount_){
        amount = amount_;
        barUpdater.set((double)amount_/500);
        labelUpdater.set(amount_);
//        percentAmount = (double)amount_/500;
    }
}
    public static void main(String[] args) {
        launch();
    }
}