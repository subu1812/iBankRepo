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
import i.bankapp.dao.AccountsRepository;
import i.bankapp.dao.BeneficiaryRepository;
import i.bankapp.model.Beneficiary;
import i.bankapp.service.BeneficiaryService;

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
	
	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	BeneficiaryService beneficiaryService;
	
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
	
	@Test
	void createBeneficiaryThroughAllLayer() throws Exception {
		String inputJson = "{\"id\":\"3\",\"acctID\": \"3\",\"beneficiaryAccountId\": \"123094355\",\"beneficiaryIFSCCode\" : \"IFSC00001\",\"beneficiaryName\":\"Sample 1\",\"status\":\"Active\" }";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/iBank/account/1/addBeneficiaryAccount")
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	} 
	
	@Test
	void updatebyBeneficiaryId() throws Exception {
		String inputJson = "{\"id\":\"2\",\"acctID\": \"1\",\"beneficiaryAccountId\": \"123094357\",\"beneficiaryIFSCCode\" : \"IFSC00001\",\"beneficiaryName\":\"Sample 1\",\"status\":\"Active\" }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/iBank/account/1/updateBeneficiaryAccount/123094357").contentType(MediaType.APPLICATION_JSON).content(inputJson);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse);
	}
	
	//@Test
	void deletebyBeneficiaryId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/account/1/deleteBeneficiary");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	@Test
	void createTransactionThroughAllLayer() throws Exception {
		String inputJson = "{\"id\": \"4\",\"acctID\" :\"1\",\"date\" : \"2022-09-18\",\"type\" : \"Credit\",\"amount\": \"1000\",\"status\" : \"Success\",\"remarks\" : \"\"}";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/iBank/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	@Test
	void fetchTrasactionbyAcccountId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/transactions/1");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	@Test
	void fetchAccountSummarybyAcccountId() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/iBank/account/1/accountSummary");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(),httpServletResponse.getStatus());
	}
	
	
}
