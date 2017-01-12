package com.mongodb.pc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by Srilatha on 1/11/2017.
 */
public class HelloWorldSparkFreemarker {
    public static void main(String... args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter helloWriter = null;
                try {
                    Template template = config.getTemplate("hello.ftl");
                    helloWriter = new StringWriter();
                    Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name","Freemarker");
                    template.process(helloMap, helloWriter);
                    System.out.println(helloWriter);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return helloWriter;
            }
        });
    }
}
