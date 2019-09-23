package app.json;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import app.config.CacheEventLogger;

@Component
public class JsonHelper {

	private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);
	HttpURLConnection conn;
	
	@CachePut(
            value = "getJsonByUrlCache",
            key = "#urlStr")
	public String getJsonByUrlRefresh(String urlStr) {
		log.info("Начато обновление кеша из источника {}",urlStr);
		return getJson(urlStr);
	}
	
	@Cacheable(
            value = "getJsonByUrlCache",
            key = "#urlStr")
	public String getJsonByUrl(String urlStr) {
		log.info("Начато получение данных из источника {}",urlStr);
		return getJson(urlStr);
	}
	
	private String getJson(String urlStr) {
		try {
			URL url= new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String jsonStr = IOUtils.toString(conn.getInputStream(), "UTF-8");
			log.info("Данные из источника {} получены успешно.",urlStr);
			conn.disconnect();
			return jsonStr;
		} catch (Exception e) {
			log.info("При получении данных из источника {} произошло исключение: {}.",urlStr, e.getMessage());
			e.printStackTrace();
			if (conn != null)
				conn.disconnect();
			return null;
		}
	}
	
}
