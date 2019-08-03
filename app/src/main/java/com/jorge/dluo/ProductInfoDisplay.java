package com.jorge.dluo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.dluo.network.Network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ProductInfoDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_display);

        System.out.println("\n\n\nOPENED HTML VIEW\n\n\n");
        String readCode = getIntent().getStringExtra("PRODUCT_CODE");

        new Network(this).getProductPage(readCode, this::displayInfo);
    }

    public void displayInfo(String rawResponse) {

        //Title
        String title = rawResponse.substring(rawResponse.indexOf("<title>")+7, rawResponse.indexOf("</title>"));
        ((TextView) findViewById(R.id.title)).setText(title);

        //Image
        new Thread(() -> {
            try {
                String temp = rawResponse.substring(rawResponse.indexOf("<meta property=\"og:image\" content=\"")+35);
                String url = temp.substring(0, temp.indexOf("\">"));
                Bitmap image = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
                runOnUiThread(() -> ((ImageView) findViewById(R.id.image)).setImageBitmap(image));
            } catch (Exception e) { e.printStackTrace(); }
        }).start();
    }
}
