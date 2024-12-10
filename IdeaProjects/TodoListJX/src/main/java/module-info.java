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
    requires org.json;

    opens appli to javafx.fxml;
    exports appli;
    //exports appli.controllers;
    //opens appli.controllers to javafx.fxml;

    opens appli.model to javafx.base;
    exports appli.accueil;
    opens appli.accueil to javafx.fxml;
    exports appli.model.repository;
    opens appli.model.repository to javafx.fxml; // Ajoute cette ligne
}