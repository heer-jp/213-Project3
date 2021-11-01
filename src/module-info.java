module Exampl {
    exports unittesting;
    exports tuition;

    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires junit;
    requires javafx.controls;
    
    opens tuition to javafx.fxml;
}