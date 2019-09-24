package app.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.HttpURLConnection;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JsonTest {

	@Autowired
	JsonHelper jsonHelper;
	
	HttpURLConnection conn;
	
	@Test
    public void testJsonHelper() throws Exception {
		JSONObject namesJSON = new JSONObject(jsonHelper.getJsonByUrl("http://country.io/names.json"));
        JSONObject phoneJSON = new JSONObject(jsonHelper.getJsonByUrl("http://country.io/phone.json"));
        assertThat(namesJSON).isNotNull();
        assertThat(phoneJSON).isNotNull();
	}
}
