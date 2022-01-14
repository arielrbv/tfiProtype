package com.nutritionx.portal.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.nutritionx.portal.util.DateAux;
import com.nutritionx.portal.util.PatientPatologiesSelected;
import com.nutritionx.portal.util.PatientPreferencesSelected;

@Controller
@SessionAttributes("professional")
public class ProfessionalController {
	@Autowired
	private ProfessionalRepository profRepo;
	@Autowired
	private PatientRepository patRep;
	@Autowired
	private PatientService patSer;	
	@Autowired
	private PatologyRepository patoRep;
	@Autowired
	private PreferenceRepository prefRep;
	@Autowired
	private PatientNutriPlanRepository patNutriPRepo;
	@Autowired
	private NutritionalPlanRepository nutriPlanRepo;
	
	
	@ModelAttribute("professional")
	public Professional newProfessional() {
		return new Professional();
	}

	@GetMapping("/professional/login")
	public ModelAndView showProfessionalLogin() {
		return new ModelAndView("professionalLogin", "professional", new Professional()); // other way
	}

	// POST for Professional's login
	@PostMapping("/professional/login")
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

	// GET show the home
	@GetMapping("/professional/home")
	public ModelAndView showProfessionalHome(Model m) {
		return new ModelAndView("professionalHome");
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

	// GET show the "MyPatients" module
	@GetMapping("/professional/mypatients")
	public ModelAndView showMyPatientsDash(@ModelAttribute("professional") Professional p, Model m,
			@RequestParam("page") Optional<Integer> page) {


		int currentPage = page.orElse(1);
		final int pageSize = 6;
		int totalPages = 0;

		p = profRepo.findByProfessionalId(p.getProfessionalId());
		List<Patient> listAux = new ArrayList<>(p.getPatients());
		Comparator<Patient> compareByLastName = (Patient p1, Patient p2) -> p1.getLastName()
				.compareTo(p2.getLastName());
		listAux.sort(compareByLastName);

		Page<Patient> patPag = patSer.findPaginated(listAux, PageRequest.of(currentPage - 1, pageSize));
		totalPages = patPag.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			m.addAttribute("pageNumbers", pageNumbers);
		}

		m.addAttribute("currentPage", currentPage);
		m.addAttribute("patPag", patPag);
		m.addAttribute("professional", p);
		return new ModelAndView("professionalMyPatients");

	}

	// GET show the "MyPatients" module usig the Search
	@GetMapping("/professional/mypatients/search")
	public ModelAndView searchInMyPatients(@RequestParam("pLastName") String pLastName,
			@ModelAttribute("professional") Professional p, Model m, @RequestParam("page") Optional<Integer> page) {

		int currentPage = page.orElse(1);
		final int pageSize = 6;
		int totalPages = 0;

		p = profRepo.findByProfessionalId(p.getProfessionalId());
		List<Patient> listPat = new ArrayList<>(p.getPatients());
		Comparator<Patient> compareByLastName = (Patient p1, Patient p2) -> p1.getLastName()
				.compareTo(p2.getLastName());
		listPat.sort(compareByLastName.reversed());

		List<Patient> listAux = new ArrayList<>();
		if (!pLastName.isBlank()) {

			for (Patient pat : listPat) {
				if (pat.getLastName().toLowerCase().contains(pLastName.toLowerCase())) {
					listAux.add(pat);
				}
			}

			Page<Patient> patPag = patSer.findPaginated(listAux ,PageRequest.of(currentPage-1, pageSize));
			totalPages = patPag.getTotalPages();
			
			if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            m.addAttribute("pageNumbers", pageNumbers);
	        }
					
			m.addAttribute("currentPage", currentPage);
	        m.addAttribute("patPag",patPag);
			m.addAttribute("professional", p);
			m.addAttribute("pLastName", pLastName);
			return new ModelAndView("professionalMyPatients");
		} else {
			return new ModelAndView("redirect:/professional/mypatients");
		}
	}
	
	// GET show the "MyPatients" module usig the Search
		@GetMapping("/professional/mypatients/patient")
		public ModelAndView searchPatient(@RequestParam("patientId") String pId, Model m) {
			Patient p = patRep.findByPatientId(pId);
			
			if(p != null) {
				DateAux d = new DateAux();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				d.setDate(format.format(p.getBirthdate()));
				d.setDate(d.getDate()+" ("+ Integer.toString(Period.between(p.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						ts.toLocalDateTime().toLocalDate()).getYears())+" años)");
				
				m.addAttribute("aux", d);
				
				//analize preferences:
				List<PatientPreferencesSelected> prefAux = new ArrayList<>();
				if(p.getPreferences() != null) {
					for(Preference p1 : prefRep.findAll()) {
						if(p.getPreferences().contains(p1)) {
							prefAux.add(new PatientPreferencesSelected(p1.getName(),p1.getType(),true));
						}else {
							prefAux.add(new PatientPreferencesSelected(p1.getName(),p1.getType(),false));
						}
					}
				}
				//analize patology:
				List<PatientPatologiesSelected> patAux = new ArrayList<>();
				if(p.getPatologies() != null) {
					for(Patology p1 : patoRep.findAll()) {
						if(p.getPatologies().contains(p1)) {
							patAux.add(new PatientPatologiesSelected(p1.getName(),true));
						}else {
							patAux.add(new PatientPatologiesSelected(p1.getName(),false));
						}
					}
				}

				m.addAttribute("plan", patNutriPRepo.findByPatientOrderByDayAsc(p));
				m.addAttribute("nutriPlan", nutriPlanRepo.findAll());	
				m.addAttribute("patient", p);
				m.addAttribute("preference", prefAux);
				m.addAttribute("patology", patAux);
				return new ModelAndView("professionalShowPatient");
			}else {
				return new ModelAndView("redirect:/professional/mypatients"); //back to patient view
			}
		}
		
		//POST for professional to change the plan
		@PostMapping("/professional/mypatients/patient/changeplan")
		public ModelAndView changePlan(@RequestParam("patientId") String pId ,@RequestParam("nutriPlanId") String nPId, Model m) {
			Patient p = patRep.findByPatientId(pId);
			p.getLinesOfPlan();
			patNutriPRepo.deleteAllInBatch(p.getLinesOfPlan());
			
			
			p.setPlanType(nPId);
			
			for (Meals meal : nutriPlanRepo.findByNutriPlanId(p.getPlanType()).getMealsOfplan()) {
				p.addLinesOfPlan(new PatientNutriPlan(null, p.getPlanType(), p, meal.getDay(), meal.getBreakfastId(),
						meal.getBreakfastDescription(), meal.getMsnackId(), meal.getMsnackDescription(), meal.getLunchId(),
						meal.getLunchDescription(), meal.getAsnackId(), meal.getAsnackDescription(), meal.getPdsnackId(),
						meal.getPdsnackDescription(), meal.getDinnerId(), meal.getDinnerDescription()));
			}

			patRep.save(p);

			return new ModelAndView("redirect:/professional/mypatients/patient?patientId="+pId);
		}
		
		
		
}






















