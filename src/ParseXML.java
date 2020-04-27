import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class ParseXML extends CCBaseStrategy {


	@Override
	public List<Long> readFile(String file) throws Exception{
		//System.out.println(file);
		List<Long> list = new ArrayList<Long>();
		Element eElement = null; 
	
		try   
		{  
		
			//creating a constructor of file class and parsing an XML file  
			File file1 = new File(file);  
			//an instance of factory that gives a document builder  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			//an instance of builder to parse the specified xml file  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc=null;
			try {
			doc = db.parse(file1);  
			} catch(Exception e) {
				System.out.println("Empty xml. Suspending...");
				
				System.exit(0);
			}
			doc.getDocumentElement().normalize();  
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
			NodeList nodeList = doc.getElementsByTagName("row");  
			// nodeList is not iterable, so we are using for loop  
			for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
				Node node = nodeList.item(itr);  
  
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					eElement = (Element) node;  
					try {
					String str = eElement.getElementsByTagName("CardNumber").item(0).getTextContent();
					
					if(str==null || str.contentEquals(" ") || str.equals("")) list.add(0L);
					else list.add(Long.parseLong(eElement.getElementsByTagName("CardNumber").item(0).getTextContent()));
					} catch(Exception e) {
						System.out.println("Incorrect tag name. Suspending...");
						System.exit(0);
					}
 
	 
				}  
			}
			
			 
		}   
		catch (Exception e)   
		{  
			e.printStackTrace();  
		} 
	return list;
	}

	@Override
	public void writeFile(String file, List<CardDetails> cd) {
		try {
		 
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("root");
			document.appendChild(root);
			for(int i = 0; i <cd.size(); i++) {
				// row element
				Element row = document.createElement("row");

				root.appendChild(row);
			
				CardDetails	currCard = cd.get(i);
				// CardNumber element
				Element cardNumber = document.createElement("CardNumber");
				cardNumber.appendChild(document.createTextNode(String.valueOf(currCard.cardNumber)));
				row.appendChild(cardNumber);

				// CardType element
				Element cardType = document.createElement("CardType");
				cardType.appendChild(document.createTextNode(currCard.cardType));
				row.appendChild(cardType);
				
				// Error element
					if(!currCard.cardType.equals("Invalid")) {
						
						Element error = document.createElement("Error");
						error.appendChild(document.createTextNode("None"));
						row.appendChild(error);
					}
					else {
						Element error = document.createElement("Error");
						error.appendChild(document.createTextNode("Invalid Card Number"));
						row.appendChild(error);
						}
			}
			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(file));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging 

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	} 
	
} 



