package com.crabar.web;

import com.alibaba.fastjson.JSON;
import com.crabar.common.controller.BaseController;
import com.crabar.service.demo.AnswerServiceImpl;
import com.crabar.service.demo.HelloServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;

/**
 * Created with IntelliJ IDEA.
 * User: wmh
 * Date: 14-7-29
 * Time: ÏÂÎç11:30
 */
@Before(IocInterceptor.class)
public class IndexController extends BaseController {

    @Inject.BY_NAME
    private HelloServiceImpl helloService;

    @Inject.BY_NAME
    private AnswerServiceImpl answerService;

    @ActionKey("/")
    public void index(){
        renderJson(helloService.sayHello());
    }

    @ActionKey("/answers")
    public void listAnswers(){
        renderJson(answerService.findAll());
    }
}
