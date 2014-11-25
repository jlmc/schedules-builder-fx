package org.xine.schedules.builder.fx.backoffice.subject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;

import org.xine.fx.guice.FXMLComponent;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.schedules.builder.fx.backoffice.BackofficeContentController;
import org.xine.schedules.builder.fx.backoffice.BackofficeModel;
import org.xine.schedules.builder.fx.backoffice.BackofficeViews;
import org.xine.schedules.builder.fx.backoffice.Status;
import org.xine.schedules.builder.fx.gui.ApplicationController;
import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.ContentDecorated;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectAnchorPane.
 */

@FXMLComponent(resources = "org.xine.schedules.builder.fx.backoffice.subject.SubjectAnchorPane")
public class SubjectAnchorPane extends AnchorPane implements ContentDecorated {

    /*
     * **************************************************************************
     * *
     * Instance Variables *
     * *
     * ************************************************************************
     */
    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The navigation button. */
    private Button navigationButton = null;

    /** The application controller. */
    private ApplicationController applicationController = null;

    /** The name. */
    private final StringProperty name = new SimpleStringProperty("<name>");

    @Inject
    private BackofficeModel<Subject> model;

    /** The sub controllers. */
    private final LinkedHashMap<Status, BackofficeContentController> controllers = new LinkedHashMap<>();

    /** The views. */
    private final Map<Status, String> views = Collections.unmodifiableMap(new LinkedHashMap<Status, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(Status.LIST, BackofficeViews.SUBJECTS_LIST);
            put(Status.CREATE, BackofficeViews.SUBJECTS_CREATE);
            put(Status.EDIT, BackofficeViews.SUBJECTS_EDIT);
        }
    });

    /** The active controller. */
    private ContentController activeController;

    /*
     * *************************************************************************
     * *
     * Constructors *
     * *
     * ************************************************************************.
     */

    /**
     * Instantiates a new subject anchor pane.
     */
    public SubjectAnchorPane() {
        super();
        setName("Subjects");
    }

    /**
     * Instantiates a new subject anchor pane.
     * @param name
     *            the name
     * @param applicationController
     *            the application controller
     */
    public SubjectAnchorPane(final String name, final ApplicationController applicationController) {
        super();
        setName(name);
        setApplicationController(applicationController);
    }

    /**
     * Instantiates a new subject anchor pane.
     * @param children
     *            the children
     */
    public SubjectAnchorPane(final Node... children) {
        super(children);
    }

    /*
     * *************************************************************************
     * *
     * Properties *
     * *
     * *************************************************************************
     */

    /*
     * *************************************************************************
     * *
     * Methods public *
     * *
     * *************************************************************************
     */

    /**
     * Initialize.
     */

    @FXML
    public void initialize() {
        // TODO:: considering the use of a Task to do loader component of

        loadSubControllers();

        // activateController(Status.LIST);
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentDecorated#getName()
     */
    @Override
    public String getName() {
        return this.name.get();
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentDecorated#setName(java.lang.String)
     */
    @Override
    public void setName(final String name) {
        this.name.set(name);

    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentDecorated#getNavigationButton()
     */
    @Override
    public Button getNavigationButton() {
        return this.navigationButton;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentDecorated#setNavigationButton(javafx.scene.control.Button)
     */
    @Override
    public void setNavigationButton(final Button activationButton) {
        this.navigationButton = activationButton;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentDecorated#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentDecorated#setApplicationController(org.xine.schedules.builder.fx.gui.ApplicationController)
     */
    @Override
    public void setApplicationController(final ApplicationController applicationController) {
        this.applicationController = applicationController;

    }

    /*
     * *************************************************************************
     * *
     * Methods private *
     * *
     * *************************************************************************
     */

    /**
     * Load sub controllers.
     */
    private void loadSubControllers() {

        for (final Status status : this.views.keySet()) {
            loadSubController(status, this.views.get(status));
        }

        if (this.controllers != null && !this.controllers.isEmpty()) {
            final Set<Entry<Status, BackofficeContentController>> mapValues = this.controllers.entrySet();
            @SuppressWarnings("unchecked")
            final Entry<Status, BackofficeContentController>[] array = new Entry[mapValues.size()];
            mapValues.toArray(array);
            if (array[0] != null) {

                // final Status state = array[0].getKey();
                final ContentController controller = array[0].getValue();
                // activateController(array[0].getValue());

                activateController(controller);
            }

        }
    }

    /**
     * Load sub controllers.
     * @param state
     *            the state
     * @param subview
     *            the views
     */
    private void loadSubController(final Status state, final String subview) {
        if (state != null && subview != null) {

            final BackofficeContentController<Subject> controller = getSubController(subview);
            // controller.setStateMachine(this);

            controller.setContentDecorated(this);
            controller.setModel(this.model);

            this.controllers.put(state, controller);
            controller.setApplicationController(this.applicationController);

            // no need create the navigation button, because this is a machine State
            // addControllerButton(controller);

            getChildren().add(controller.getRootNode());

            controller.setControllerConstrains();
            controller.getRootNode().setVisible(false);
        }
    }

    /**
     * Gets the sub controller.
     * @param <N>
     *            the number type
     * @param view
     *            the view
     * @return the sub controller
     */
    @SuppressWarnings("unchecked")
    private <N> N getSubController(final String view) {
        try {
            final Result loadResult = this.fxmlLoader.load(SubjectAnchorPane.class.getResource(view));
            return (N) loadResult.getController();
        } catch (final Exception e) {
            throw new RuntimeException("Could not load sub view: " + view, e);
        }

    }

    /**
     * Activate controller.
     * @param status
     *            the state
     * @param controller
     *            the controller
     */
    private void activateController(final ContentController controller) {
        if (controller == null || controller == this.activeController) {
            return;
        }

        final ContentController oldActiveController = this.activeController;
        controller.getRootNode().setVisible(true);

        if (oldActiveController != null) {
            oldActiveController.onDeactivate();
            oldActiveController.getRootNode().setVisible(false);
        }

        this.activeController = controller;
        this.activeController.onActivate();
    }

    @Override
    public void changeStatus(final Status status) {
        final BackofficeContentController contentController = this.controllers.get(status);
        if (contentController != null) {
            activateController(contentController);
        }
    }

}
