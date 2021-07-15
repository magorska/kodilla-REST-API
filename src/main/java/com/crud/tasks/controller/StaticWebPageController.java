package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

//    @RequestMapping("/")
//    public String index(Map<String, String> model) {
//        model.put("variable", "My Thymeleaf variable");
//        return "index";
//    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("multiply_operation", "2 * 2 = ");
        model.put("multi_add_operation", "2 * 2 + 2 = ");
        model.put("sub_multi_operation", "2 - (2 * 2) = ");
        return "index";
    }
}
