package com.nutritionx.portal.controllers;

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
import com.nutritionx.portal.service.ServiceResponse;

@Controller
public class TestControllers {

	@Autowired
	private PatientRepository patRep;


	@ResponseBody
	@RequestMapping(value = "/test/query", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPatientStandAlone(@RequestBody Patient p) {
		p = patRep.findByEmailAndPassword(p.getEmail(), p.getPassword());
		ServiceResponse<Patient> response;
		if (p != null) {
			response = new ServiceResponse<Patient>("OK", p);
		} else {
			response = new ServiceResponse<Patient>("NOK", p);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
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
