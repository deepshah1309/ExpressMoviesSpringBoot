package com.practice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.Entity.Director;


@Repository("directorRepository")
public interface DirectorRepository  extends JpaRepository<Director,Integer>{

	
	  public Optional<Director> findByFirstName(String name);
	  public Optional<Director> findByFirstNameAndLastName(String firstName,String lastName);
}
