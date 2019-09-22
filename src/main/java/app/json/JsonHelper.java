package app.json;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class JsonHelper {

	HttpURLConnection conn;
	
	public String getJsonByUrl(String urlStr) {
		try {
			URL url= new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String jsonStr = IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
			conn.disconnect();
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null)
				conn.disconnect();
			return null;
		}
	}
}
