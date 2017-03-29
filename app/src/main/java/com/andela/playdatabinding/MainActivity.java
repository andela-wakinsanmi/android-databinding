package com.andela.playdatabinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.andela.playdatabinding.databinding.ActivityMainBinding;
import com.andela.playdatabinding.model.User;

public class MainActivity extends AppCompatActivity {
    private User user;
    private ActivityMainBinding binding;
    private boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("Waleola", "Akinsanmi");
        binding.setUser(user);
        binding.setMyHandlers(this);
    }

    public void onClickOfButton(View v) {
        Log.d("waleola", "Clicked the button " /*+ u.firstName + " : " + u.lastName*/);

        if (toggle) {
            user = new User("newName", "new Password");
            toggle = false;
        } else {
            user = new User("Waleola", "Akinsanmi");
            toggle = true;
        }
        binding.setUser(user);
    }
}
