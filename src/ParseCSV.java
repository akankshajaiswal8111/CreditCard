import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class ParseCSV extends CCBaseStrategy {

		@Override
		public List<Long> readFile(String file) throws Exception{
			final String delimiter = ",";
			List<Long> list = new ArrayList<Long>();
			try {
		         File file1 = new File(file);
		         FileReader fr = new FileReader(file1);
		         BufferedReader br = new BufferedReader(fr);
		         String line = "";
		         String[] tempArr;
		         if(br.readLine() != null) {
		        	 System.out.println("Reading header");
		         }
		         while((line = br.readLine()) != null) {
		            tempArr = line.split(delimiter);
		            if(tempArr[0] == null || tempArr[0].equals(" ") || tempArr[0].equals(""))
		            	list.add(0L);
		            
		            else list.add(Long.parseLong(tempArr[0]));
		         }
		         
		         br.close();
		         if(list.size() == 0) throw new Exception(); 
		         } catch(IOException ioe) {
		            ioe.printStackTrace();
		         }
			return list;
		}


		@Override
		public void writeFile(String file, List<CardDetails> cd) {
			PrintWriter pw;
	        try {
	            pw = new PrintWriter(new File(file));
	 
	            StringBuffer csvHeader = new StringBuffer("");
	            StringBuffer csvData = new StringBuffer("");
	            csvHeader.append("CardNumber,CardType,Error\n");
	 
	            // write header
	            pw.write(csvHeader.toString());
	            
	 
	            // write data
	            for(int i =0 ; i<cd.size(); i++) {
	            	CardDetails	currCard = cd.get(i);
	            	pw.write((String.valueOf(currCard.cardNumber)));
	            	pw.write(",");
	            	pw.write(currCard.cardType);
	            	pw.write(",");
	            	
	            	if(!currCard.cardType.equals("Invalid")) {
	            		pw.write("None");
	            	}
	            	else {
	            		pw.write("Invalid Card Number");
	            	}
	            	pw.write("\n");	
	            	
	            }
	            pw.close();
	            	
	            } catch(Exception e) {
	            	e.printStackTrace();
	            }
	        
	            
			
		}
   }