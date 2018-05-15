package com.chunhui.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chunhui.stock","com.chunhui.core.dto","com.chunhui.core.service"})
@MapperScan(basePackages = {"com.chunhui.core.dto","com.chunhui.core.dao"})
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}
}
