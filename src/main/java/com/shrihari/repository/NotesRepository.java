package com.shrihari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shrihari.entity.Notes;
import com.shrihari.entity.User;


public interface NotesRepository extends JpaRepository<Notes, Integer> {

	public List<Notes> findByUser(User user );
}
