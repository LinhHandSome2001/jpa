package com.phucle.spring.jpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phucle.spring.jpa.entity.Customer;
import com.phucle.spring.jpa.entity.Hobby;
import com.phucle.spring.jpa.service.BaseService;

@Controller
@RequestMapping("/hobby")
public class HobbyController {
	@Autowired
    private BaseService<Hobby> hobbyService;
	
	@Autowired
	private BaseService<Customer> customerService;
	
	@ModelAttribute("customers")
	public List<Customer> customers(){
	        return customerService.getAll();
	}
	
	@GetMapping("/list")
	public String listHobbies(Model theModel) {
		List<Hobby> theProvinces = hobbyService.getAll();
		theModel.addAttribute("hobbies", theProvinces);
	
		
		return "list-hobby";
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		//LOG.debug("inside show customer-form handler method");
		Hobby theProvice = new Hobby();
		theModel.addAttribute("hobby", theProvice);
		
		return "hobby-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("hobby") Hobby theProvince,BindingResult theBindingResult) {
    	if (theBindingResult.hasErrors()) {
    		return "hobby-form";
    	}
    	else {
		hobbyService.save(theProvince);	
		return "redirect:/hobby/list";
    	}
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("hobbyId") int theId,
									Model theModel)  {
		Hobby theProvince = hobbyService.getByID(theId);	
		theModel.addAttribute("hobby", theProvince);
		return "hobby-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("hobbyId") int theId)  {
		hobbyService.deleteById(theId);
		return "redirect:/hobby/list";
	}
}
