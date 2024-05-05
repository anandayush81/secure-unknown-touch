package com.dev.ayush.touchdatacollect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    private CustomTextView touchData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        touchData= (CustomTextView) findViewById(R.id.main_touch_data);

        isWriteStoragePermissionGranted();



    }

    public void selectMode(View view)
    {
        if (view.getId()==R.id.splash_button_training)
        {
            Intent in = new Intent(MainActivity.this,SplashActivity.class);
            startActivity(in);
        }


    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {

            case 2:

                if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"Now Perform Tap to register!",Toast.LENGTH_LONG).show();

                    Log.v("Write_permission","Permission: "+permissions[0]+ "was "+grantResults[0]);
                    //resume tasks needing this permission

                }
                else
                {
                    Toast.makeText(this,"Grant Permission to Download.",Toast.LENGTH_LONG).show();


                }
                break;
        }

    }

    public  boolean isWriteStoragePermissionGranted()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)
            {
                Log.v("Permission_write:","Permission is granted");
                return true;
            }
            else
                {

                Log.v("Permission_write:","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else
            { //permission is automatically granted on sdk<23 upon installation
                Log.v("Permission_write:","Permission is granted");
                return true;
        }
    }


    public void startTraining(View view)
    {

            if (view.getId()==R.id.training_button_finish)
            {
                Intent in = new Intent(MainActivity.this,SplashActivity.class);
                startActivity(in);
                finish();
            }


    }
}
