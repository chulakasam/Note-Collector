package lk.ijse.notecollector.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse")
@EnableWebMvc
@MultipartConfig(
                fileSizeThreshold = 1,
                maxFileSize = 1024*1024*6,
                maxRequestSize = 1024*1024*10
                )
public class WebAppConfig {

}
