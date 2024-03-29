package com.shrihari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shrihari.entity.Notes;
import com.shrihari.entity.User;
import com.shrihari.repository.NotesRepository;
@Service
public class NotesServiceImpl implements NotesService {
	@Autowired
	private NotesRepository notesRepo;
	@Override
	public Notes saveNotes(Notes note) {
		Notes saveNotes =notesRepo.save(note);
		return saveNotes;
	}

	@Override
	public Notes getNotesById(Integer id) {
		Notes notes = notesRepo.findById(id).get();
		return notes;
	}

	@Override
	public List<Notes> getNotesByUser(User user) {
		List<Notes> notes =notesRepo.findByUser(user);
		return notes;
	}

	@Override
	public Notes updateNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepo.save(notes);
	}

	@Override
	public boolean deleteNotes(Integer id) {
		Notes deleteNotes =notesRepo.findById(id).get();
		if(deleteNotes != null) {
			notesRepo.delete(deleteNotes);
			return true;
		}
		return false;
	}

}
