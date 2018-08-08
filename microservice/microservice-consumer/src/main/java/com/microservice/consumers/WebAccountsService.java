package com.microservice.consumers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.microservice.exceptions.AccountNotFoundException;

/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Paul Chapman
 */
@Service
public class WebAccountsService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebAccountsService.class
			.getName());

	public WebAccountsService(String serviceUrl) {
		logger.info("WebAccountsService URL=="+serviceUrl);
		//this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
		this.serviceUrl = serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory().getClass());
	}

	public Account findByNumber(String accountNumber) {

		logger.info("WebAccountsService findByNumber() invoked: for " + accountNumber+" url="+serviceUrl + "/accounts/{number}");
		Account account = null;
		try{
			account = restTemplate.getForObject(serviceUrl + "/accounts/{number}",Account.class, accountNumber);
		}catch (HttpClientErrorException e) { // 404
		 System.out.println("findByNumber exception");
		 e.printStackTrace();
	    }
		return account;
	}

	public List<Account> byOwnerContains(String name) {
		logger.info("WebAccountsService byOwnerContains() invoked:  for " + name+" url="+serviceUrl	+ "/accounts/owner/{name}");
		Account[] accounts = null;

		try {
			accounts = restTemplate.getForObject(serviceUrl	+ "/accounts/owner/{name}", Account[].class, name);
		} catch (HttpClientErrorException e) { // 404
			System.out.println("byOwnerContains exception");
			e.printStackTrace();
		}

		if (accounts == null || accounts.length == 0)
			return null;
		else
			return Arrays.asList(accounts);
	}

	public Account getByNumber(String accountNumber) {
		Account account = restTemplate.getForObject(serviceUrl
				+ "/accounts/{number}", Account.class, accountNumber);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else
			return account;
	}
}
