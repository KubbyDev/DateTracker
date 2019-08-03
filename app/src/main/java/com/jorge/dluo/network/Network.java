package com.jorge.dluo.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Network {

    private android.content.Context context;

    public Network(android.content.Context context) {
        this.context = context;
    }

    public void getProductPage(String code, INetworkResponseHandler responseHandler) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://fr.openfoodfacts.org/produit/" + code + "/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                responseHandler::onProductPageReceived,
                error -> System.out.println("\nError" + error.getMessage())
        );

        System.out.println("Sending Http Request");

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
