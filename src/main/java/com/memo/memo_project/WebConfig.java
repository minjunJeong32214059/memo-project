package com.memo.memo_project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 브라우저가 /files/** 로 접근하면
       	// 도커 내부의 /memo_files/ 폴더를 바라보도록
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:/memo_files/");
    }
}