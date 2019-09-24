package com.ningxia.ensure.controller;

import com.ningxia.ensure.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/hello")
    public Object hello() {
        List<Map<String, Object>> maps = null;
        try {
            maps = testService.basicInfo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }
}
