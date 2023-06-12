package i.bankapp;

import static org.junit.Assert.assertTrue;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import i.bankapp.controller.AccountController;
import i.bankapp.dao.AccountsRepository;
import i.bankapp.model.Accounts;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class IBankAppApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private ObjectMapper objectMapper;
	
	@Autowired
	AccountController accountController;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Test
	void createAccountsThroughAllLayer() throws Exception{
		Accounts accounts = new Accounts(1, "vinod", 12345, "vinod@ibank.com", "Active");
		String inputJson = "{\"acctID\": \"1\",\"accountName\": \"Vinod\",\"phoneNumber\": \"12345\",\"eMail\": \"vinod@ibank.com\",\"acctStatus\": \"Active\" }";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/iBank/account")
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		System.out.println(httpServletResponse.getStatus());
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}

	@Test
	void fetchbyId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/account/1/balance");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		System.out.println(httpServletResponse.getStatus());
	}
	
	
}
