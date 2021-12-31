package com.nutritionx.portal.controllers;

import java.time.Period;
import java.time.ZoneId;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.service.PatientService;
import com.nutritionx.portal.service.ServiceResponse;
import com.nutritionx.portal.util.PlanAssignment;

@Controller
public class TestControllers {

	@Autowired
	private PatientRepository patRep;
	
	@Autowired
	private PatientService patServ;


//	@ResponseBody
//	@RequestMapping(value = "/test/query", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> createPatientStandAlone(@RequestBody Patient p) {
//		p = patRep.findByEmailAndPassword(p.getEmail(), p.getPassword());
//		ServiceResponse<Patient> response;
//		if (p != null) {
//			response = new ServiceResponse<Patient>("OK", p);
//		} else {
//			response = new ServiceResponse<Patient>("NOK", p);
//		}
//		return new ResponseEntity<Object>(response, HttpStatus.OK);
//	}

	
	@GetMapping ("/test/query")
	@ResponseBody
	public Patient getPatient(@RequestBody Patient p){
		return patRep.findByPatientId(p.getPatientId());
	}
	
	@ResponseBody
	@RequestMapping(value = "/test/query/patient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = "application/json"*/)
	public ResponseEntity createPatientStandAlone(@RequestBody Patient p) {	
		
		patServ.updatePatient(p);
				
		System.out.println(p);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@Autowired
	private KieSession session;
	
	@ResponseBody
	@RequestMapping(value = "/planAssignment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PlanAssignment planAssignment(@RequestBody PlanAssignment pa) {
		
		//PlanAssignment pa = new PlanAssignment("M",31,86.7f,1.73f,"30-",null);
		session.insert(pa);
		session.fireAllRules();
		return pa;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
//	public String userAuthentication(@RequestBody Patient p) {
//		p = patRep.findByEmailAndPassword(p.getEmail(), p.getPassword());
//		
//		if (p != null) {
////			ServiceResponse<Patient> response = new ServiceResponse<Patient>("OK", p);
//			Model m = null;
//			m.addAttribute("patient", p);	
//		} else {
////			ServiceResponse<Patient> response = new ServiceResponse<Patient>("NOK", p);
//		}
//		return "Home";
//	}
	
	
	/** TAKES INFO FROM THE VIEW AND SET ANOTHER VIEW IN CASE OF ERROR (WOULD BE BETTER WITH AJAX? FOR SURE...) */
	// POST Patient Login Attempt from VIEW
//	@RequestMapping(value = "/loginasddas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
//	public ModelAndView userAuthentication(@ModelAttribute("patient") Patient p) {
//		ModelAndView m = new ModelAndView();
//			p = patRep.findByEmailAndPassword(p.getEmail(), p.getPassword());
//			if (p != null) {
//				m.setViewName("home");
//				m.addObject("patient", p);
//			} else {
//				m.setViewName("loginAuthFail");
//				m.setStatus(HttpStatus.UNAUTHORIZED);
//				p = new Patient();
//				m.addObject("patient", p);
//			}
//		return m;
//	}
	

	
	


	/** IT WORKS PERFECTLY WITHOUT SENDING A JSON */
//	@ResponseBody
//	@RequestMapping(value = "/portal/test/selfRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = "application/json"*/)
//	public ResponseEntity createPatientStandAlone(@RequestBody Patient p) {	
//		System.out.println(p);
//		return new ResponseEntity(HttpStatus.OK);
//	}

	/** IT WORKS PERFECTLY SENDING A JSON */
//	@ResponseBody
//	@RequestMapping(value = "/portal/test/selfRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json"/*,  MediaType.APPLICATION_JSON_VALUE*/)
//	public ResponseEntity<Object> createPatientStandAlone(@RequestBody Patient p) {	
//		
//		System.out.println(p);
//		
//		ServiceResponse<Patient> response = new ServiceResponse<Patient>("success", p);
//		return new ResponseEntity<Object>(response, HttpStatus.OK);
//	}

}
