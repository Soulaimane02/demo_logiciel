module appli.todolistjx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires jbcrypt;

    opens appli.todolistjx to javafx.fxml;
    exports appli.todolistjx;
    exports appli.todolistjx.controllers;
    opens appli.todolistjx.controllers to javafx.fxml;

    opens appli.todolistjx.model to javafx.base; // Ajoute cette ligne
}