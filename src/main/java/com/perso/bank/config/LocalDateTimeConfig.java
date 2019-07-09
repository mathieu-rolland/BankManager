package com.perso.bank.config;

import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;

@Configuration
@Getter
public class LocalDateTimeConfig {

	private DateTimeFormatter dateTimeFormatter;
	
	@PostConstruct
	public void setup() {
		dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	}
	
	@Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.serializers(new LocalDateSerializer(dateTimeFormatter) );
            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
        };
    }
	
}
