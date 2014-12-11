package org.xine.qtime.fxdesktop.controllers;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;

@FXMLController
public class StartUpController extends ContentController {
    /** The root. */
    @FXML
    private AnchorPane root;

    /** The play button. */
    @FXML
    private Button playButton;

    /** The progress. */
    @FXML
    private ProgressIndicator progress;

    private Task<Void> counter;

    /**
     * Instantiates a new start up controller.
     */
    public StartUpController() {
        super();
        setName("StartUp");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        this.progress.setVisible(false);

        // ///////////////////////////////////

        // final StackPane stack = new StackPane();
        //
        // this.counter = new Task<Void>() {
        // @Override
        // public Void call() throws Exception {
        // StartUpController.this.progress.setVisible(true);
        //
        // for (int i = 1; i <= 100; i++) {
        // Thread.sleep(200);
        // updateProgress(i, 100);
        // System.out.println(i + "%");
        // }
        //
        // StartUpController.this.progress.setVisible(false);
        // return null;
        // }
        // };
        //
        // this.progress.progressProperty().bind(this.counter.progressProperty());
        // this.progress.progressProperty().addListener((obs, oldProgress, newProgress) -> {
        // final PseudoClass warning = PseudoClass.getPseudoClass("warning");
        // final PseudoClass critical = PseudoClass.getPseudoClass("critical");
        // if (newProgress.doubleValue() < 0.3) {
        // this.progress.pseudoClassStateChanged(warning, false);
        // this.progress.pseudoClassStateChanged(critical, true);
        // } else if (newProgress.doubleValue() < 0.65) {
        // this.progress.pseudoClassStateChanged(warning, true);
        // this.progress.pseudoClassStateChanged(critical, false);
        // } else {
        // this.progress.pseudoClassStateChanged(warning, false);
        // this.progress.pseudoClassStateChanged(critical, false);
        // }
        // });
        //
        // stack.setStyle("-fx-background-color: antiqueWhite;");
        // stack.getChildren().add(this.progress);
        // AnchorPane.setTopAnchor(stack, Double.valueOf(0d));
        // AnchorPane.setLeftAnchor(stack, Double.valueOf(0d));
        // AnchorPane.setRightAnchor(stack, Double.valueOf(0d));
        // AnchorPane.setBottomAnchor(stack, Double.valueOf(0d));
        //
        // this.root.getChildren().clear();
        // this.root.getChildren().add(stack);
        // stack.setVisible(true);
        //
        // new Thread(this.counter).start();

    }

    /**
     * On play clicked.
     * @param actionEvent
     *            the action event
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @FXML
    public void onPlayClicked(final ActionEvent actionEvent) throws IOException {
        System.out.println("testin events");
        this.progress.setVisible(true);
    }

}
