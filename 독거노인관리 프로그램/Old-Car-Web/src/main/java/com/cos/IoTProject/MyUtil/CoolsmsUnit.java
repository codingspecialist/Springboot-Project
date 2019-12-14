package com.cos.IoTProject.MyUtil;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;

public class CoolsmsUnit {
	  public static void MessageTest(String managerphone, String warning) {
		  String api_key = "NCSPPPTGGFEZBXTT";
		  String api_secret = "IX8KX9NVSXDHPM5KKOBV8HDA7YSYWGPY";
		  Message message = new Message(api_key, api_secret);
		  
		  HashMap<String, String> params = new HashMap<String, String>();
		      // send					
		      params.put("to", managerphone);
		      params.put("from", "01064101832");
		      params.put("type", "SMS");
		      params.put("text", warning);
		      params.put("app_version", "test app 1.2");
		      try {
		          JSONObject obj = (JSONObject) message.send(params);
		          System.out.println(obj.toString());
		        } catch (Exception e) {
		          System.out.println(e.getMessage());
		        }
		  }
}
