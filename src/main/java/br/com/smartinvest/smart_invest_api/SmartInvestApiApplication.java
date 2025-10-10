package br.com.smartinvest.smart_invest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SmartInvestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartInvestApiApplication.class, args);
	}
}
