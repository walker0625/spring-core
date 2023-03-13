package com.jon.springcore.web;

import com.jon.springcore.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogDemoService {

    //private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    private final MyLogger myLogger;

    public void logic(String id) {
        //MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log(id);
    }

}
