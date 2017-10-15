package org.vini.diff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application.
 * This is a REST API to perform diff operations.
 * 
 * @author Vinicius Alves
 */
@SpringBootApplication()
public class DiffApiApp {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(DiffApiApp.class);
		logger.info("Initializing Diff API.");
		SpringApplication.run(DiffApiApp.class, args);

	}

}
