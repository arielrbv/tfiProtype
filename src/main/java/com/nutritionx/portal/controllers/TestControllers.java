package com.nutritionx.portal.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Breakfast;
import com.nutritionx.portal.model.Meals;
import com.nutritionx.portal.model.NutritionalPlan;
import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.PatientNutriPlan;
import com.nutritionx.portal.repository.BreakfastRepository;
import com.nutritionx.portal.repository.MealsRepository;
import com.nutritionx.portal.repository.NutritionalPlanRepository;
import com.nutritionx.portal.repository.PatientNutriPlanRepository;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.service.PatientService;
import com.nutritionx.portal.util.PlanAssignment;

@Controller
public class TestControllers {

	@Autowired
	private PatientRepository patRep;

	@Autowired
	private PatientService patServ;

	@Autowired
	private PatientNutriPlanRepository patNutriPRepo;


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
	
	@GetMapping("/")
	public ModelAndView showpage(Model m) {
		Patient p = patRep.findByEmail("patient6@gmail.com");
		m.addAttribute("patient", p);
		m.addAttribute("avatar", Base64.getEncoder().encodeToString(p.getAvatar()));
		return new ModelAndView("imageselect");
	}

	@PostMapping("/test/profile/update/avatar")
	public ModelAndView updateAvatar(@RequestParam("avatar") MultipartFile file , Model m) {
		//Base64.getEncoder().encodeToString(
		Patient p = (Patient) m.getAttribute("patient");
		try {
			p.setAvatar(file.getBytes());
			System.out.println(p.getAvatar());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patRep.save(p);
		m.addAttribute("patient", p);
		return new ModelAndView("redirect:/");
	}
	
	
	
	
	@GetMapping ("/test/query")
	@ResponseBody
	public Patient getPatient(@RequestBody Patient p){
		return patRep.findByPatientId(p.getPatientId());
	}
	
	@ResponseBody
	@RequestMapping(value = "/test/query/patient", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = "application/json"*/)
	public ResponseEntity<HttpStatus> createPatientStandAlone(@RequestBody Patient p) {	
		
		patServ.updatePatient(p);
				
		System.out.println(p);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
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
	
	
	@Autowired
	private NutritionalPlanRepository nutriPlanRepo;
	
	@GetMapping ("/test/nutriplan")
	@ResponseBody
	public NutritionalPlan getNutriPlan (@RequestBody NutritionalPlan np){
		return nutriPlanRepo.findByNutriPlanId(np.getNutriPlanId());
	}
	
	@Autowired
	private MealsRepository mealRepo;
	
	@GetMapping ("/test/meals")
	@ResponseBody
	//@RequestMapping(value = "/test/meals", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public List<Meals> getMeal (@RequestBody Meals meal){
	//	Meals m3 = mealRepo.findByMealId("27");
	//	System.out.println("\n\n"+m3);
		return  mealRepo.findByPlanId(meal.getPlanId());
	}

	@Autowired
	private BreakfastRepository breakfastRepo;
	
	//@GetMapping ("/test/breakfast")
	@ResponseBody
	@RequestMapping(value = "/test/breakfast", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional <Breakfast> getbreakfast (@RequestBody Breakfast b){
		String[] input = b.getId().split("\\|");		
		Optional <Breakfast> br = breakfastRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	
	
//	@RequestMapping(value = "/test/changeBreakfast", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = "application/json"*/)
//	public ModelAndView changeBreakfast (@RequestBody PatientNutriPlan pnp){
//		ModelAndView m = new ModelAndView();
//		String[] input = pnp.getBreakfastId().split("\\|");	
//		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
//		Optional <Breakfast> br = breakfastRepo.findById(input[1]);
//		
//		pnp2.ifPresent(pn2 ->{
//			br.ifPresent(br2 ->{
//				pn2.setBreakfastId(br2.getId());
//				pn2.setBreakfastDescription(br2.getDescription());
//				patNutriPRepo.save(pn2);
//			});
//			m.addObject("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
//		} );
//		
//		m.setViewName("home2");
//		return m;
//	}
//	public String changeBreakfast (@RequestBody PatientNutriPlan pnp, Model m){
//		String[] input = pnp.getBreakfastId().split("\\|");	
//		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
//		Optional <Breakfast> br = breakfastRepo.findById(input[1]);
//		
//		pnp2.ifPresent(pn2 ->{
//			br.ifPresent(br2 ->{
//				pn2.setBreakfastId(br2.getId());
//				pn2.setBreakfastDescription(br2.getDescription());
//				patNutriPRepo.save(pn2);
//			});
//			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
//		} );
//		
//		return "home2";
//	}

	
	
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
