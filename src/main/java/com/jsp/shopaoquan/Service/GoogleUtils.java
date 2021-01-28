package com.jsp.shopaoquan.Service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.shopaoquan.Entity.GooglePojo;
import com.jsp.shopaoquan.Entity.customer;
@Component
public class GoogleUtils {
	public static String GOOGLE_CLIENT_ID = "894453835091-ngnssakc3q9n2dnj6qv1h950o1m7d21k.apps.googleusercontent.com";
	  public static String GOOGLE_CLIENT_SECRET = "Y0K0FuorYiBpJBnzUvLyMZ0w";
	  public static String GOOGLE_REDIRECT_URI = "https://brandclothes.herokuapp.com/login-google";
	  public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	  public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	  public static String GOOGLE_GRANT_TYPE = "authorization_code";
	  public String getToken(final String code) throws ClientProtocolException, IOException {
	    String response = Request .Post(GOOGLE_LINK_GET_TOKEN)
	        .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
	            .add("client_secret", GOOGLE_CLIENT_SECRET)
	            .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
	            .add("grant_type", GOOGLE_GRANT_TYPE).build())
	        .execute().returnContent().asString();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode node = mapper.readTree(response).get("access_token");
	    return node.textValue();
	  }
	  public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
	    String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
	    String response = Request.Get(link).execute().returnContent().asString();
	    ObjectMapper mapper = new ObjectMapper();
	    GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
	    System.out.println(googlePojo);
	    return googlePojo;
	  }
	  public customer buildUser(GooglePojo googlePojo) {
	    customer userDetail = new customer(googlePojo.getId(),
	        "Da Nang", googlePojo.getEmail(),11111 , googlePojo.getId(), googlePojo.getEmail());
	    return userDetail;
	  }

}
