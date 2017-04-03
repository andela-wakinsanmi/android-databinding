package com.andela.playdatabinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.layer.sdk.LayerClient;
import com.layer.sdk.exceptions.LayerException;
import com.layer.sdk.listeners.LayerAuthenticationListener;
import com.layer.sdk.listeners.LayerConnectionListener;

public class ChatActivity extends AppCompatActivity implements LayerConnectionListener, LayerAuthenticationListener {

    private LayerClient layerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layerClient = ((PlayDataApp) getApplicationContext()).getLayerClient();
        layerClient.registerConnectionListener(this);
        layerClient.registerAuthenticationListener(this);
        layerClient.connect();
    }

    @Override
    public void onAuthenticated(LayerClient layerClient, String s) {
        Log.d("waleola", "onAuthenticated");
    }

    @Override
    public void onDeauthenticated(LayerClient layerClient) {
        Log.d("waleola", "onDeauthenticated");
    }

    @Override
    public void onAuthenticationChallenge(LayerClient layerClient, String s) {
        Log.d("waleola", "onAuthenticationChallenge");
    }

    @Override
    public void onAuthenticationError(LayerClient layerClient, LayerException e) {
        Log.d("waleola", "onAuthenticationError");
    }

    @Override
    public void onConnectionConnected(LayerClient layerClient) {
        Log.d("waleola", "onConnectionConnected");
    }

    @Override
    public void onConnectionDisconnected(LayerClient layerClient) {
        Log.d("waleola", "onConnectionDisconnected");
    }

    @Override
    public void onConnectionError(LayerClient layerClient, LayerException e) {
        Log.d("waleola", "onConnectionError");
    }
}
