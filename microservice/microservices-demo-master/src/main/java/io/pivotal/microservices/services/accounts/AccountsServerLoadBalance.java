package io.pivotal.microservices.services.accounts;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import io.pivotal.microservices.accounts.AccountRepository;
import io.pivotal.microservices.accounts.AccountsConfiguration;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AccountsConfiguration.class)
public class AccountsServerLoadBalance {
	@Autowired
	protected AccountRepository accountRepository;

	protected static Logger logger = Logger.getLogger(AccountsServerLoadBalance.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		
		logger.info("===AccountsServerLoadBalance===");
		
		System.setProperty("spring.config.name", "accounts-server-load-balance");

		SpringApplication.run(AccountsServerLoadBalance.class, args);
	}
}
