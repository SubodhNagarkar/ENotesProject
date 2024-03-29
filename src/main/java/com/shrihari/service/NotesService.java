package com.shrihari.service;

import java.util.List;

import com.shrihari.entity.Notes;
import com.shrihari.entity.User;

public interface NotesService {

	public Notes saveNotes(Notes note);
	
	public Notes getNotesById(Integer id);
	
	public List<Notes> getNotesByUser(User user);
	
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(Integer id);
}
