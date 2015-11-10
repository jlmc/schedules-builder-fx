package org.xine.qtime.client.fx.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Discipline {

	private final LongProperty id = new SimpleLongProperty();

	private final StringProperty code = new SimpleStringProperty();

	private final StringProperty description = new SimpleStringProperty();

	public final LongProperty idProperty() {
		return this.id;
	}

	public final long getId() {
		return idProperty().get();
	}

	public final void setId(final long id) {
		idProperty().set(id);
	}

	public final StringProperty codeProperty() {
		return this.code;
	}

	public final java.lang.String getCode() {
		return codeProperty().get();
	}

	public final void setCode(final java.lang.String code) {
		codeProperty().set(code);
	}

	public final StringProperty descriptionProperty() {
		return this.description;
	}

	public final java.lang.String getDescription() {
		return descriptionProperty().get();
	}

	public final void setDescription(final java.lang.String description) {
		descriptionProperty().set(description);
	}

}
