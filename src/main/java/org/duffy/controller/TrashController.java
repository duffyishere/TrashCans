package org.duffy.controller;

import org.duffy.service.TrashCanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/trash/*")
@Log4j
public class TrashController {
	
	@Setter(onMethod_ = @Autowired)
	private TrashCanService service;

	@GetMapping("/map")
	public void map(Model model) {
		
		log.info("map.........");
		
		model.addAttribute("trashCans", service.getList());
	}
	
}
