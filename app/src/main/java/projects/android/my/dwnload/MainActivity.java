package projects.android.my.dwnload;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadFiles().execute("http://images.all-free-download.com/images/graphiclarge/harry_potter_icon_6825007.jpg","https://maxcdn.icons8.com/Share/icon/color/Cinema//sherlock_holmes1600.png");
    }

    class DownloadFiles extends AsyncTask{

        private ProgressDialog pDialog;
        OutputStream output;
        Bitmap img;
        int count=0;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

        }


        @Override
        protected Object doInBackground(Object[] params)
        {

            for(int i=0;i<params.length;i++)
            {
                try
                {
                    URL url = new URL(params[i].toString());
                    URLConnection conection = url.openConnection();
                    conection.connect();
                    InputStream input = conection.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                    count++;
                }
                catch (Exception e)
                {

                }


            }
            return img;
        }

        @Override
        protected void onPostExecute(Object o)
        {
            super.onPostExecute(o);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView1);
           // ImageView imageView3 = (ImageView) findViewById(R.id.imageView2);

            switch (count)
            {
                case 1: imageView.setImageBitmap((Bitmap) o);
                    break;
                case 2:imageView2.setImageBitmap((Bitmap) o);
                    break;

            }

        }
    }
}
