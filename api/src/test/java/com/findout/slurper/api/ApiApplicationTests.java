package com.findout.slurper.api;

import com.findout.slurper.api.domain.OrderData;
import com.findout.slurper.api.service.BigpipeService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = "spring.rabbitmq.listener.auto-startup=false")
public class ApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	BigpipeService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void pingShouldReturnOK() {
		ResponseEntity<String> response = restTemplate.getForEntity("/ping", String.class);

		assertNotNull(response);
		assertEquals("OK", response.getBody());
	}

	@Ignore
	public void dataShouldCallBigpipeService() {
		OrderData orderData = new OrderData("orderid", 1.2, "hej");
		Mockito.when(service.sendOrder(orderData)).thenReturn("SENT");

		ResponseEntity<String> response = restTemplate.postForEntity("/order", orderData, String.class);

		assertEquals("{\"responseString\": \"SENT\"}", response.getBody());

		Mockito.verify(service).sendOrder(orderData);

	}

}

