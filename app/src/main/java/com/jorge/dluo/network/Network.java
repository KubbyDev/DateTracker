package com.jorge.dluo.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Network {

    private android.content.Context context;

    public Network(android.content.Context context) { this.context = context; }

    /** Sends an HTTP request to openfoodfacts with the provided barcode
     * <br> When the response is received, calls the
     * callback (responseHandler) with the response as parameter */
    public void getProductPage(String code, INetworkResponseHandler responseHandler) {

        RequestQueue queue = Volley.newRequestQueue(context);
        //Constructs the URL of the request
        String url = "https://fr.openfoodfacts.org/produit/" + code + "/";

        //Sets the handlers in case of success and in case of error
        //In case of success, calls the callback, in case of error, prints the error message
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                responseHandler::onProductPageReceived,
                error -> System.out.println("\nError" + error.getMessage())
        );

        System.out.println("Sending Http Request");

        //Adds the request to the queue (will be sent by volley)
        queue.add(stringRequest);
    }
}
