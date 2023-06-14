package com.chandan.hibernateentitymappings.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private Map<String, String> dbValues;
    private List<Map<String, String>> rabbitmq;

    public void iterateDbValues() {
        rabbitmq.forEach(map ->
                {
                    Set<Map.Entry<String, String>> entries = map.entrySet();
                    entries.forEach(entry -> System.out.println(entry.getKey() + "=" + entry.getValue()));
                }
        );
    }
}
