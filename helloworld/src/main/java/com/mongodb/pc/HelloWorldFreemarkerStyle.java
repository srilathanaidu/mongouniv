package com.mongodb.pc;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Srilatha on 1/11/2017.
 */
public class HelloWorldFreemarkerStyle {
    public static void main(String... args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
        try {
            Template template = config.getTemplate("hello.ftl");
            StringWriter helloWriter = new StringWriter();
            Map<String, Object> helloMap = new HashMap<>();
            helloMap.put("name","Freemarker");
            template.process(helloMap, helloWriter);
            System.out.println(helloWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
