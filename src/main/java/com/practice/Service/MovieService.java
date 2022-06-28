package com.practice.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.DTO.DirectorDTO;
import com.practice.DTO.MovieDTO;
import com.practice.Entity.Director;
import com.practice.Entity.Movie;
import com.practice.Repository.DirectorRepository;
import com.practice.Repository.MovieRepository;



/**
 * AUTHOR: DEEP SHAH (DSE)
 */
@Service("movieservice")
@Transactional
public class MovieService implements MovieServiceI{

	

	private MovieRepository movieRepo;
	private DirectorRepository directorRepo;

    @Autowired
	public void setMovieRepo(MovieRepository movieRepo,DirectorRepository directorRepo) {
		this.movieRepo = movieRepo;
		this.directorRepo = directorRepo;
	}

	public void add(MovieDTO dto,List<DirectorDTO> directorDTOList) {
	
		try {
			
			System.out.println("========INSERT EXECUTION======");
			
			//check for unique Movie title
			Optional<Movie> movieCheck = movieRepo.findByMovie_Title(dto.getMovie_Title());
			
			if(!movieCheck.isEmpty()) {
				throw new Exception("Movie already exists || try another");
			}
			//convert DTO TO entity
			Movie movie  = MovieDTO.convertDTOtoEntity(dto);
			
			
	        List<Director> directors  = (List<Director>)directorDTOList.stream().
	        		map(d->{
	        	Director directorInMap =DirectorDTO.convertDTOToEntity(d);
	        	return directorInMap; }).collect(Collectors.toList());
	        System.out.println("directors through map "+directors);
			
			movie.setDirectors(directors);
			movieRepo.save(movie);
			System.out.println("INSERTED SUCCESSFULLY -> "+movie);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		
		
	}

	@Override
	public void searchBasedOnTitle(String title) {
		// TODO Auto-generated method stub
		System.out.println("=========SEARCH RESULT  (Searching for "+title+")==========");
		try {
			
			List<Movie> movieList = movieRepo.findByMovie_TitleLike(title);
			if(movieList.isEmpty()) {
				throw new Exception("Invalid Movie Title");
			}
			movieList.stream().forEach(System.out::println);
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		
		
	    
	}

	@Override
	public void updateReleaseDate(LocalDate releaseDate, String movieTitle) {
		// TODO Auto-generated method stub
	   System.out.println("=========UPDATE==========");
		try {
			
	
			Optional<Movie> movieCheck = movieRepo.findByMovie_Title(movieTitle);	
			if(movieCheck.isEmpty()) {
				throw new Exception("Invalid");
			}
			
			Integer affected = movieRepo.updateMovieWithGivenReleaseDate(releaseDate, movieTitle);
			System.out.println("total Rows updated "+affected);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteBasedOnMovieTitle(String Title) {
		// TODO Auto-generated method stub
		
		
		try {
			Optional<Movie> movieCheck = movieRepo.findByMovie_Title(Title);	
			if(movieCheck.isEmpty()) {
				throw new Exception("Invalid");
			}
			

			movieRepo.deleteByMovie_Title(Title);
			System.out.println("Deleted movie with title "+Title);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Movie> displayAll() {
		
        System.out.println("=====================Displaying All Movies================");
		return movieRepo.findAll();
	}

	@Override
	public void getDirectorListFromTitle(String title) {

		try {
			Optional<Movie> movie = movieRepo.findByMovie_Title(title);
		    if(movie.isEmpty()) {
		    	throw new Exception("Movie doesn't exists");
		    }
			System.out.println("DIRECTOR LIST FOR Movie="+title);
		    movie.get().getDirectors().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void getMovieListFromDirectorName(String name) {
		
		Optional<Director> director = directorRepo.findByFirstName(name);
		System.out.println("MOVIE LIST FOR DIRECTOR ="+name);
		if(!director.isEmpty()) director.get().getMovies().stream().forEach(System.out::println);

	}

	@Override
	public void updateDirectorDetails(DirectorDTO dto) {
		
		try {
		Optional<Director> optDirector = directorRepo.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
			
			System.out.println("check Director"+optDirector.get());
			
		
	         if(optDirector.isEmpty()) {
	        	 throw new Exception("director doesn't exist");
	         }
	         Director director = optDirector.get();
	         director.setAddress(dto.getAddress());
	         director.setEmail(dto.getEmail());
	         director.setContactNumber(dto.getContactNumber());
	         directorRepo.save(director);
	         
	         System.out.println("UPDATED");
	         
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
