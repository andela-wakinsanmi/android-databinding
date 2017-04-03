package com.andela.playdatabinding;


import android.app.Application;

import com.layer.sdk.LayerClient;

public class PlayDataApp extends Application {
    private LayerClient layerClient;

    @Override
    public void onCreate() {
        super.onCreate();
        layerClient = LayerClient.newInstance(this, "layer:///apps/staging/c0b0af06-160c-11e7-8d0c-9f3cfad2e7f8",
                new LayerClient.Options().googleCloudMessagingSenderId("946056141954"));

        LayerClient.applicationCreated(this);

    }

    public LayerClient getLayerClient() {
        return layerClient;
    }
}
