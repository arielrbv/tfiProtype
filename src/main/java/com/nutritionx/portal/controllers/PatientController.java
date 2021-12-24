package com.nutritionx.portal.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.repository.PatologyRepository;
import com.nutritionx.portal.service.PatientService;
import com.nutritionx.portal.service.ServiceResponse;
import com.nutritionx.portal.util.JavaMailUtil;

@Controller
@SessionAttributes("patient")
public class PatientController {

	@Autowired
	private PatientService patServ;

	@Autowired
	private PatientRepository patRep;
	
	@Autowired
	private PatologyRepository patoRep;
	
	//SET the patient to be present in the session.
	@ModelAttribute("patient")
	public Patient newPatient() {
		return new Patient();
	}

	// GET to load the Login View
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	// GET to load the selfReg View
	@GetMapping("/selfRegistration")
	public String showSelfRegForm(/*Model model*/) {
		//Patient p = new Patient();
		//model.addAttribute("patient", p);
		return "selfRegistration";//
	}

	// GET to load the home View
	@GetMapping("/home")
	public String showHome(/* @RequestBody Patient p, */ Model m) {
		//
		//			GET RID OFF This when /home be complete
		//
		m.addAttribute("patient", patRep.findByEmailAndPassword("ariel.rbv@gmail.com", "pas123"));
		return "home";
	}
	
	// GET to load the planPreStep1 View
	@GetMapping("/planprep/step-1")
	public String showPlanPrepStep1(/* @RequestBody Patient p, */ Model m) {
		//
		//			GET RID OFF This when /home be complete
		//
		return "planPrepStep1";
	}
	
	// GET to load the planPreStep1 View
	
	@GetMapping("/planprep/step-2")
	public String showPlanPrepStep2(Model m) {
		
		m.addAttribute("patology", patoRep.findAll());

		return "planPrepStep2";
	}
	
	

	// POST Patient Account Creation from AJAX
	@ResponseBody
	@RequestMapping(value = "/selfRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Object> createPatient(@RequestBody Patient p) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date = new Date(ts.getTime());
		try {// creates a hash as token
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			SecureRandom secureRandom = new SecureRandom(); // Hash Randombytes as token
			byte[] hash = digest.digest(secureRandom.generateSeed(10));
			String secureAux = Base64.getEncoder().encodeToString(hash);
			p.setToken(secureAux);
			// Hash the email&pass as pass
			hash = digest.digest((p.getEmail() + p.getPassword()).getBytes(StandardCharsets.UTF_8));
			secureAux = Base64.getEncoder().encodeToString(hash);

			p.setPassword(secureAux);
			p.setCreated(date);
			p.setStatus(1);

			System.out.println("Patient to create: " + p.toString());
			patServ.createPatient(p);

			// sendMail(String recipient, int emailType, String userToken)
			JavaMailUtil.sendMail(p.getEmail(), 1, p.getToken());

		} catch (Exception e) {
			// SHOW POPUPOF FAILURE
		}
		ServiceResponse<Patient> response = new ServiceResponse<Patient>("success", p);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	// POST Patient Login Attempt from VIEW
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	public ModelAndView userAuthentication(@ModelAttribute("patient") Patient p) {
		ModelAndView m = new ModelAndView();

		try {
			//
			//			GET RID OFF THE COMMENTS AT THE FINAL VERSION OF THE PROTOTYPE TO TRIGGER THE SECURE PASS
			//
//			MessageDigest digest = MessageDigest.getInstance("SHA-256");
//			SecureRandom secureRandom = new SecureRandom(); // Hash Randombytes as token
//			byte[] hash = digest.digest((p.getEmail() + p.getPassword()).getBytes(StandardCharsets.UTF_8));
//			String secureAux = Base64.getEncoder().encodeToString(hash);
//			p.setPassword(secureAux);
			
			p = patRep.findByEmailAndPassword(p.getEmail(), p.getPassword());
			if (p != null) {
				m.setViewName("redirect:/home");
				m.addObject("patient", p);
			} else {
				m.setViewName("loginAuthFail");
				m.setStatus(HttpStatus.UNAUTHORIZED);
				p = new Patient();
				m.addObject("patient", p);
			}
		} catch (Exception e) {
				//error
		}
		return m;
	}
	
	// POST Patient LOGOUT Attempt from VIEW
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    m.addAttribute("patient", newPatient());
	    return "redirect:/login"; 
	}
	

}
