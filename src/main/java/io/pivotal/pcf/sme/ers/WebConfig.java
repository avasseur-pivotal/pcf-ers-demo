package io.pivotal.pcf.sme.ers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(
                        "/webjars/**",
                        "/images/**",
                        "/tutorial/**"
                        )
                .addResourceLocations(
                        "/webjars/",
                        "classpath:/static/images/",
                        "classpath:/public/tutorial/"
                        )
                .resourceChain(false);
    }
}