package com.api.annualreportmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AnnualReportMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnualReportMgmtApplication.class, args);
	}

}
