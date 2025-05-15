package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import java.util.Locale;

import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alawiyaa.rest.webservice.restful_web_services.usecase.HellowWorldBean;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String hellowWordString() {
        return "hello wordl";
    }

    @GetMapping(path = "/hello-world-bean")
    public HellowWorldBean hellowWordBean() {
        return new HellowWorldBean("hello-world");
    }

    @GetMapping("hello/{name}")
    public String helloPath(@PathVariable String name) {

        return String.format("Hellow %s", name);
    }

    @GetMapping(path = "/hello-world-int")
    public String hellowWordInternationalize() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.messgae", null, "Default Message", locale);
    }

}
