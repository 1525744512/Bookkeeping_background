package com.bookkeeping.app.Utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000);
        factory.setReadTimeout(10000);

        RestTemplate restTemplate = new RestTemplateBuilder().build();
        restTemplate.getMessageConverters().stream().filter(m -> m instanceof MappingJackson2HttpMessageConverter).findAny().ifPresent(c -> ((MappingJackson2HttpMessageConverter) c).setSupportedMediaTypes(Stream.concat(c.getSupportedMediaTypes().stream(), Stream.of(MediaType.TEXT_HTML)).collect(Collectors.toList())));

        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }
}
