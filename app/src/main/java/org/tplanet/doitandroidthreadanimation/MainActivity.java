package org.tplanet.doitandroidthreadanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ArrayList<Drawable> drawableArrayList = new ArrayList<Drawable>();

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        startAnimation();
    }

    public void startAnimation() {

        Resources res = getResources();
        drawableArrayList.add(res.getDrawable(R.drawable.laughing));
        drawableArrayList.add(res.getDrawable(R.drawable.crying));
        drawableArrayList.add(res.getDrawable(R.drawable.happy));
        drawableArrayList.add(res.getDrawable(R.drawable.surprised));
        drawableArrayList.add(res.getDrawable(R.drawable.sad));

        AnimThread thread = new AnimThread();
        thread.start();
    }

    class AnimThread extends Thread{
        @Override
        public void run() {
            int index = 0;
            for (int i = 0; i < 100; i++) {
                final Drawable drawable = drawableArrayList.get(index);
                index+=1;
                if(index>4){
                    index =0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
