package com.activiti.book;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class Process_Deployment {

	private static String REST_URI = "http://localhost:8080/activiti-rest/service";

	private static ClientResource getClientResource(String uri) {
		ClientResource resource = new ClientResource(uri);
		resource.setChallengeResponse(ChallengeScheme.HTTP_BASIC, "kermit",
				"kermit");
		return resource;
	}

	public static JSONArray getProcessDefinitions() throws JSONException,
			IOException {
		String uri = REST_URI + "/repository/process-definitions";
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			JSONArray processArray = (JSONArray) object.get("data");
			return processArray;
		}
		return null;
	}

	public static JSONObject getProcessDefinitionById(String processId)
			throws JSONException, IOException {
		String uri = REST_URI + "/repository/process-definitions/" + processId;
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject updateProcess(String processId)
			throws JSONException, IOException {

		String uri = REST_URI + "/repository/process-definitions/" + processId;
		JSONObject updateData = new JSONObject();
		updateData.put("category", "Updated Category");
		Representation response = getClientResource(uri).put(updateData);

		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject suspendProcessDefination(String id)
			throws JSONException, IOException {
		String uri = REST_URI + "/repository/process-definitions/" + id;

		JSONObject my_data = new JSONObject();
		my_data.put("action", "suspend");
		my_data.put("includeProcessInstances", "false");

		Representation response = getClientResource(uri).put(my_data);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {

			return object;
		}
		return null;
	}

	public static JSONObject addCandidate(String id) throws JSONException,
			IOException {
		String uri = REST_URI + "/repository/process-definitions/" + id
				+ "/identitylinks";
		JSONObject my_data = new JSONObject();
		my_data.put("user", "kermit");

		Representation response = getClientResource(uri).post(my_data);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject startprocessInstance(String id, String businesskey)
			throws JSONException, IOException {
		String uri = REST_URI + "/runtime/process-instances";

		JSONObject my_data = new JSONObject();
		my_data.put("processDefinitionId", id);
		my_data.put("businessKey", businesskey);

		JSONArray variables = new JSONArray();

		JSONObject custname = new JSONObject();
		custname.put("name", "customerName");
		custname.put("value", "Raj");
		variables.put(custname);

		JSONObject profit = new JSONObject();
		profit.put("name", "potentialProfit");
		profit.put("value", "2500");
		variables.put(profit);
		my_data.put("variables", variables);
		Representation response = getClientResource(uri).post(my_data);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {

			return object;
		}
		return null;
	}

	public static JSONArray getTasks() throws JSONException, IOException {
		String uri = REST_URI + "/runtime/tasks";
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			JSONArray taskArray = (JSONArray) object.get("data");
			return taskArray;
		}
		return null;

	}

	public static void claimTask(int id) throws JSONException, IOException {
		String uri = REST_URI + "/runtime/tasks/" + id;

		JSONObject my_data = new JSONObject();
		my_data.put("action", "claim");
		my_data.put("assignee", "kermit");

		Representation response = getClientResource(uri).post(my_data);

	}

	public static JSONObject createUser() throws JSONException, IOException {
		String uri = REST_URI + "/identity/users";

		JSONObject my_data = new JSONObject();
		my_data.put("id", "irshad");
		my_data.put("firstName", "Irshad");
		my_data.put("lastName", "Mansuri");
		my_data.put("email", "irshad@localhost.com");
		my_data.put("password", "irshad");
		Representation response = getClientResource(uri).post(my_data);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject createGroup() throws JSONException, IOException {
		String uri = REST_URI + "/identity/groups";

		JSONObject my_data = new JSONObject();
		my_data.put("id", "attunegroup");
		my_data.put("name", "Attune Group");
		my_data.put("type", "security-role");
		Representation response = getClientResource(uri).post(my_data);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject addGroupMember(String id) throws JSONException,
			IOException {
		String uri = REST_URI + "/identity/groups/" + id + "/members";

		JSONObject my_data = new JSONObject();
		my_data.put("userId", "irshad");

		Representation response = getClientResource(uri).post(my_data);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static void deleteGroupMember(String groupId, String userId)
			throws JSONException, IOException {
		String uri = REST_URI + "/identity/groups/" + groupId + "/members/"
				+ userId;
		Representation response = getClientResource(uri).delete(MediaType.ALL);
	}

	public static JSONObject getDbTables(String name) throws JSONException,
			IOException {
		String uri = REST_URI + "/management/tables/" + name;
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);

		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject getDbTablesColumn(String name)
			throws JSONException, IOException {
		String uri = REST_URI + "/management/tables/" + name + "/columns";
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);

		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject getEngineProperty() throws JSONException,
			IOException {
		String uri = REST_URI + "/management/properties";
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);

		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static JSONObject getEngineInfo() throws JSONException, IOException {
		String uri = REST_URI + "/management/engine";
		Representation response = getClientResource(uri).get(
				MediaType.APPLICATION_JSON);

		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}
}