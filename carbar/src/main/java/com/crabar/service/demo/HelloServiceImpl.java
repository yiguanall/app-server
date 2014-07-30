package com.crabar.service.demo;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: wmh
 * Date: 14-7-30
 * Time: ионГ12:00
 */
@Service("helloService")
public class HelloServiceImpl {

    public String sayHello(){
        return "hello world!";
    }
}
