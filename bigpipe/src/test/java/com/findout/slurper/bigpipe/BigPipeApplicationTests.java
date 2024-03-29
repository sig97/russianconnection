package com.findout.slurper.bigpipe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = "spring.rabbitmq.listener.auto-startup=false")
public class BigPipeApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void contextLoads() {
	}

	@Test
	public void pingShouldReturnOK() {
		ResponseEntity<String> response = restTemplate.getForEntity("/ping", String.class);

		assertNotNull(response);
		Assert.assertEquals("OK", response.getBody());
	}

}

