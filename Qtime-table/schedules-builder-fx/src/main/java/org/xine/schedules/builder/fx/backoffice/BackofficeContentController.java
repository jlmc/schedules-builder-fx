package org.xine.schedules.builder.fx.backoffice;

import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.ContentDecorated;

/**
 * The Class BackofficeContentController.
 */
public abstract class BackofficeContentController extends ContentController {

    /** The macro. */
    private ContentDecorated contentDecorated;

    public ContentDecorated getContentDecorated() {
        return this.contentDecorated;
    }

    public void setContentDecorated(final ContentDecorated contentDecorated) {
        this.contentDecorated = contentDecorated;
    }

}
