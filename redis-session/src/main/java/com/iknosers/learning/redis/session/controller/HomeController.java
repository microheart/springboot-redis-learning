package com.iknosers.learning.redis.session.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping("/set")
    public String set(HttpServletRequest request,
                                   @RequestParam("key") String value) {

        request.getSession().setAttribute("key", value);
        return String.format("%s->%s", "key", value);
    }

    @RequestMapping("/get")
    public Map<String, Object> get(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        map.put("key", request.getSession().getAttribute("key"));

        return map;
    }

}
