package i.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import i.bankapp.controller.AccountController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class IBankAppApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	AccountController accountController;
	
	@Test
	void createAccountsThroughAllLayer() throws Exception{
		String inputJson = "{\"acctID\": \"1\",\"accountName\": \"Vinod\",\"phoneNumber\": \"12345\",\"eMail\": \"vinod@ibank.com\",\"acctStatus\": \"Active\" }";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/iBank/account")
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}

	@Test
	void fetchbyAccountId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/account/1");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	@Test
	void deletebyAccountId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/account/1");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	@Test
	void createAccountBalanceThroughAllLayer() throws Exception {
		String inputJson = "{\"acctID\": \"1\",\"balance\": \"10000\" }";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/iBank/accountbalance")
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	@Test
	void fetchbyAccountBalanceId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/accountbalance/1");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	
}
