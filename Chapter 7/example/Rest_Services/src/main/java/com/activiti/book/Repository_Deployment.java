package com.activiti.book;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class Repository_Deployment {

	private static String REST_URI = "http://localhost:8080/activiti-rest/service";

	private static ClientResource getClientResource(String uri) {
		ClientResource resource = new ClientResource(uri);
		resource.setChallengeResponse(ChallengeScheme.HTTP_BASIC, "kermit",
				"kermit");
		return resource;
	}

	public static JSONArray getDeployments() throws JSONException, IOException {
		String deploymentURI = REST_URI + "/repository/deployments";
		Representation response = getClientResource(deploymentURI).get(
				MediaType.APPLICATION_JSON);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			JSONArray dataArray = (JSONArray) object.get("data");
			return dataArray;

		}
		return null;
	}

	public static JSONObject getDeployments(int id) throws JSONException,
			IOException {
		String deploymentURI = REST_URI + "/repository/deployments/" + id;
		Representation response = getClientResource(deploymentURI).get(
				MediaType.APPLICATION_JSON);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}

	public static void deleteDeployment(int id) {
		String deploymentURI = REST_URI + "/repository/deployments/" + id;
		Representation response = getClientResource(deploymentURI).delete(
				MediaType.ALL);
	}

	public static JSONArray getDeploymentResources(int id) throws IOException,
			JSONException {
		String deploymentURI = REST_URI + "/repository/deployments/" + id
				+ "/resources";
		Representation response = getClientResource(deploymentURI).get(
				MediaType.APPLICATION_JSON);

		String json = "{data :" + response.getText() + "}";

		JSONObject object = new JSONObject(json);

		if (object != null) {
			JSONArray arr = (JSONArray) object.get("data");
			return arr;
		}
		return null;
	}

	public static JSONObject getDeploymentResourcesById(int id,
			String resourceId) throws JSONException, IOException {
		String deploymentURI = REST_URI + "/repository/deployments/" + id
				+ "/resources/" + resourceId;
		Representation response = getClientResource(deploymentURI).get(
				MediaType.APPLICATION_JSON);
		JSONObject object = new JSONObject(response.getText());
		if (object != null) {
			return object;
		}
		return null;
	}
}