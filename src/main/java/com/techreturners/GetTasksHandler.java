package com.techreturners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.techreturners.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetTasksHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(GetTasksHandler.class);

	private Connection connection= null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Object ResultSet;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("received: the request");
		List<Task> tasks = new ArrayList<>();

		String userId = request.getPathParameters().get("userID");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
					"tasks-db.cqklo55dbmqm.eu-west-2.rds.amazonaws.com",
					"tasks",
					"root",
					"Mstyrs7&"));

			preparedStatement = connection.prepareStatement( "SELECT * FROM task WHERE userID = ?");
			preparedStatement.setString( 1, userID);
			ResultSet = preparedStatement.executeQuery();
			while (ResultSet.next()) {
				Task task = new Task(ResultSet.getString('taskID'),
						ResultSet.getString('description'),
						ResultSet.getString('completed'));
				tasks.add(task);

			}
		}
		catch (Exception e) {
			LOG.error(String.format("enable to querry our database for tasks for user %s",userID, e);
		}
		finally{
			
		}
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String responseBody = objectMapper.writeValueAsString(tasks);
			response.setBody(responseBody);
		}
		catch(JsonProcessingException e) {
			LOG.info("unable to marshall Tasks array", e);
		}
	
		return response;
	}
}
