package com.springboot.yoeli.CONFIG;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/")
public class RestEasyConfig extends Application{

}
