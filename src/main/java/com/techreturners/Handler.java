package com.techreturners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.techreturners.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("received: the request");
		Task t1 = new Task ("bcd1234", "Task Java", false);
		Task t2 = new Task ("bcd1235", "Task JavaScript", false);

		List<Task> tasks = new ArrayList<>();
		tasks.add(t1);
		tasks.add(t2);

		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);
		return response;
	}
}
