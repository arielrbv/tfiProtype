package com.nutritionx.portal.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

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
import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.service.PatientService;
import com.nutritionx.portal.service.ServiceResponse;
import com.nutritionx.portal.util.JavaMailUtil;

@Controller
//@RestController
public class PatientController {

	private Date dateAux = new Date();

	@Autowired
	private PatientService patServ;

	@ModelAttribute(value = "patient")
	public Patient newEntity() {
		return new Patient();
	}

	@GetMapping("/selfRegistration")
	public String showSelfRegForm(Model model) {
		Patient p = new Patient();
		model.addAttribute("patient", p);
		model.addAttribute("dateAux", dateAux);
		return "selfRegistration";
	}



	@ResponseBody
	@RequestMapping(value = "/selfRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json"/*,  MediaType.APPLICATION_JSON_VALUE*/)
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
	


	

	//@PostMapping("/portal/test/selfRegistration")
	
	/**IT WORKS PERFECTLY WITHOUT SENDING A JSON*/
//	@ResponseBody
//	@RequestMapping(value = "/portal/test/selfRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = "application/json"*/)
//	public ResponseEntity createPatientStandAlone(@RequestBody Patient p) {	
//		System.out.println(p);
//		return new ResponseEntity(HttpStatus.OK);
//	}
	
	/**IT WORKS PERFECTLY  SENDING A JSON*/
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



		//System.out.println(d);

	












