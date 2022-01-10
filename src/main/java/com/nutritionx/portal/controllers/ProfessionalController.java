package com.nutritionx.portal.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

import com.nutritionx.portal.model.Patient;
import com.nutritionx.portal.model.Professional;
import com.nutritionx.portal.repository.PatientRepository;
import com.nutritionx.portal.repository.ProfessionalRepository;
import com.nutritionx.portal.service.PatientService;

@Controller
@SessionAttributes("professional")
public class ProfessionalController {
	@Autowired
	private ProfessionalRepository profRepo;
	@Autowired
	private PatientRepository patRep;
	@Autowired
	private PatientService patSer;

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

	// POST for Professional's login
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
//		p = profRepo.findByProfessionalId(p.getProfessionalId());
//		p.getPatients(); // initialize the lazy fetch
//		m.addAttribute("professional", p);
//		m.addAttribute("byLastName", Comparator.comparing(Patient::getLastName));
//		return new ModelAndView("professionalMyPatients");

		int currentPage = page.orElse(1);
		final int pageSize = 6;
		int totalPages = 0;

		p = profRepo.findByProfessionalId(p.getProfessionalId());
		List<Patient> listAux = new ArrayList<>(p.getPatients());
		Comparator<Patient> compareByLastName = (Patient p1, Patient p2) -> p1.getLastName()
				.compareTo(p2.getLastName());
		listAux.sort(compareByLastName.reversed());

		Page<Patient> patPag = patSer.findPaginated(listAux, PageRequest.of(currentPage - 1, pageSize));
		totalPages = patPag.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			m.addAttribute("pageNumbers", pageNumbers);
		}

		m.addAttribute("currentPage", currentPage);
		m.addAttribute("patPag", patPag);
		m.addAttribute("professional", p);
		// m.addAttribute("byLastName", Comparator.comparing(Patient::getLastName));
		return new ModelAndView("professionalMyPatients");

	}

	// GET show the "MyPatients" module
	@GetMapping("/professional/mypatients/search")
	public ModelAndView searchPatient(@RequestParam("pLastName") String pLastName,
			@ModelAttribute("professional") Professional p, Model m, @RequestParam("page") Optional<Integer> page) {

//		p = profRepo.findByProfessionalId(p.getProfessionalId());
//		p.getPatients(); // initialize the lazy fetch
//		if (!lastName.isBlank()) {
//			Set<Patient> pat2 = new HashSet<>();
//			for (Patient pat : p.getPatients()) {
//				if (pat.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
//					pat2.add(pat);
//				}
//			}
//			p.setPatients(pat2);
//			if (pat2.isEmpty()) {
//				m.addAttribute("professional", p);
//				m.addAttribute("lastName", lastName);
//				m.addAttribute("byLastName", Comparator.comparing(Patient::getLastName));
//				return new ModelAndView("professionalMyPatients");
//			} else {
//				m.addAttribute("professional", p);
//				m.addAttribute("lastName", lastName);
//				m.addAttribute("byLastName", Comparator.comparing(Patient::getLastName));
//				return new ModelAndView("professionalMyPatients");
//			}
//		}else {
//			return new ModelAndView("redirect:/professional/mypatients");
//		}
//	}

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
			
			/*if (!listAux.isEmpty()) {
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
				m.addAttribute("lastName", lastName);
				return new ModelAndView("professionalMyPatients");
			} else {
				Page<Patient> patPag = patSer.findPaginated(listAux ,PageRequest.of(currentPage-1, pageSize));
				totalPages = patPag.getTotalPages();
				
				if (totalPages > 0) {
		            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
		                .boxed()
		                .collect(Collectors.toList());
		            m.addAttribute("pageNumbers", pageNumbers);
		        }
				m.addAttribute("currentPage", currentPage);
				Page<Patient> patPag=null;
		        m.addAttribute("patPag",patPag);
				m.addAttribute("professional", p);
				m.addAttribute("lastName", lastName);
				return new ModelAndView("professionalMyPatients");
			}*/
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

}
