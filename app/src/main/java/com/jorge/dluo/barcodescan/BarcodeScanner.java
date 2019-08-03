package com.jorge.dluo.barcodescan;

import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
import com.jorge.dluo.R;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class BarcodeScanner implements BarcodeRetriever {

    private IBarcodeScanResultHandler onBarcodeScannedHandler;
    private AppCompatActivity activity;

    public BarcodeScanner(AppCompatActivity fromActivity) {
        activity = fromActivity;
    }

    public void scanBarcode(IBarcodeScanResultHandler onBarcodeScanned) {

        onBarcodeScannedHandler = onBarcodeScanned;

        BarcodeCapture capture = (BarcodeCapture) activity.getSupportFragmentManager().findFragmentById(R.id.barcode);
        capture.setRetrieval(this);
    }

    @Override
    public void onRetrieved(final Barcode barcode) {
        onBarcodeScannedHandler.onBarcodeScanned(barcode.displayValue);
    }

    @Override
    public void onRetrievedMultiple(Barcode closetToClick, List<BarcodeGraphic> barcode) {
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
    }

    @Override
    public void onRetrievedFailed(String reason) {
    }

    @Override
    public void onPermissionRequestDenied() {
    }
}
