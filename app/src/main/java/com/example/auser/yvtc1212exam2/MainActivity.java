package com.example.auser.yvtc1212exam2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        imageView.setImageBitmap(image);
    }



    public void chat(View v) {
        intent = new Intent(MainActivity.this,MyChat.class);
        startActivity(intent);
    }


    public void top10(View v) {
        intent = new Intent(MainActivity.this,MapsActivity.class);
        startActivity(intent);
    }
}
