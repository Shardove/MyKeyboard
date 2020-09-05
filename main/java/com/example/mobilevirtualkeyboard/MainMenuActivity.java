package com.example.mobilevirtualkeyboard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract.Constants;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity
{
    private static final int MY_PERMISSION_REQUEST_BIND_INPUT_METHOD = 99;
    TextView activatingKeyboard;
    private boolean isKeyboardEnabled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        activatingKeyboard = findViewById(R.id.txt_active_kb);

        activatingKeyboard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkingPermission();
            }
        });
    }

    public void checkingPermission()
    {
        Intent dialogIntent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);
    }

}

