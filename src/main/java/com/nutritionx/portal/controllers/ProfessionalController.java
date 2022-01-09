package com.nutritionx.portal.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.Professional;
import com.nutritionx.portal.repository.ProfessionalRepository;

@Controller
@SessionAttributes("professional")
public class ProfessionalController {
	@Autowired
	private ProfessionalRepository profRepo;

	@ModelAttribute("professional")
	public Professional newProfessional() {
		return new Professional();
	}

	@GetMapping("/professional/login")
	public ModelAndView showProfessionalLogin() {
		// return new ModelAndView("professionalLogin"); --> just view
		// return new ModelAndView("professionalLogin").addObject("professional", new
		// Professional()); --> one way to send the object;
		return new ModelAndView("professionalLogin", "professional", new Professional()); // other way
	}
	

	@PostMapping("/loginP")
	public ModelAndView loginProfessional(@ModelAttribute("professional") Professional p, Model m) {
		try {
			p = profRepo.findByEmailAndPassword(p.getEmail(), p.getPassword());
			if (p != null) {
				m.addAttribute("professional", p);
				return new ModelAndView("redirect:/professional/home", "professional", p);
			} else {
				ModelAndView mv = new ModelAndView("professionalLoginAuthFail", "professional", new Professional());
				mv.setStatus(HttpStatus.UNAUTHORIZED);
				return mv;
			}
		} catch (Exception e) {		
			ModelAndView mv = new ModelAndView("professionalLoginAuthFail", "professional", new Professional());
			mv.setStatus(HttpStatus.UNAUTHORIZED);
			return mv;
		}
	}
	
	@GetMapping("/professional/home")
	public ModelAndView showProfessionalHome(Model m) {
		return new ModelAndView("professionalHome"/*,"professional",(Professional) m.getAttribute("professional")*/);
	}

	
	
	
	
	// POST Patient LOGOUT Attempt from VIEW
	@PostMapping(value = "/professional/logout")
	public String professionalLogout(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		m.addAttribute("professional", newProfessional());
		return "redirect:/professional/login";
	}
}
