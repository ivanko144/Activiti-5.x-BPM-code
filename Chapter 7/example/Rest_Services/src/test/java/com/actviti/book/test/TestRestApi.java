package com.actviti.book.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.activiti.book.Process_Deployment;
import com.activiti.book.Repository_Deployment;

public class TestRestApi {

	// @Test
	// public void deploymentTest() throws JSONException, IOException {
	//
	// JSONArray procesArray = Repository_Deployment.getDeployments();
	// for (int i = 0; i < procesArray.length(); i++) {
	// JSONObject jasonObject = (JSONObject) procesArray.get(i);
	// assertNotNull(jasonObject);
	// System.out.println(jasonObject.toString());
	// }
	// }

	// @Test
	// public void deploymentById() throws JSONException, IOException {
	// JSONObject processObject = Repository_Deployment.getDeployments(23);
	// assertNotNull(processObject);
	// System.out.println(processObject.toString());
	// }

	// @Test
	// public void deleteDeployment() throws JSONException, IOException {
	// Repository_Deployment.deleteDeployment(23);
	// }

	// @Test
	// public void deploymentResource() throws JSONException, IOException {
	//
	// JSONArray processArray = Repository_Deployment
	// .getDeploymentResources(23);
	// assertNotNull(processArray);
	//
	// for (int i=0;i<processArray.length();i++){
	// JSONObject object = (JSONObject) processArray.get(i);
	// assertNotNull(object);
	// System.out.println(object.toString());
	// }
	// }

	// @Test
	// public void deploymentResourceById() throws JSONException, IOException {
	//
	// JSONObject processObject = Repository_Deployment
	// .getDeploymentResourcesById(23, "VacationRequest.png");
	// assertNotNull(processObject);
	// System.out.println(processObject.toString());
	// }

	// @Test
	// public void processDefinitons() throws Exception {
	// JSONArray processArray = Process_Deployment.getProcessDefinitions();
	// for (int i = 0; i < processArray.length(); i++) {
	// JSONObject jsonObject = (JSONObject) processArray.get(i);
	// assertNotNull(jsonObject);
	// System.out.println(jsonObject.toString());
	// }
	// }

	// @Test
	// public void processDefinitionById() throws Exception {
	// JSONObject jsonObject = Process_Deployment
	// .getProcessDefinitionById("createTimersProcess:1:37");
	// assertNotNull(jsonObject);
	// System.out.println(jsonObject.toString());
	// }

	// @Test
	// public void update() throws Exception {
	//
	// System.out.println("=== Before Updating Category ===");
	// JSONObject object1 = Process_Deployment
	// .getProcessDefinitionById("createTimersProcess:1:37");
	//
	// assertNotNull(object1);
	// System.out.println(object1.toString());
	// System.out.println("==== After Updating Category ===");
	//
	// Process_Deployment
	// .updateProcess("createTimersProcess:1:37");
	//
	// JSONObject object2 = Process_Deployment
	// .getProcessDefinitionById("createTimersProcess:1:37");
	// System.out.println(object2.toString());
	// }

	// @Test
	// public void suspedProcess() throws Exception {
	// JSONObject object = Process_Deployment
	// .suspendProcessDefination("createTimersProcess:1:37");
	// assertNotNull(object);
	// System.out.println(object.toString());
	// }

	// @Test
	// public void addUser() throws JSONException, IOException {
	// JSONObject jsonObject = Process_Deployment
	// .addCandidate("vacationRequest:1:36");
	//
	// assertNotNull(jsonObject);
	//
	// System.out.println(jsonObject.toString());
	// }

	// @Test
	// public void task() throws Exception {
	// JSONArray processArray = Process_Deployment.getTasks();
	// for (int i = 0; i < processArray.length(); i++) {
	//
	// JSONObject jsonObject = (JSONObject) processArray.get(i);
	// assertNotNull(jsonObject);
	//
	// System.out.println(jsonObject.toString());
	// }
	// }

	// @Test
	// public void createUser() throws Exception {
	// JSONObject object = Process_Deployment.createUser();
	// System.out.println(object.toString());
	// assertNotNull(object);
	// }

	// @Test
	// public void getSpecificTable() throws Exception {
	// JSONObject object = Process_Deployment.getDbTables("ACT_RU_VARIABLE");
	// assertNotNull(object);
	// System.out.println(object.toString());
	// }

	// @Test
	// public void getProperties() throws Exception {
	// JSONObject object = Process_Deployment.getEngineProperty();
	//
	// assertNotNull(object);
	// System.out.println(object.toString());
	// }

	@Test
	public void getEngineInfo() throws Exception {
		JSONObject object = Process_Deployment.getEngineInfo();
		assertNotNull(object);
		System.out.println(object.toString());
	}
}