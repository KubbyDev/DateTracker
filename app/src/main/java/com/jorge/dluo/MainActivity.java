package com.jorge.dluo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jorge.dluo.barcodescan.BarcodeScanner;
import com.jorge.dluo.database.Database;

/** This activity is the main on. It starts when the application starts
 * <br> Its goal is to start the barcode scan and launch the ProductInfoDisplay activity
 * when the scan is done */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Displays res/layout/activity_main.xml

        /* Tests SQL request
        Database.connect();
        Database.sendRequest("SELECT test FROM test");
        */

        /* Skips barcode reading (uses a hardcoded barcode)
        Intent intent = new Intent(MainActivity.this, ProductInfoDisplay.class);
        intent.putExtra("PRODUCT_CODE", "5601029004014");
        startActivity(intent);
        */

        //Creates a BarcodeScanner and scans
        new BarcodeScanner(this).scanBarcode(result -> {
            //This is executed when the scan is done
            Intent intent = new Intent(MainActivity.this, ProductInfoDisplay.class);
            intent.putExtra("PRODUCT_CODE", result); //Puts the result of the scan on a global variable
            startActivity(intent); //Starts the ProductInfoDisplay activity
        });
    }
}