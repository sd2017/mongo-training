package com.unmesh;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

/**
 * Created by upgundecha on 06/08/16.
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        try {

            Map<String, Object> fruitMap = new HashMap<String, Object>();
            fruitMap.put("fruits",
                    Arrays.asList("apple", "orange", "banana", "peach"));

            Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
            StringWriter writer = new StringWriter();
            fruitPickerTemplate.process(fruitMap, writer);
            get("/", (req, res) -> writer);

        } catch (Exception e) {
            halt(500);
            e.printStackTrace();
        }

        post("/favorite_fruit", (req, res) -> {
            final String fruit = req.queryParams("fruit");
            if(fruit == null ) {
                return "Why don't you pick one";
            } else {
                return "Your Favorite fruit is: " + fruit;
            }
        });
    }
}
