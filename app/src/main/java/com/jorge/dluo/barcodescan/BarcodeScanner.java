package com.jorge.dluo.barcodescan;

import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
import com.jorge.dluo.R;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

/** This class can read a barcode. Just create a new BarcodeScanner and call scanBarcode on it */
public class BarcodeScanner implements BarcodeRetriever {

    //The callback function that will be called when the barcode will be successfully read
    private IBarcodeScanResultHandler onBarcodeScannedHandler;
    //The activity that asked for a barcode scan
    private AppCompatActivity activity;

    public BarcodeScanner(AppCompatActivity fromActivity) { activity = fromActivity; }

    /** Opens the camera and starts searching for a barcode. When it finds one,
     * calls the callback function (onBarcodeScanned) */
    public void scanBarcode(IBarcodeScanResultHandler onBarcodeScanned) {

        //If this method is called a second time before the end of the first scan, throws an error
        if(onBarcodeScannedHandler != null)
            throw new RuntimeException("This barcode scanner is already scanning !");

        //Sets the callback function
        onBarcodeScannedHandler = onBarcodeScanned;

        //Starts the scan (onRetrieved will be called when the scan is done)
        BarcodeCapture capture = (BarcodeCapture) activity.getSupportFragmentManager().findFragmentById(R.id.barcode);
        capture.setRetrieval(this);
    }

    /** This function is called when the scanner successfully read a barcode */
    @Override
    public void onRetrieved(final Barcode barcode) {
        //Calls the callback function with the result of the scan
        onBarcodeScannedHandler.onBarcodeScanned(barcode.displayValue);
        //Sets the callback to null so the system doesn't throw any error if a second scan is attempted
        onBarcodeScannedHandler = null;
    }

    //These functions are not used for the moment, but they should be
    //(Or the app may crash on edge cases)
    @Override
    public void onRetrievedMultiple(Barcode closetToClick, List<BarcodeGraphic> barcode) {}
    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {}
    @Override
    public void onRetrievedFailed(String reason) {}
    @Override
    public void onPermissionRequestDenied() {}
}
