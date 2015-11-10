package org.xine.qtime.client.fx.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class User {

    private final SimpleStringProperty username = new SimpleStringProperty(null);

    private final SimpleBooleanProperty loggedIn = new SimpleBooleanProperty(false);

    private final SimpleObjectProperty<Image> avatar = new SimpleObjectProperty<Image>(null);


    public final SimpleStringProperty usernameProperty() {
        return this.username;
    }

    public final java.lang.String getUsername() {
        return usernameProperty().get();
    }

    public final void setUsername(final java.lang.String username) {
        usernameProperty().set(username);
    }

    public final SimpleBooleanProperty loggedInProperty() {
        return this.loggedIn;
    }

    public final boolean isLoggedIn() {
        return loggedInProperty().get();
    }

    public final void setLoggedIn(final boolean loggedIn) {
        loggedInProperty().set(loggedIn);
    }

    public final SimpleObjectProperty<Image> avatarProperty() {
        return this.avatar;
    }

    public final javafx.scene.image.Image getAvatar() {
        return avatarProperty().get();
    }

    public final void setAvatar(final javafx.scene.image.Image avatar) {
        avatarProperty().set(avatar);
    }
}
