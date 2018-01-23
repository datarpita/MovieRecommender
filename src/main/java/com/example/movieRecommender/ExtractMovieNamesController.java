package com.example.movieRecommender;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;


@Controller
public class ExtractMovieNamesController {
	
	//private static ArrayList<String> listOfMovies = new ArrayList<String>();
	
	/*static{
		String imdbMovieFilePath="IMDB_Movie_Titles.csv";
		
		HashMap<String, String> movieMap = readCSVFile(imdbMovieFilePath);
		
		System.out.println("Size of moviemap: " + movieMap.size());		
		
		Set<Entry<String, String>> entrySet = movieMap.entrySet();
		for (Entry<String, String> eachKey : entrySet){
			listOfMovies.add(eachKey.getValue());
		}
	}*/
	@Autowired
	DataDAO daoObj;

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void fetchAllMovies(HttpServletRequest req, HttpServletResponse resp){
		try {
			resp.setContentType("application/json");
			resp.setHeader("Cache-control", "no-cache, no-store");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Expires", "-1");
			
			
			String query = req.getParameter("term");
	        System.out.println("term===="+ query);
	        query = query.toLowerCase();
	        ArrayList<String>  searchArrayList = daoObj.getMovieNames(query);
	        
	        /*ArrayList<String>  searchArrayList = new ArrayList<String>();
			for(int i=0; i<listOfMovies.size(); i++) {
	            String name = listOfMovies.get(i).toLowerCase();
	            if(name.startsWith(query)) {
	            	searchArrayList.add(listOfMovies.get(i));
	            }
	        }*/
			
			
			
			String searchList = new Gson().toJson(searchArrayList);
			System.out.println("SearchList:  " + searchList);
			resp.getWriter().println(searchList);
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}
	
	
	
	/*public static HashMap<String, String> readCSVFile(String fullFilePath){
		
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, String> listOfMovies = new HashMap<String, String>();
        File f = new File(ExtractMovieNamesController.class.getResource(fullFilePath).getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] movieArray = line.split(cvsSplitBy);
                //Movie m = new Movie(movieArray[0], movieArray[1]);
                listOfMovies.put(movieArray[0], movieArray[1]);              
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return listOfMovies;
	}*/	
	
}
