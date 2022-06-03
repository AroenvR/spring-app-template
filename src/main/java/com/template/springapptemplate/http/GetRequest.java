package com.template.springapptemplate.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

// TODO: ADAPT THIS TO WHAT YOU NEED
public class GetRequest {

    public static void getDataFor(String crypto) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                String.format("https://api.coingecko.com/api/v3/coins/%s/market_chart?vs_currency=usd&days=365&interval=daily", crypto)
        )).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(resp -> parse(resp, crypto))
                .join();
    }

    private static String parse(String responseBody, String crypto) {
        JSONObject apiResponse = new JSONObject(responseBody);

        JSONArray prices = apiResponse.getJSONArray("prices"); // time, price
        JSONArray marketCap = apiResponse.getJSONArray("market_caps"); // time, marketCap
        JSONArray volume = apiResponse.getJSONArray("total_volumes"); // time, totalVolume

        for (int i = 0; i < prices.length(); i++) {

        }

        JSONArray arr = prices.getJSONArray(0);
        for (int i = 0; i < arr.length(); i++) {
            long que = arr.getLong(i);
            if (i == 0) {
                Date date = new Date(que);
                System.out.println("Time: " + date);
            } else if (i == 1) {
                System.out.println("Price: " + que);
            }
        }
        return null;
    }

}
