module com.example.dyshootingrangeproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dyshootingrangeproject to javafx.fxml;
    exports com.example.dyshootingrangeproject;
}