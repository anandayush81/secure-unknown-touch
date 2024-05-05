package com.dev.ayush.touchdatacollect;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileWriter;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void selectMode(View view)
    {
        if (view.getId()==R.id.splash_button_training)
        {
            final String headers = "event_time,down_time,pressure,major_axis,minor_axis,size";
            File folder = new File(Environment.getExternalStorageDirectory()
                    + "/TouchData");

            //folder.createNewFile();

            boolean var = false;
            if (!folder.exists())
            {
                var = folder.mkdirs();
                //var= folder.createNewFile();
            }


            System.out.println("" + var);




            final String filename = Environment.getExternalStorageDirectory()+"/TouchData/SingleTapData.csv";


            new Thread()
            {
                public void run()
                {
                    try
                    {

                        FileWriter fw = new FileWriter(filename,false);


                        fw.append(headers);

                        fw.append('\n');


                        // fw.flush();
                        fw.close();

                    }
                    catch (Exception e)
                    {
                        Log.d("Write CSV file: ",""+e);
                    }

                }
            }.start();
            Intent in = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(in);
        }
        else if (view.getId()==R.id.splash_button_testing)
        {
            Intent in = new Intent(SplashActivity.this,TestingActivity.class);
            startActivity(in);
        }

    }
}
