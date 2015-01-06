package org.xine.qtime.fxdesktop;

import org.xine.fx.guice.GuiceApplication;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.qtime.fxdesktop.controllers.ApplicationController;
import org.xine.qtime.fxdesktop.gui.FxDecorateScene;
import org.xine.qtime.fxdesktop.gui.Views;

import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * The Class Launcher.
 */

public class Launcher extends GuiceApplication {

    /** The Constant MIN_WIDTH. */
    public static final int MIN_WIDTH = 995;

    /** The Constant MIN_HEIGHT. */
    public static final int MIN_HEIGHT = 650;

    /** The Constant LOGGER. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

    /** The application controller. */
    private ApplicationController applicationController;

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /**
     * Load fonts.
     */
    private static void loadFonts() {
        loadFont("/font/awesome/fontawesome-webfont.ttf");
        loadFont("/font/awesome/ubuntu/Ubuntu-L.ttf");
    }

    /**
     * Load resource bundle.
     * @return the resource bundle
     */
    private static ResourceBundle loadResourceBundle() {
        final Locale currentLocale = new Locale("pt");
        final ResourceBundle resourceBundle = ResourceBundle.getBundle(
                "org.xine.qtime.fxdesktop.language", currentLocale);
        return resourceBundle;
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
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        loadFonts();

        // Mac OS X workaround for Smack debugging and JavaFX (Swing + JavaFX issue).
        System.setProperty("java.awt.headless", "false");

        final ResourceBundle resources = loadResourceBundle();

        // stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle(resources.getString("AppName"));
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setHeight(MIN_HEIGHT);
        primaryStage.setWidth(MIN_WIDTH);

        // stage.getIcons().add(new Image(this.getClass()
        // .getResourceAsStream("/org/spout/platform/resources/spout.png")));

        final Result appViewResult = this.fxmlLoader.load(getClass().getResource(Views.APP_VIEW),
                resources);

        final FxDecorateScene fxDecorateScene = new FxDecorateScene(
                (Parent) appViewResult.getRoot(), primaryStage);

        fxDecorateScene.setEdgeSize(5);

        primaryStage.setScene(fxDecorateScene);

        this.applicationController = appViewResult.getController();
        this.applicationController.setDecorateScene(fxDecorateScene);

        primaryStage.show();

    }

    /**
     * This method is used to fetch and/or create (Guice) modules necessary
     * to fully construct this application.
     * <p>
     * The modules that are initialized in this method and added to the passed List will be used to
     * create the {@link Injector} instance that is used in the context of this application.
     * </p>
     * @param modules
     *            A list of modules (initially empty) that shall be used to create the
     *            injector to be used in the context of this application.
     * @throws Exception
     *             if anything goes wrong during initialization.
     * @see #getInjector()
     */
    @Override
    public void init(final List<Module> modules) throws Exception {
        // TODO:: no modules configurated eat
    }

    /**
     * The main method.
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {

        launch(args);
    }

}
