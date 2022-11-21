module com.example.simpledatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;   // EK 4.11.22: file->proj struc->modules->dependencies->+ (tilf. sql jar)
    requires java.sql;
    requires java.naming;   // nødvendig: EK 4.11.22


    opens com.example.simpledatabase to javafx.fxml;
    exports com.example.simpledatabase;
}