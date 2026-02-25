package com.memo.memo_project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 브라우저가 /files/** 로 접근하면
        // 실제 내 컴퓨터의 C:/memo_files/ 폴더를 보여주도록
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///C:/memo_files/");
    }
}