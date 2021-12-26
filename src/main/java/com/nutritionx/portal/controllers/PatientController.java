package com.nutritionx.portal.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.PatientNutriPlan;
import com.nutritionx.portal.model.Patology;
import com.nutritionx.portal.model.Preference;
import com.nutritionx.portal.repository.PatientNutriPlanRepository;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.repository.PatologyRepository;
import com.nutritionx.portal.repository.PreferenceRepository;
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

	@Autowired
	private PreferenceRepository prefRep;
	
	@Autowired
	private PatientNutriPlanRepository  patNutriPRepo;

	// SET the patient to be present in the session.
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
	public String showSelfRegForm(/* Model model */) {
		// Patient p = new Patient();
		// model.addAttribute("patient", p);
		return "selfRegistration";//
	}

	// GET to load the home View
	@GetMapping("/home")
	public String showHome(/* @RequestBody Patient p, */ Model m) {
		//
		// GET RID OFF This when /home be complete
		//
		Patient p = new Patient();
		p=patRep.findByEmailAndPassword("ariel.rbv@gmail.com", "pas123");
		//System.out.println(p.getLinesOfPlan());
		List<PatientNutriPlan> pnp = patNutriPRepo.findByPatientOrderByDayAsc(p);
		

		m.addAttribute("patient",p);
		m.addAttribute("plan", patNutriPRepo.findByPatientOrderByDayAsc(p));
		
		


		return "home";
	}

	// GET to load the planPreStep1 View
	@GetMapping("/planprep/step-1")
	public String showPlanPrepStep1(Model m) {

		// PatPrefTest pp = new PatPrefTest();
		// m.addAttribute("patpref", pp);
		Preference p = new Preference();
		m.addAttribute("preference", p);
		return "planPrepStep1";
	}

	// GET to load the planPreStep2 View
	@GetMapping("/planprep/step-2")
	public String showPlanPrepStep2(Model m) {
		Preference pre = new Preference();
		m.addAttribute("preference", pre);
		m.addAttribute("preference2", prefRep.findAllAndDescriptionisNull());
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
			// GET RID OFF THE COMMENTS AT THE FINAL VERSION OF THE PROTOTYPE TO TRIGGER THE
			// SECURE PASS
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
			// error
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

	// POST to load the planPreStep2 View
	@PostMapping("/planprep/step-2")
	public ModelAndView showPlanPrepStep2Post(@ModelAttribute("patient") Patient pat,
			@ModelAttribute("preference") Preference pref, Model m) {
		ModelAndView m2 = new ModelAndView();
		switch (pref.getPreferenceId()) {
		case "card1":
			pref = prefRep.findByPreferenceId("cd9cdd08-5ec5-11ec-9e40-98fa9b9e034a");
			break;
		case "card2":
			pref = prefRep.findByPreferenceId("8b5f38af-5ec8-11ec-9e40-98fa9b9e034a");
			break;
		case "card3":
			pref = prefRep.findByPreferenceId("8b5f53b4-5ec8-11ec-9e40-98fa9b9e034a");
			break;
		case "card4":
			pref = prefRep.findByPreferenceId("8b5f6d0e-5ec8-11ec-9e40-98fa9b9e034a");
			break;
		default:
			break;
		}

		pat.addPreference(pref);
		m.addAttribute("patient", pat);
		m2.setViewName("redirect:/planprep/step-2");
		return m2;
	}

	// map to post the step2
	
	//@PostMapping("/test/checkbox")
	@RequestMapping(value = "/test/checkbox", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	public ResponseEntity showfinalModal(@RequestParam(value = "pats", required = false) List<Patology> pat,
			@ModelAttribute("preference") Preference pref1,
			@RequestParam(value = "prefs", required = false) List<Preference> prefs, Model m) {	
		Patient p = (Patient) m.getAttribute("patient");
		Set<Patology> setPat = new HashSet<Patology>(pat);
		p.setPatologies(setPat);
		
		for (Preference pr : prefs) {
			p.addPreference(pr);
			
		}
		
		switch (pref1.getPreferenceId()) {
		case "card1":
			pref1 = prefRep.findByPreferenceId("8b5f86da-5ec8-11ec-9e40-98fa9b9e034a");
			break;
		case "card2":
			pref1 = prefRep.findByPreferenceId("8b5fa105-5ec8-11ec-9e40-98fa9b9e034a");
			break;
		case "card3":
			pref1 = prefRep.findByPreferenceId("8b5fb8b3-5ec8-11ec-9e40-98fa9b9e034a");
			break;
		default:
			break;
		}
		p.addPreference(pref1);		
		
		patServ.updatePatient(p);
		m.addAttribute("patient", p);
		return new ResponseEntity(HttpStatus.OK);
	}

}
