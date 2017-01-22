package com.mongodb.pc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.halt;

/**
 * Created by Srilatha on 1/11/2017.
 */
public class SparkRoutes {
    public static void main(String... args){
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(SparkRoutes.class, "/");
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = null;
                try {
                    Map<String, Object> fruitMap = new HashMap<>();
                    fruitMap.put("fruits", Arrays.asList("apple", "blackberry", "apricot", "plum"));
                    writer = new StringWriter();
                    Template template = config.getTemplate("fruitPicker.ftl");
                    template.process(fruitMap, writer);
                } catch(Exception e) {
                    halt(500);
                    return null;
                }
                System.out.println(writer);
                return writer;
            }
        });
        Spark.post("/favorite_fruit", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String fruit = request.queryParams("fruit");
                if(fruit == null) {
                    return "why dont you pick one";
                }
                else {
                    return "your favorite fruit is " + fruit;
                }
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
