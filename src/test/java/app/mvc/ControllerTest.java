package app.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void testHintPhoneCode() throws Exception {
        this.mockMvc.perform(get("/rest/code?country=be"))
        	.andDo(print())
            .andExpect(status().isOk());
    }
	
	@Test
    public void testRefreshCache() throws Exception {
        this.mockMvc.perform(get("/rest/code/refresh"))
        	.andDo(print())
            .andExpect(status().isOk());
    }
}
