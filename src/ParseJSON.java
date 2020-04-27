import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON extends CCBaseStrategy {
   
   public static String readInString(String args0) 
   {
       String content = "";

       try
       {
           content = new String ( Files.readAllBytes( Paths.get(args0) ) );
       } 
       catch (IOException e) 
       {
           e.printStackTrace();
       }

       return content;
   }

   @Override
	public List<Long> readFile(String file) throws Exception {
	   List<Long> list = new ArrayList<Long>();
	   try {
		   String str = readInString(file);
		   JSONArray arr = null;
		   try {
			   arr = new JSONArray(str);
		   } catch(Exception e) {
			   System.out.println("Empty Json file.Suspending...");
			   System.exit(0);
		   }
		   
		   for(int i = 0; i < arr.length(); i++) {
			   
			   
			   JSONObject obj = arr.getJSONObject(i);
			   Object  cn= obj.get("CardNumber");
			   //long checkValue = (Long) obj.get("CardNumber");
			   try {
				   long cNum = (Long) cn;
				   list.add(cNum);
			   } catch(Exception e)  {
				   list.add(0L);
			   }
			   
		   }
		   if(arr.length() == 0) {
			   System.out.println("JSON is invalid");
		   }
  		
       
	   } catch(Exception e) {
		  
		   e.printStackTrace();
	   }
	   return list;
   }

   @Override
   public void writeFile(String file, List<CardDetails> cd) {
	   
	   
	   JSONArray jsonCardList = new JSONArray();
	   JSONArray result = new JSONArray();
	   String str = "CardNumber, CardType, Error \n";

			  
			 
	   for(int i =0; i<cd.size(); i++) {
		   String error=null;
		   CardDetails currCard = cd.get(i);
		   if(!currCard.cardType.equals("Invalid")) {
			    error = "None";
		   } else error = "Invalid Card Number";
		   str+= currCard.cardNumber + "," +currCard.cardType +","+ error+ "\n";
		   
		   
//		   try {
//			   JSONObject jsonCard = new JSONObject();
//			   jsonCard.put("CardNumber", String.valueOf(currCard.cardNumber));
//			   jsonCard.put("CardType", currCard.cardType);
//			   if(!currCard.cardType.equals("Invalid")) {
//				   jsonCard.put("Error", "None");
//			   } else jsonCard.put("Error", "Invalid Card Number");
//			   
//			   jsonCardList.put(jsonCard);
//			   //jsonCardList.add(jsonCard);
//		   } catch (JSONException e) {
//		
//			   e.printStackTrace();
//		   }
		   
	   }
	   try {
			 result = CDL.toJSONArray(str);
		   } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	   
	   //Write JSON file
	   	try (FileWriter file1 = new FileWriter(file)) {
	   		String jsonString = result.toString();
	   		System.out.println(jsonString);
	   		file1.write(jsonString);
	   		file1.flush();

	   	} catch (IOException e) {
	   		e.printStackTrace();
	   	}
   }
	

} 
