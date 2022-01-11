package com.graduation.education;

import com.graduation.education.converter.String2Date;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2Date());
    }
}
