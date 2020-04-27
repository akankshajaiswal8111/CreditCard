import java.util.List;

import org.json.JSONException;

public abstract class CCBaseStrategy {
	
  public abstract List<Long> readFile(String file) throws Exception;
  public abstract void writeFile(String file, List<CardDetails> cd);

}
