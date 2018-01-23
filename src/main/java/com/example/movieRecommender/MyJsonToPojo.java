package com.example.movieRecommender;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class MyJsonToPojo {
	
	public static void main(String[] args) {  
        String packageName="com.example.movieRecommender.pojo";  
        /*File inputJson= new File("C:/Arpita_Datta/My Projects/Machine_Learning_projects/workspace_ML/MovieRecommender/src/main/java/com/example/movieRecommender"+
        File.separator+"input.json"); */
        /*File outputJson= new File("C:/Arpita_Datta/My Projects/Machine_Learning_projects/workspace_ML/MovieRecommender/src/main/java/com/example/movieRecommender"+
                File.separator+"output.json");*/  
        File outputJson= new File("C:/Arpita_Datta/My_Projects/Machine_Learning_projects/workspace_ML/MovieRecommender/src/main/java/com/example/movieRecommender"+
                File.separator+"input_recommend.json");
        File outputPojoDirectory=new File("C:/Arpita_Datta/My_Projects/Machine_Learning_projects/workspace_ML/MovieRecommender/src/main/java/com/example/movieRecommender"+File.separator+"convertedPojo");  
        outputPojoDirectory.mkdirs();  
        try {  
             //new MyJsonToPojo().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));  
        	new MyJsonToPojo().convert2JSON(outputJson.toURI().toURL(), outputPojoDirectory, packageName, outputJson.getName().replace(".json", ""));
        } catch (IOException e) {  
             // TODO Auto-generated catch block  
             System.out.println("Encountered issue while converting to pojo: "+e.getMessage());  
             e.printStackTrace();  
        }  
   }  
   public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{  
	   JCodeModel codeModel = new JCodeModel();  
	   URL source = inputJson;  
	   GenerationConfig config = new DefaultGenerationConfig() {  
		   @Override  
		   public boolean isGenerateBuilders() { // set config option by overriding method  
			   return true;  
		   }  
		   public SourceType getSourceType(){  
			   return SourceType.JSON;  
		   }  
	   };  
	   SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());  
	   mapper.generate(codeModel, className, packageName, source);  
	   codeModel.build(outputPojoDirectory);  
   } 

}
