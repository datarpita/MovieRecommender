package com.example.movieRecommender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.movieRecommender.json.pojo.Input;
import com.example.movieRecommender.json.pojo.Input1;
import com.example.movieRecommender.json.pojo.InputRecommend;
import com.example.movieRecommender.json.pojo.Inputs;
import com.example.movieRecommender.json.pojo.Movie;
import com.example.movieRecommender.json.pojo.Output;
import com.google.gson.Gson;

@Controller
public class RecommendMovieController {
	
	@Autowired
	private MovieRecommderService service;
	@Autowired
	private DataDAO daoObj;
	
	@RequestMapping(value="/recommend", method=RequestMethod.POST)
	public void recommend(HttpServletRequest request, HttpServletResponse response){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if(br != null){
			    json = br.readLine();
			}
			System.out.println("Json=="+json);
			
			InputRecommend obj = new Gson().fromJson(json, InputRecommend.class);
			System.out.println("Movies------"+ obj.getMovies());
			String requestJson = convertToServiceInp(obj);
			Output outputObj = service.fetchResults(requestJson);
			System.out.println("Original response-->"+ new Gson().toJson(outputObj));
			List<List<String>>  recomMovieList = outputObj.getResults().getOutput1().getValue().getValues();
			ArrayList<String> recomMovArr = new ArrayList<String>();
			for (List<String> strList : recomMovieList){
				for (int index = 1 ; index < strList.size(); index++){
					recomMovArr.add(strList.get(index));
				}
			}
			String responseJson = new Gson().toJson(recomMovArr);
			System.out.println("Final Response Json:  " + responseJson);
			
			response.setContentType("application/json");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");			
			response.getWriter().println(responseJson);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	private String convertToServiceInp(InputRecommend inpRecObj){
		String result = null;
		
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("UserId");
		columnNames.add("MovieId");
		columnNames.add("Rating");
		columnNames.add("Timestamp");
		Input1 input1Obj = new Input1();
		input1Obj.setColumnNames(columnNames);
		
		List<List<String>> finalSet = new ArrayList<List<String>>();
		for (Movie m : inpRecObj.getMovies()){
			ArrayList<String> singleSet = new ArrayList<String>(); 
			singleSet.add("1"); //userid			
			singleSet.add(daoObj.getMovieId(m.getName())); // movie name
			singleSet.add(m.getRating()); //rating
			singleSet.add("1381620027"); //timestamp
			finalSet.add(singleSet);
		}		
		input1Obj.setValues(finalSet);
		Inputs inputsObj = new Inputs();
		inputsObj.setInput1(input1Obj);
		Input inputObj = new Input();
		inputObj.setInputs(inputsObj);
		
		result = new Gson().toJson(inputObj);
		System.out.println("Request Json-->" + result);
		
		return result;
	}

}
