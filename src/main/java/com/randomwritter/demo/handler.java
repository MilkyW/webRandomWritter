package com.randomwritter.demo;

import com.wxr.RandomWritter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RestController

public class handler {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String say() {
        return "try: http://localhost:8080/RandomWritter?filename=hamlet.txt&n=3&len=100";
    }

    @RequestMapping("/RandomWritter")
    public @ResponseBody
    String rw(
            @RequestParam(name = "filename", required = false, defaultValue = "") String filename,
            @RequestParam(name = "n", required = false, defaultValue = "") String n,
            @RequestParam(name = "len", required = false, defaultValue = "") String len) {
        Logger LOGGER = Logger.getLogger(RandomWritter.class);
        if (filename.equals(""))
            return "Error: please enter filename!\t" +
                    "e.g. filename = hamlet.txt\n";
        if (n.equals(""))
            return "Error: please enter n!\t" +
                    "e.g. n = 3\n";
        if (len.equals(""))
            return "Error: please enter len!\t" +
                    "e.g. len = 10\n";
        RandomWritter rm = new RandomWritter();
        String in = filename + '\n' + n + '\n'
                + len + '\n';
        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes());
        System.setIn(is);
        rm.out = "";
        try {
            rm.main();
        } catch (Exception e) {
            if (!rm.err.equals(""))
                return "Error: " + rm.err;
            return e.getMessage();
        }
        if (rm.out.equals("")) {
            if (!rm.err.equals(""))
                return "Error: " + rm.err;
            LOGGER.error("filename: " + filename + '\t'
                    + "N: " + n + '\t'
                    + "len: " + len + '\n');
            return "Error: " + "unknown error!";
        }
        return rm.out + '\n';
    }
}
