package org.xine.schedules.builder.fx.backoffice;

import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.ContentDecorated;

/**
 * The Class BackofficeContentController.
 */
public abstract class BackofficeContentController<T> extends ContentController {

    /** The macro. */
    private ContentDecorated contentDecorated;

    private BackofficeModel<T> model;

    public BackofficeModel<T> getModel() {
        return this.model;
    }

    public void setModel(final BackofficeModel<T> model) {
        this.model = model;
    }

    public ContentDecorated getContentDecorated() {
        return this.contentDecorated;
    }

    public void setContentDecorated(final ContentDecorated contentDecorated) {
        this.contentDecorated = contentDecorated;
    }

}
