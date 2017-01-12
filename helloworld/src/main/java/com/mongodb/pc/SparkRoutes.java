package com.mongodb.pc;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Srilatha on 1/11/2017.
 */
public class SparkRoutes {
    public static void main(String... args){
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "GET from root";
            }
        });
        Spark.get("/test", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "GET for test endpoint";
            }
        });
        Spark.get("/echo/:thing", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return request.params(":thing");
            }
        });
    }
}
