module com.example.oblig2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oblig2 to javafx.fxml;
    exports com.example.oblig2;
}