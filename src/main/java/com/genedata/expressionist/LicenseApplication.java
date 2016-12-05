package com.genedata.expressionist;

import com.genedata.expressionist.data.License;
import com.genedata.expressionist.data.LicenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LicenseApplication {

	@Bean
	public CommandLineRunner loadData(LicenseRepository repository) {
		return (args) -> {
			repository.save(new License("Roche", fromNow(200)));
			repository.save(new License("GSK", fromNow(300)));
			repository.save(new License("BAT", fromNow(100)));
			repository.save(new License("Novartis", fromNow(-10)));
		};
	}

// ------------------------- Private -------------------------

	private static Date fromNow(int offset) {
		return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(offset));
	}

// --------------------------- main() ---------------------------

	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}
}
