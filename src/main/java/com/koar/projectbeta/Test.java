package com.koar.projectbeta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class Test {


    @Autowired
    MessageSource messageSource;

    @RequestMapping("/hello-world")
    public String test(){
        return messageSource.getMessage("errors.notfound.page", null, LocaleContextHolder.getLocale());
    }
}
