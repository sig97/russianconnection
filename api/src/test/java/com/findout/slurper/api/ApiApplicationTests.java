package com.findout.slurper.api;

import com.findout.slurper.api.domain.OrderData;
import com.findout.slurper.api.service.BigpipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("norabbit")
public class ApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	BigpipeService service;

	@MockBean
	RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void pingShouldReturnOK() {
		ResponseEntity<String> response = restTemplate.getForEntity("/ping", String.class);

		assertNotNull(response);
		assertEquals("OK", response.getBody());
	}

	@Test
	public void dataShouldCallBigpipeService() {

		OrderData orderData = new OrderData("orderid", 1.2, "hej");
		Mockito.when(service.sendOrder(orderData)).thenReturn("SENT");

		ResponseEntity<String> response = restTemplate.postForEntity("/order", orderData, String.class);

		assertEquals("{\"responseString\": \"SENT\"}", response.getBody());

		Mockito.verify(service).sendOrder(orderData);

	}

}

