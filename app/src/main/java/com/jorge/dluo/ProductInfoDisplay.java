package com.jorge.dluo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.jorge.dluo.network.INetworkResponseHandler;
import com.jorge.dluo.network.Network;

import org.sufficientlysecure.htmltextview.HtmlTextView;

public class ProductInfoDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_display);

        System.out.println("\n\n\nOPENED HTML VIEW\n\n\n");
        System.out.println(getIntent().getStringExtra("PRODUCT_CODE"));

        displayHtml(
                "<h2>An Unordered HTML List</h2>\n" +
                "<ul>\n" +
                "  <li>Coffee</li>\n" +
                "  <li>Tea</li>\n" +
                "  <li>Milk</li>\n" +
                "</ul>");

        new Network(this).getProductPage("5601029004014", this::displayHtml);
    }

    public void displayHtml(String htmlText) {
        ((HtmlTextView) findViewById(R.id.html_display)).setHtml(htmlText);
    }
}
