package com.xing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class CommonConfig {

    /**
     * protibuf 序列化
     * @return
     */

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter(){
        return new ProtobufHttpMessageConverter();
    }

    /**
     *  protobuf反序列化
     * @param converter
     * @return
     */
    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter converter) {
        return new RestTemplate(Collections.singletonList(converter));
    }





}
