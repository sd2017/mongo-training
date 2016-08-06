package com.unmesh;

import spark.Request;

import static spark.Spark.get;

/**
 * Created by upgundecha on 06/08/16.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello World from Spark");

        get("/test", (req, res) -> "This is test page" );
        get("/echo/:thing", (req, res) -> {
            return getThing(req);
        });
    }

    private static String getThing(Request request) {
        return request.params(":thing");
    }
}
