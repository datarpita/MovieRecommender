package com.example.movieRecommender.pojo;

public class Movie {
	
	public Movie(String movieId, String movieTitle) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
	}
	private String movieId;
	private String movieTitle;
	
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
}
