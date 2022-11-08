package com.hc.boot.demo;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author wangxiaolei
 * @since 2022/10/27 10:59
 */
@RestController
public class DemoAction {

    @RequestMapping(value = "/")
    public String root() {
        return "root";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "ok";
    }

    @RequestMapping(value = "/bmsLocation/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public String bmsLocation_getAll() throws IOException {
        File file = ResourceUtils.getFile("classpath:response-deptList.json");
        String s = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return s;
    }

    @RequestMapping(value = "/bmsUser/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public String bmsUser_getAll() throws IOException {
        File file = ResourceUtils.getFile("classpath:response-userList.json");
        String s = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return s;
    }

    @RequestMapping("/bmsUser/userAuth")
    public String bmsUser_userAuth() {
        return null;
    }

    public static void main(String[] args) {
        long t1 = System.nanoTime();
        int n = 0;
        for (int i = 0; i < 1000000000L; i++) {
            n++;
        }
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
    }
}
