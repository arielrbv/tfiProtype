package com.nutritionx.portal.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kie.api.runtime.KieSession;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Meals;
import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.PatientNutriPlan;
import com.nutritionx.portal.model.Patology;
import com.nutritionx.portal.model.Preference;
import com.nutritionx.portal.model.Professional;
import com.nutritionx.portal.repository.NutritionalPlanRepository;
import com.nutritionx.portal.repository.PatientNutriPlanRepository;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.repository.PatologyRepository;
import com.nutritionx.portal.repository.PreferenceRepository;
import com.nutritionx.portal.repository.ProfessionalRepository;
import com.nutritionx.portal.service.PatientService;
import com.nutritionx.portal.service.ServiceResponse;
import com.nutritionx.portal.util.DateAux;
import com.nutritionx.portal.util.GenericUser;
import com.nutritionx.portal.util.JavaMailUtil;
import com.nutritionx.portal.util.PlanAssignment;

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
	private PatientNutriPlanRepository patNutriPRepo;
	@Autowired
	private ProfessionalRepository proRepo;
	@Autowired
	private KieSession session;
	@Autowired
	private NutritionalPlanRepository nutriPlanRepo;

	// SET the patient to be present in the session.
	@ModelAttribute("patient")
	public Patient newPatient() {
		return new Patient();
	}

	// GET to load the Login View
	@GetMapping("/login")
	public String showLogin(Model m) {
		m.addAttribute("patient", newPatient());
		return "login";
	}

	// GET to load the selfReg View
	@GetMapping("/selfRegistration")
	public String showSelfRegForm(Model m) {
		m.addAttribute("patient", newPatient());
		return "selfRegistration";//
	}

	// GET to load the home View
	@GetMapping("/home")
	public String showHome(Model m) {

		Patient p = (Patient) m.getAttribute("patient");
		p = patRep.findByEmail(p.getEmail());
		m.addAttribute("patient", p);
		m.addAttribute("plan", patNutriPRepo.findByPatientOrderByDayAsc(p));

		return "home";
	}

	// GET to load the planPreStep1 View
	@GetMapping("/planprep/step-1")
	public String showPlanPrepStep1(Model m) {

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

	// GET to load the patientUpdateProfile view
	@GetMapping("/profile/update")
	public String showProfileUpdate(Model m) {
		Patient p = (Patient) m.getAttribute("patient");
		DateAux d = new DateAux();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		d.setDate(format.format(p.getBirthdate()));
		m.addAttribute("aux", d);
		return "patientUpdateProfile";
	}

	// GET to load the passwordUpdate view
	@GetMapping("/password/update")
	public String showPasswordUpdate(Model m) {
		m.addAttribute("genericUser", new GenericUser());
		return "patientUpdatePassword";
	}

	// GET to activate account
	@GetMapping("/userAccountActivation")
	public ModelAndView accountActivation(@RequestParam("user") String email, @RequestParam("token") String token) {
		Patient p = patRep.findByEmailAndToken(email, token);
		if (p != null) {
			p.setToken(null);
			patRep.save(p);
			return new ModelAndView("redirect:/accountActivation", HttpStatus.OK);
		} else {// accountAlreadyActivated or incorrect data combination
			p = new Patient();
			return new ModelAndView("redirect:/login", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/accountActivation")
	public String accountActivationSuccess() {
		return "/accountActivation";
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

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest((p.getEmail() + p.getPassword()).getBytes(StandardCharsets.UTF_8));
			String secureAux = Base64.getEncoder().encodeToString(hash);
			p.setPassword(secureAux);

			p = patRep.findByEmailAndPassword(p.getEmail(), p.getPassword());
			if (p != null) {
				if (p.getToken() == null) {
					m.setViewName("redirect:/home");
					m.addObject("patient", p);
				} else {
					m.setViewName("loginAuthFailInactive");
					m.setStatus(HttpStatus.UNAUTHORIZED);
					p = new Patient();
					m.addObject("patient", p);
				}
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
	public String patientLogout(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		m.addAttribute("patient", newPatient());
		return "redirect:/login";
	}

	// POST to load the planPreStep2 View
	@PostMapping("/planprep/step-1")
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
	// @PostMapping("/test/checkbox")
	@RequestMapping(value = "/planprep/assignment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	public ResponseEntity<HttpStatus> showfinalModal(@RequestParam(value = "pats", required = false) List<Patology> pat,
			@ModelAttribute("preference") Preference pref1,
			@RequestParam(value = "prefs", required = false) List<Preference> prefs, Model m) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Patient p = (Patient) m.getAttribute("patient");
		PlanAssignment pa = new PlanAssignment(p.getGender(),
				Period.between(p.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						ts.toLocalDateTime().toLocalDate()).getYears(),
				p.getWeight(), p.getHeight(), "", null);

		if (pat == null) {
			Set<Patology> setPat = new HashSet<Patology>();
			p.setPatologies(setPat);
		} else {
			Set<Patology> setPat = new HashSet<Patology>(pat);
			p.setPatologies(setPat);
		}

		if (prefs != null) {
			for (Preference pr : prefs) {
				p.addPreference(pr);
			}
		}

		switch (pref1.getPreferenceId()) {
		case "card1":
			pref1 = prefRep.findByPreferenceId("8b5f86da-5ec8-11ec-9e40-98fa9b9e034a");
			pa.setExerciseTime("30-");
			break;
		case "card2":
			pref1 = prefRep.findByPreferenceId("8b5fa105-5ec8-11ec-9e40-98fa9b9e034a");
			pa.setExerciseTime("30-60");
			break;
		case "card3":
			pref1 = prefRep.findByPreferenceId("8b5fb8b3-5ec8-11ec-9e40-98fa9b9e034a");
			pa.setExerciseTime("60+");
			break;
		default:
			break;
		}

		// trigger the SE and plan assignment
		session.insert(pa);
		session.fireAllRules();

		p.setPlanType(pa.getAssigment());
		p.addPreference(pref1);

		// create patientNutriPlan Lines

		for (Meals meal : nutriPlanRepo.findByNutriPlanId(p.getPlanType()).getMealsOfplan()) {
			p.addLinesOfPlan(new PatientNutriPlan(null, p.getPlanType(), p, meal.getDay(), meal.getBreakfastId(),
					meal.getBreakfastDescription(), meal.getMsnackId(), meal.getMsnackDescription(), meal.getLunchId(),
					meal.getLunchDescription(), meal.getAsnackId(), meal.getAsnackDescription(), meal.getPdsnackId(),
					meal.getPdsnackDescription(), meal.getDinnerId(), meal.getDinnerDescription()));
		}

		// assign professional
		assingProfessional(p);

		m.addAttribute("patient", p);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/profile/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	public ResponseEntity<HttpStatus> profileUpdate(@ModelAttribute("patient") Patient p,
			@ModelAttribute("aux") DateAux d, Model m) {

		try {
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(d.getDate());
			p.setBirthdate(date1);
			patServ.updatePatient(p);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/password/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	public ResponseEntity<HttpStatus> passwordUpdate(@ModelAttribute("patient") Patient p,
			@ModelAttribute("genericuser") GenericUser u, Model m) {

		//
		// GET RID OFF THE COMMENTS AT THE FINAL VERSION OF THE PROTOTYPE TO TRIGGER THE
		// SECURE PASS
		//
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// SecureRandom secureRandom = new SecureRandom(); // Hash Randombytes as token
			byte[] hash = digest.digest((p.getEmail() + u.getcPass()).getBytes(StandardCharsets.UTF_8));
			String secureAux = Base64.getEncoder().encodeToString(hash);

			p = patRep.findByEmailAndPassword(p.getEmail(), secureAux);
			if (p != null) { /// update pass
				hash = digest.digest((p.getEmail() + u.getnPass()).getBytes(StandardCharsets.UTF_8));
				secureAux = Base64.getEncoder().encodeToString(hash);
				p.setPassword(secureAux);
				patServ.updatePatient(p);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			} else {
				return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public void assingProfessional(Patient p) {
		Professional pr = proRepo.findByProfessionalId("880be7ec-1e1c-4aba-83b0-47de14c6222d");
		p.addProfessional(pr);
		// patServ.updatePatient(p);
		patRep.save(p);
	}

}
