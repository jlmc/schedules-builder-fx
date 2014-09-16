package org.xine.schedules.builder.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerticalMenuBar extends Application {
    public static TabPane tabPanel;

    // http://introjava.wordpress.com/2012/04/08/java-fx-2-ribbon-menu/
    // @Override
    // public void start(final Stage primaryStage) {
    // final BorderPane root = buildView();
    // final Scene scene = new Scene(root, 300, 250);
    // primaryStage.setTitle("Hello World!");
    // primaryStage.setScene(scene);
    // primaryStage.show();
    // }

    BorderPane buildView() {
        final BorderPane root = new BorderPane();
        tabPanel = new TabPane();
        root.setCenter(tabPanel);
        final Accordion accordion = new Accordion();
        Pane pane = null;
        TitledPane tiledPane;
        final General1Bar general1 = new General1Bar();
        pane = general1.getView();
        tiledPane = new TitledPane("General1", pane);
        accordion.getPanes().add(tiledPane);

        final General2Bar general2 = new General2Bar();
        pane = general2.getView();
        tiledPane = new TitledPane("General2", pane);
        accordion.getPanes().add(tiledPane);

        final General3Bar general3 = new General3Bar();
        pane = general3.getView();
        tiledPane = new TitledPane("General3", pane);
        accordion.getPanes().add(tiledPane);

        root.setLeft(accordion);
        return root;
    }

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final BorderPane root = buildView();
        final Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

class General1Bar {

    public Pane getView() {
        final Pane p = new Pane();
        final Button button = new Button("One");
        final Button button1 = new Button("Two");
        final VBox vBox = new VBox(5);
        vBox.getChildren().addAll(button, button1);
        p.getChildren().addAll(vBox);
        return p;
    }

}

class General2Bar {
    public Pane getView() {
        final Pane p = new Pane();
        final Button button = new Button("One");
        final Button button1 = new Button("Two");
        final VBox vBox = new VBox(5);
        vBox.getChildren().addAll(button, button1);
        p.getChildren().addAll(vBox);
        return p;
    }

}

class General3Bar {
    public Pane getView() {
        final Pane p = new Pane();
        final Button button = new Button("One");
        final Button button1 = new Button("Two");
        final VBox vBox = new VBox(5);
        vBox.getChildren().addAll(button, button1);
        p.getChildren().addAll(vBox);
        return p;
    }
}
