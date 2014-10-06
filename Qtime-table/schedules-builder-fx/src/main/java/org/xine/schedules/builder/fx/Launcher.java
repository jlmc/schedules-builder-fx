package org.xine.schedules.builder.fx;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceApplication;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.schedules.builder.fx.controllers.QApplicationController;
import org.xine.schedules.builder.fx.gui.FxDecorateScene;
import org.xine.schedules.builder.fx.gui.Views;

import com.google.inject.Module;

/**
 * The Class Launcher.
 */
public class Launcher extends GuiceApplication {

    /** The Constant MIN_WIDTH. */
    public static final int MIN_WIDTH = 800;

    /** The Constant MIN_HEIGHT. */
    public static final int MIN_HEIGHT = 600;

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The application controller. */
    private QApplicationController applicationController;

    /*
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    // @Override
    /**
     * Start aaa.
     * @param primaryStage
     *            the primary stage
     * @throws Exception
     *             the exception
     */
    public static void startAAA(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("Hello World!");
        final Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        final StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    /**
     * The main method.
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /*
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage stage) throws Exception {
        loadFonts();

        // Mac OS X workaround for Smack debugging and JavaFX (Swing + JavaFX issue).
        System.setProperty("java.awt.headless", "false");

        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Qxine");
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setHeight(MIN_HEIGHT);
        stage.setWidth(MIN_WIDTH);

        // stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/org/spout/platform/resources/spout.png")));

        final Result appViewResult = this.fxmlLoader.load(getClass().getResource(Views.APP_VIEW));

        final FxDecorateScene fxDecorateScene = new FxDecorateScene((Parent) appViewResult.getRoot(), stage);

        fxDecorateScene.setEdgeSize(5);

        stage.setScene(fxDecorateScene);

        this.applicationController = appViewResult.getController();
        this.applicationController.setDecorateScene(fxDecorateScene);

        stage.show();

        // final Parent root = FXMLLoader.load(getClass().getResource(Views.APP_VIEW));

        // final Scene scene = new Scene((Parent) appViewResult.getRoot(), 800, 650);

        // stage.setScene(scene);
        // stage.show();

        // final ScreensController mainContainer = new ScreensController();
        // mainContainer.loadScreen(Views.APP_VIEW, Views.APP_VIEW);
        // // mainContainer.loadScreen(REGISTER, REGISTER_FILE);
        // // mainContainer.loadScreen(MAIN, MAIN_FILE);
        // // mainContainer.loadScreen(RANKING, RANKING_FILE);
        //
        // mainContainer.setScreen(Views.APP_VIEW);
        //
        // /*
        // * AnchorPane.setTopAnchor(n, 0.0);
        // * AnchorPane.setRightAnchor(n, 0.0);
        // * AnchorPane.setLeftAnchor(n, 0.0);
        // * AnchorPane.setBottomAnchor(n, 0.0);
        // */
        //
        // final Group root = new Group();
        //
        // // final VBox vg = new VBox(mainContainer);
        // // vg.setMaxHeight(Double.MAX_VALUE);
        //
        // root.getChildren().addAll(mainContainer);
        // // final BorderPane root = new BorderPane();
        // // root.setCenter(mainContainer);
        //
        // // final Scene scene = new Scene(vg);
        // final Scene scene = new Scene(root);
        //
        // primaryStage.setScene(scene);
        //
        // primaryStage.show();
    }

    /**
     * Load fonts.
     */
    private static void loadFonts() {
        loadFont("/font/awesome/fontawesome-webfont.ttf");
        loadFont("/font/awesome/ubuntu/Ubuntu-L.ttf");
    }

    /**
     * Load font.
     * @param location
     *            the location
     */
    private static void loadFont(final String location) {
        Font.loadFont(Launcher.class.getResourceAsStream(location), 12);

    }

    /*
     * (non-Javadoc)
     * @see javafx.application.Application#stop()
     */
    @Override
    public void stop() throws Exception {

        System.out.println("QUITING");
        super.stop();
    }

    /*
     * (non-Javadoc)
     * @see org.xine.fx.guice.GuiceApplication#init(java.util.List)
     */
    @Override
    public void init(final List<Module> arg0) throws Exception {
        // TODO Auto-generated method stub

    }

}
