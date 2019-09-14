package com.jorge.dluo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.dluo.network.Network;

import java.io.InputStream;
import java.net.URL;

/** This activity's goal is to display the information
 * about the product that has just been scanned */
public class ProductInfoDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_display); //Displays res/layout/activity_product_info_display.xml

        System.out.println("\n\n\nOPENED HTML VIEW\n\n\n");

        //Gets the scanned barcode
        String readCode = getIntent().getStringExtra("PRODUCT_CODE");

        //Sends an HTTP request to the openfoodfacts database with the barcode
        //The response is automatically put in parameter of displayInfo
        new Network(this).getProductPage(readCode, this::displayInfo);
    }

    /** Displays the information about a product.
     * The rawResponse should be the HTTP response from openfoodfacts */
    public void displayInfo(String rawResponse) {

        //Searches for the title in the response and displays it
        String title = rawResponse.substring(rawResponse.indexOf("<title>")+7, rawResponse.indexOf("</title>"));
        ((TextView) findViewById(R.id.title)).setText(title);

        //Searches for the URL of the image in the response and displays it
        //Creates a new process because downloading the image may take some time
        new Thread(() -> {
            try {
                //Searches for the URL
                String temp = rawResponse.substring(rawResponse.indexOf("<meta property=\"og:image\" content=\"")+35);
                String url = temp.substring(0, temp.indexOf("\">"));
                //Downloads the image
                Bitmap image = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
                //Displays the image
                runOnUiThread(() -> ((ImageView) findViewById(R.id.image)).setImageBitmap(image));
            } catch (Exception e) { e.printStackTrace(); }
        }).start();
    }
}
