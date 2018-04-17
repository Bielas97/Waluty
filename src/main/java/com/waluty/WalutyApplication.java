package com.waluty;

import com.waluty.parser.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalutyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalutyApplication.class, args);
		Table table = new Table();
	}
}
