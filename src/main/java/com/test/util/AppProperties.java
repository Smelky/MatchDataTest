package com.test.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.props")
@Data
public class AppProperties {
    private String fileName;
}
