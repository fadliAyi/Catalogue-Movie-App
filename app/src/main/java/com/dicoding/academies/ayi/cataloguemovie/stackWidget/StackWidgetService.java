package com.dicoding.academies.ayi.cataloguemovie.stackWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by ayi on 11/01/18.
 */

public class StackWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
