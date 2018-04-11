package com.ships.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ships.model.ShippingCompany;
import com.ships.services.ShippingCompanyService;

@Controller
public class ShippingCompanyController {

	@Autowired
	private ShippingCompanyService shipCompService;

	@RequestMapping(value = "/showShippingCompanies", method=RequestMethod.GET)
	public String getShips(Model m){

		ArrayList<ShippingCompany> shippingCompany = shipCompService.getCompany();
		m.addAttribute("shippingCompany", shippingCompany);

		return "displayShippingCompany";
	}

	// this is the get request which directs to the add company page
	@RequestMapping(value = "/addShippingCompany", method = RequestMethod.GET)
	public String getAddShippingCompany(@ModelAttribute("shipC") ShippingCompany shipC, HttpServletRequest h) {

		return "addShippingCompany";
	}

	// This is the post request which saves the shipping company object 
	// then displays the new data on the display page 
	@RequestMapping(value = "/addShippingCompany", method = RequestMethod.POST)
	public String postProduct(@Valid @ModelAttribute("shipC") ShippingCompany shipC,BindingResult result) {

		if(result.hasErrors()) {			
			return "addShippingCompany";
		}else{
			// Pass the customer to the Customer Service for saving
			shipCompService.save(shipC);
			return "redirect:displayShippingCompany";
		}
	}

}
