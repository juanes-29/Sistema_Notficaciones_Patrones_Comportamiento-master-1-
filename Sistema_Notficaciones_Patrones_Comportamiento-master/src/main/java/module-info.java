module co.edu.uniquindio.poo.sistemas_notificacion {
    requires javafx.controls;
    requires javafx.fxml;

    exports co.edu.uniquindio.poo.sistemas_notificacion.viewController;


    opens co.edu.uniquindio.poo.sistemas_notificacion to javafx.fxml;
    opens co.edu.uniquindio.poo.sistemas_notificacion.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemas_notificacion;
}