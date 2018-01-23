package com.example.movieRecommender;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.movieRecommender.json.pojo.Output;

@Service
public class MovieRecommderService {
	
	/*public static void main(String[] args) {
		new MovieRecommderService().fetchResults();
	}*/
	
	public Output fetchResults(String requestJson){ 
	   
	   final String uri = "https://ussouthcentral.services.azureml.net/workspaces/3526fde061f043b68a26f61b5405e501/services/bfb6c44137e942e3a4282574d33d4d39/execute?api-version=2.0&details=true";
	   final String apikey = "yE4poSua+i4yGEV3h7Ae3Qv8Z/RNwVSPUDovR7iFZGnsMo2jWNlR3JC7cSpfFBYyg1CHwllu4qzUxGgPQWWwNg==";
	   RestTemplate restTemplate = new RestTemplate();
	   
	   //String requestJson = "{  \"Inputs\": {  \"input1\": {     \"ColumnNames\": [      \"UserId\",    \"MovieId\",       \"Rating\",      \"Timestamp\"     ],     \"Values\": [        [    \"28000\",       \"68646\",       \"10\",         \"1381620027\"     ]    ] }  }, \"GlobalParameters\": {}}";
	   HttpHeaders headers = new HttpHeaders();
	   headers.setContentType(MediaType.APPLICATION_JSON);
	   ArrayList<MediaType> listOfMediaTypes = new ArrayList<MediaType>();
	   listOfMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
	   headers.setAccept(listOfMediaTypes);
	   headers.set("Authorization", ("Bearer "+apikey));
	   
	   HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
	   //HttpEntity request = new HttpEntity(headers);
	  // List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	   //MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
	   //messageConverters.add(map);
	   //messageConverters.add(new FormHttpMessageConverter());
	   //restTemplate.setMessageConverters(messageConverters);
	   Output finalResult = restTemplate.postForObject(uri, request, Output.class);
	   
	   
	   //ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	   
	    
	   System.out.println(finalResult.getResults().getOutput1().getValue().getValues());
	   return finalResult;
	}
	

}
