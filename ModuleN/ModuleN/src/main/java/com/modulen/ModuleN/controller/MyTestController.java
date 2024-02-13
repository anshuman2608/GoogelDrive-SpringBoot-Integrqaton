package com.modulen.ModuleN.controller;


import com.modulen.ModuleN.model.TestObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MyTestController {

//    @Autowired
//    TestObj test;

    @GetMapping("/test1")
    public String test1(){

        return "I am trying to work";
    }

    @GetMapping("/test2")
    public TestObj test2(){

        TestObj test=new TestObj("Anshuman","8700840555","asingh851127@gmail.com","Delhi");



        return test;
    }
}
