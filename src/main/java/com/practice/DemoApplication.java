package com.practice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.practice.DTO.DirectorDTO;
import com.practice.DTO.MovieDTO;
import com.practice.Service.MovieService;



/**
 * 
 * @author DEEP
 * 1268134 - DSE
 * 
 * GUIDELINE TO RUN PROJECT CREATE SCHEMA WITH NAME = movie_project in SQL WORKBENCH
 *
 */
@SpringBootApplication
public class DemoApplication {


	@SuppressWarnings("all")
	public static void main(String[] args) {
		AbstractApplicationContext context = (AbstractApplicationContext)SpringApplication.run(DemoApplication.class, args);
	    
		
		MovieService service = (MovieService)context.getBean("movieservice");
		//Insert 
		MovieDTO dto = new MovieDTO("BNC",LocalDate.now(),LocalDateTime.now());
		List<DirectorDTO> directorList = Arrays.asList(
				new DirectorDTO("abn", "vbn", "Mombai","abc@infosys.com",9876543210L),
				new DirectorDTO("xyz","vdg","Banglore","richard@infy.com",9089786734L)
				);
	
		
		//Uncomment below code to add New Entries in Movie
//		service.add(dto,directorList);  //this automates mapping of DIRECTORS IN MOVIE
		//adds Entry in Movie , Director and relational Mapping in Movie_Director Table
		
		
        service.searchBasedOnTitle("ABC");
        
        
//        service.updateReleaseDate(LocalDate.of(2024, 6, 1), "ABC");
        
        
        /**
         * Get all Directors from Movie Title
         */
        System.out.println("================= Get all Directors from Movie Title ===================");
        
        service.getDirectorListFromTitle("ABC");
        
        /**
         * Get all Movies from Director Name
         */
        System.out.println(" ===================Get all Movies from Director Name=============");
        service.getMovieListFromDirectorName("ABn");
        
        
        //Get All movies
        //Display Log At Info Level
        System.out.println(service.displayAll().toString());       
        
        
        /**
         * UPDATES IN DIRECTOR : 
         * @author DEEP (DSE)
         */
        
        //changing Mombai to Mumbai and abc@infosys.com to xyz @infosys.com .
        //for actual data refer line no 31 and uncomment insert function to add records
        service.updateDirectorDetails(new DirectorDTO("abn", "vbn", "Mumbai","xyz@infosys.com",9876543210L));
        
	}

}
