
public class StrategyPicker {
	public static CCBaseStrategy select(String file) throws Exception {
	String extension = file.substring(file.lastIndexOf(".") + 1, file.length());
	//System.out.println(extension);
        if (extension.equals("csv"))
        {
            return new ParseCSV();
        }
        else if (extension.equals("json"))
        {
            return new ParseJSON();
        }
        else if (extension.equals("xml"))
        {
            return new ParseXML();
        }

        throw new Exception("No strategy found for file type: " + file);
    }

}
