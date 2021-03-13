package br.ce.wcaquino.consumer.tasks.pact.barriga;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import br.ce.wcaquino.consumer.barriga.service.BarrigaConsumer;

public class LoginTest {
	
	@Rule
	public PactProviderRule mockProvider = new PactProviderRule("Barriga", this);
	
	@Pact(consumer = "BasicConsumer")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
		PactDslJsonBody requestBody = new PactDslJsonBody()
				.stringType("email","cursopact@gmail.com")
				.stringType("senha","123456");
		
		PactDslJsonBody responseBody = new PactDslJsonBody()
				.stringType("token","cursopact@gmail.com");
		
		return builder
				.given("Your user is created")
				.uponReceiving("Signin with a valid user")
					.path("/signin")
					.method("POST")
					.body(requestBody)
				.willRespondWith()
					.status(200)
					.body(responseBody)
				.toPact();
	}
	
	@Test
	@PactVerification
	public void shouldSignin() throws IOException {
		
		BarrigaConsumer consumer = new BarrigaConsumer(mockProvider.getUrl());
		String token = consumer.login("cursopact@gmail.com", "123456");
		Assert.assertThat(token, CoreMatchers.is(CoreMatchers.notNullValue()));
		
	}
}
