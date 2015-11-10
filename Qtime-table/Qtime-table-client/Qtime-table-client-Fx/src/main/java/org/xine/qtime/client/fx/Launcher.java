package org.xine.qtime.client.fx;

import java.util.List;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceApplication;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.qtime.client.fx.controllers.ApplicationController;
import org.xine.qtime.client.fx.controllers.NotificationController;
import org.xine.qtime.client.fx.gui.FxDecorateScene;
import org.xine.qtime.client.fx.gui.Views;
import org.xine.qtime.client.fx.gui.icons.Icons;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends GuiceApplication {

	public static final int MIN_WIDTH = 995;
	public static final int MIN_HEIGHT = 650;
	private ApplicationController applicationController;

	@Inject
	private GuiceFXMLLoader fxmlLoader;

	private static void loadFonts() {
		loadFont("/font/awesome/fontawesome-webfont.ttf");
		loadFont("/font/awesome/ubuntu/Ubuntu-L.ttf");
	}

	private static void loadFont(final String location) {
		Font.loadFont(Launcher.class.getResourceAsStream(location), 12);
	}

	// @Override
	public void basestart(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/Styles.css");

		stage.setTitle("JavaFX and Maven");
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void init(List<Module> modules) throws Exception {
		modules.add(new AbstractModule() {
			@Override
			protected void configure() {
				// bind(SubjectConnector.class).to(SubjectConnectorService.class).in(Singleton.class);
				// bind(PropertyManager.class).to(SimplePropertyManager.class).in(Singleton.class);
				// bind(TaskProvider.class).to(TaskProviderImpl.class).in(Singleton.class);
			}
		});

	}

	@Override
	public void start(final Stage mainStage) throws Exception {
		loadFonts();

		// Mac OS X workaround for Smack debugging and JavaFX (Swing + JavaFX issue).
		System.setProperty("java.awt.headless", "false");

		// stage.initStyle(StageStyle.UNDECORATED);
		// define the Application icon
		mainStage.setTitle("");
		// mainStage.getIcons().add(ImageUtils.getFXImageSafely("/images/e-plan.png"));
		mainStage.getIcons().add(Icons.getIcon(Icons.E_PLAN));

		mainStage.setMinHeight(MIN_HEIGHT);
		mainStage.setMinWidth(MIN_WIDTH);
		mainStage.setHeight(MIN_HEIGHT);
		mainStage.setWidth(MIN_WIDTH);

		final Result applicationViewResult = this.fxmlLoader.load(getClass().getResource(Views.APPLICATION_VIEW));

		final FxDecorateScene fxDecorateScene = new FxDecorateScene((Parent) applicationViewResult.getRoot(),
				mainStage);

		fxDecorateScene.setEdgeSize(5);
		mainStage.setScene(fxDecorateScene);

		this.applicationController = applicationViewResult.getController();
		this.applicationController.setDecorateScene(fxDecorateScene);

		mainStage.show();

		fxDecorateScene.getController().centerOnScreen();

		// notification compoment
		final Result notificationViewResult = this.fxmlLoader.load(getClass().getResource(Views.NOTIFICATION_VIEW));
		final Stage notificationStage = new Stage(StageStyle.TRANSPARENT);
		notificationStage.setScene(new Scene(notificationViewResult.getRoot()));
		final NotificationController notificationController = notificationViewResult.getController();
		notificationController.setStage(notificationStage);
		notificationController.init();

		this.applicationController.setNotificationController(notificationController);

	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
