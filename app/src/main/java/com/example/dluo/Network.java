package com.example.dluo;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Network {

    public static void test(android.content.Context context) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://fr.openfoodfacts.org/produit/5601029004014/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("\nResponse : " + response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("\nError" + error.getMessage());
                    }
                }
        );

        System.out.println("Sending Http Request");

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
