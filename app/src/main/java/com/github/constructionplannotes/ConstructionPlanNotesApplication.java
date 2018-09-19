package com.github.constructionplannotes;

import android.app.Application;
import io.realm.Realm;

public class ConstructionPlanNotesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
