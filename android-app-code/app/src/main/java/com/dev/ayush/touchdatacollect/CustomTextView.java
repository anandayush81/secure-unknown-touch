package com.dev.ayush.touchdatacollect;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView
{

    public CustomTextView(Context context)
    {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);
        String event_str[]=event.toString().split("\\{");
        String event_str_final[]=event_str[1].split("\\}");
        float pressure=event.getPressure();
        float eventTime = event.getEventTime();
        float downTime= event.getDownTime();



        try
        {
            exportTouchEventCSV(eventTime+","+downTime+","+pressure+
                                                        ","+event.getTouchMajor()+
                                                                ","+event.getTouchMinor()+","+event.getSize());
        } catch (IOException e)
        {
            Log.d("Write CSV:",""+e);
            e.printStackTrace();
        }

        Toast.makeText(getContext(),""+event_str_final[0],Toast.LENGTH_LONG).show();


        performClick();

        return true;
    }

    @Override
    public boolean performClick()
    {
        return super.performClick();


    }

    public void exportTouchEventCSV(final String event) throws IOException
    {


            File folder = new File(Environment.getExternalStorageDirectory()
                    + "/TouchData");
           /* if (folder==null)
            {
               throw NullPointerException ;
            }*/

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

                        FileWriter fw = new FileWriter(filename,true);


                        fw.append(event);

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



    }
}
