package com.jon.springcore.web;

import com.jon.springcore.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    private final MyLogger myLogger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        //MyLogger myLogger = myLoggerObjectProvider.getObject();
        String requestUrl = request.getRequestURL().toString();
        myLogger.setRequestUrl(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }

}
