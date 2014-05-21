import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class ParamsMatch {
//	@Test
	public void test() {
		Pattern pattern = Pattern.compile("(.*)\\((.*)\\)");
		Matcher matcher = pattern
				.matcher("a({c:1,withe:[221.122.127.10,221.122.127.90],black:192.168.5.1})");
		if (matcher.find()) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
				System.out.println(matcher.group(i));
			}
		}
	}

	@Test
	public void test1() {
		ObjectMapper mapper =new org.codehaus.jackson.map.ObjectMapper();
		try {
			Map map = mapper.readValue("{\"c\":1,\"withe\":[221],\"black\":[192]}", HashMap.class);
			System.out.println(map);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
