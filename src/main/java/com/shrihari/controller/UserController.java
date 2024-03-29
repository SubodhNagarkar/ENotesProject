package com.shrihari.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shrihari.entity.Notes;
import com.shrihari.entity.User;
import com.shrihari.repository.NotesRepository;
import com.shrihari.repository.UserRepository;
import com.shrihari.service.NotesService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private NotesService notesService;
	@ModelAttribute
	public User getuser(Principal p, Model m) {
		String email = p.getName();
		User user =userRepo.findByEmail(email);
		m.addAttribute("user",user);
		return user;
	}
	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_notes";
	}
	@GetMapping("/viewNotes")
	public String viewNotes(Model m ,Principal p) {
		User getuser = getuser(p, m);
		List<Notes> notesByUser = notesService.getNotesByUser(getuser);
		m.addAttribute("notesList",notesByUser);
		return "view_notes";
	}
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {
		
		Notes notesById = notesService.getNotesById(id);
		m.addAttribute("n",notesById);
		return "edit_notes";
	}
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes,Principal p, Model m ,HttpSession session) {
		notes.setDate(LocalDate.now());
		notes.setUser(getuser(p, m));
		Notes note =notesService.saveNotes(notes);
		if(note != null) {
			session.setAttribute("msg", "Sucefully saved the Notes");
		}
		else {
			session.setAttribute("msg", "failed");
		}
		return "redirect:/user/addNotes";
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes,Principal p, Model m ,HttpSession session) {
		notes.setDate(LocalDate.now());
		notes.setUser(getuser(p, m));
		Notes note =notesService.saveNotes(notes);
		if(note != null) {
			session.setAttribute("msg", "Sucessfully updated the Notes");
		}
		else {
			session.setAttribute("msg", "failed");
		}
		return "redirect:/user/viewNotes";
	}
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, Model m ,HttpSession session) {
		
		boolean f = notesService.deleteNotes(id);
		if(f) {
			session.setAttribute("msg", "deleted");
		}
		else {
			session.setAttribute("msg", " NOT deleted");
		}
		
		return "redirect:/user/viewNotes";
	}
	
	
}
