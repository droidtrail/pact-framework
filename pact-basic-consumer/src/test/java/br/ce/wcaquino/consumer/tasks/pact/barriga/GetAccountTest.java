package br.ce.wcaquino.consumer.tasks.pact.barriga;

import java.io.IOException;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.core.model.RequestResponsePact;

public class GetAccountTest extends ConsumerPactTest {
	private final String TOKEN = "JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTM2MTZ9.WbVNx3HkEcC9mGDxNeuAzGbtir1BoA3aSHfMHBNxIB8";
	@Override
	protected RequestResponsePact createPact(PactDslWithProvider builder) {
			DslPart body = LambdaDsl.newJsonArrayMinLike(1,(arr)->){
				
			}
		 
		
		return builder
				.given("Thre is at least one account")
				.uponReceiving("Retrieve the user's account")
					.path("/contas")
					.method("GET")
					.headers("Authorization", TOKEN)
				.willRespondWith()
					.status(200)
					.body(body)
				;
	}

	@Override
	protected String providerName() {
		
		return "Barriga";
	}

	@Override
	protected String consumerName() {
		
		return "BasicConsumer";
	}

	@Override
	protected void runTest(MockServer mockServer, PactTestExecutionContext context) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
