package com.journaldev.spring.form.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.form.model.AddressForm;
import com.journaldev.spring.form.model.Customer;

@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomerController.class);
	
	private Map<String, AddressForm> customers = null;
	
	/*public CustomerController(){
		customers = new HashMap<String, Customer>();
	}

	@RequestMapping(value = "/cust/save", method = RequestMethod.GET)
	public String saveCustomerPage(Model model) {
		logger.info("Returning custSave.jsp page");
		model.addAttribute("customer", new Customer());
		return "custSave";
	}

	@RequestMapping(value = "/cust/save.do", method = RequestMethod.POST)
	public String saveCustomerAction(
			@Valid Customer customer,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning custSave.jsp page");
			return "custSave";
		}
		logger.info("Returning custSaveSuccess.jsp page");
		model.addAttribute("customer", customer);
		customers.put(customer.getEmail(), customer);
		return "custSaveSuccess";
	}*/
	
	public CustomerController(){
		customers = new HashMap<String, AddressForm>();
	}

	@RequestMapping(value = "/cust/save", method = RequestMethod.GET)
	public String saveCustomerPage(Model model) {
		logger.info("Returning custSave.jsp page");
		model.addAttribute("addressForm", new AddressForm());
		return "custSave";
	}

	@RequestMapping(value = "/cust/save.do", method = RequestMethod.POST)
	public String saveCustomerAction(
			@ModelAttribute("addressForm") @Valid AddressForm customer,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning custSave.jsp page"+" count=="+bindingResult.getErrorCount());
			return "custSave";
		}
		logger.info("Returning custSaveSuccess.jsp page");
		model.addAttribute("addressForm", customer);
		customers.put(customer.getMainAddress(), customer);
		return "custSaveSuccess";
	}

}
