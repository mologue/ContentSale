package com.mologue.contentSale;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.mologue.contentSale.dao")
public class ContentSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentSaleApplication.class, args);
	}
}
