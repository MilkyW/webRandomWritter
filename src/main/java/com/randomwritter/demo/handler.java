package com.randomwritter.demo;

import com.wxr.RandomWritter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RestController

public class handler {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String say() {
        return "Hello World";
    }

    @RequestMapping("/RandomWritter")
    public @ResponseBody
    String rw(
            @RequestParam(name = "filename", required = false) String filename,
            @RequestParam(name = "n", required = false) Integer n,
            @RequestParam(name = "len", required = false) Integer len) {
        RandomWritter rm = new RandomWritter();
        String in = filename + '\n' + String.valueOf(n) + '\n'
                + String.valueOf(len) + '\n';
        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes());
        System.setIn(is);
        rm.out = "";
        rm.main();
        return rm.out+'\n';
    }
}
