package com.nutritionx.portal.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nutritionx.portal.model.Asnack;
import com.nutritionx.portal.model.Breakfast;
import com.nutritionx.portal.model.Dinner;
import com.nutritionx.portal.model.Lunch;
import com.nutritionx.portal.model.Meals;
import com.nutritionx.portal.model.Msnack;
import com.nutritionx.portal.model.NutritionalPlan;
import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.PatientNutriPlan;
import com.nutritionx.portal.model.Pdsnack;
import com.nutritionx.portal.model.Professional;
import com.nutritionx.portal.repository.AsnackRepository;
import com.nutritionx.portal.repository.BreakfastRepository;
import com.nutritionx.portal.repository.DinnerRepository;
import com.nutritionx.portal.repository.LunchRepository;
import com.nutritionx.portal.repository.MsnackRepository;
import com.nutritionx.portal.repository.NutritionalPlanRepository;
import com.nutritionx.portal.repository.PatientNutriPlanRepository;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.repository.PdsnackRepository;

@Controller
public class PatientNutriPlanController {
	@Autowired
	private PatientRepository patRep;
	@Autowired
	private PatientNutriPlanRepository patNutriPRepo;
	@Autowired
	private BreakfastRepository breakfastRepo;
	@Autowired
	private MsnackRepository msnackRepo;
	@Autowired
	private LunchRepository lunchRepo;
	@Autowired
	private AsnackRepository asnackRepo;
	@Autowired
	private PdsnackRepository pdsnackRepo;	
	@Autowired
	private DinnerRepository dinnerRepo;
	@Autowired
	private NutritionalPlanRepository nutriPlanRepo;
	

	@ResponseBody
	@RequestMapping(value = "/nutriplan/getBreakfast", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional <Breakfast> getBreakfast (@RequestBody Breakfast b){
		String[] input = b.getId().split("\\|");		
		Optional <Breakfast> br = breakfastRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	@RequestMapping(value = "/nutriplan/changeBreakfast", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changeBreakfast (@RequestBody PatientNutriPlan pnp, Model m){
		String[] input = pnp.getBreakfastId().split("\\|");	
		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
		Optional <Breakfast> br = breakfastRepo.findById(input[1]);
		
		pnp2.ifPresent(pn2 ->{
			br.ifPresent(br2 ->{
				pn2.setBreakfastId(br2.getId());
				pn2.setBreakfastDescription(br2.getDescription());
				patNutriPRepo.save(pn2);
			});
			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
		} );
		return "z";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/nutriplan/getMsnack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional<Msnack> getMsnack (@RequestBody Msnack b){
		String[] input = b.getId().split("\\|");		
		Optional<Msnack> br = msnackRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	@RequestMapping(value = "/nutriplan/changeMsnack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changeMsnack (@RequestBody PatientNutriPlan pnp, Model m){
		String[] input = pnp.getMsnackId().split("\\|");	
		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
		Optional<Msnack> br = msnackRepo.findById(input[1]);
		
		pnp2.ifPresent(pn2 ->{
			br.ifPresent(br2 ->{
				pn2.setMsnackId(br2.getId());
				pn2.setMsnackDescription(br2.getDescription());
				patNutriPRepo.save(pn2);
			});
			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
		} );
		return "z";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/nutriplan/getLunch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional<Lunch> getLunch (@RequestBody Lunch b){
		String[] input = b.getId().split("\\|");		
		Optional<Lunch> br = lunchRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	@RequestMapping(value = "/nutriplan/changeLunch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changeLunch (@RequestBody PatientNutriPlan pnp, Model m){
		String[] input = pnp.getLunchId().split("\\|");	
		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
		Optional<Lunch> br = lunchRepo.findById(input[1]);
		
		pnp2.ifPresent(pn2 ->{
			br.ifPresent(br2 ->{
				pn2.setLunchId(br2.getId());
				pn2.setLunchDescription(br2.getDescription());
				patNutriPRepo.save(pn2);
			});
			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
		} );
		return "z";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/nutriplan/getAsnack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional<Asnack> getAsnack(@RequestBody Asnack b){
		String[] input = b.getId().split("\\|");		
		Optional<Asnack> br = asnackRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	@RequestMapping(value = "/nutriplan/changeAsnack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changeAsnack (@RequestBody PatientNutriPlan pnp, Model m){
		String[] input = pnp.getAsnackId().split("\\|");	
		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
		Optional<Asnack> br = asnackRepo.findById(input[1]);
		
		pnp2.ifPresent(pn2 ->{
			br.ifPresent(br2 ->{
				pn2.setAsnackId(br2.getId());
				pn2.setAsnackDescription(br2.getDescription());
				patNutriPRepo.save(pn2);
			});
			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
		} );
		return "z";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/nutriplan/getPdsnack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional<Pdsnack> getPdsnack(@RequestBody Pdsnack b){
		String[] input = b.getId().split("\\|");		
		Optional<Pdsnack> br = pdsnackRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	@RequestMapping(value = "/nutriplan/changePdsnack", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changePdsnack (@RequestBody PatientNutriPlan pnp, Model m){
		String[] input = pnp.getPdsnackId().split("\\|");	
		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
		Optional<Pdsnack> br = pdsnackRepo.findById(input[1]);
		
		pnp2.ifPresent(pn2 ->{
			br.ifPresent(br2 ->{
				pn2.setPdsnackId(br2.getId());
				pn2.setPdsnackDescription(br2.getDescription());
				patNutriPRepo.save(pn2);
			});
			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
		} );
		return "z";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/nutriplan/getDinner", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Optional<Dinner> getDinner(@RequestBody Dinner b){
		String[] input = b.getId().split("\\|");		
		Optional<Dinner> br = dinnerRepo.findById(input[1]);
		br.ifPresent(id -> id.setId(input[0])); 
		return br;
	}
	
	@RequestMapping(value = "/nutriplan/changeDinner", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changeDinner (@RequestBody PatientNutriPlan pnp, Model m){
		String[] input = pnp.getDinnerId().split("\\|");	
		Optional<PatientNutriPlan> pnp2 = patNutriPRepo.findById(Integer.parseInt(input[0]));
		Optional<Dinner> br = dinnerRepo.findById(input[1]);
		
		pnp2.ifPresent(pn2 ->{
			br.ifPresent(br2 ->{
				pn2.setDinnerId(br2.getId());
				pn2.setDinnerDescription(br2.getDescription());
				patNutriPRepo.save(pn2);
			});
			m.addAttribute("patient", patRep.findByPatientId(pn2.getPatient().getPatientId()));
		} );
		return "z";
	}
	

	
	
}
