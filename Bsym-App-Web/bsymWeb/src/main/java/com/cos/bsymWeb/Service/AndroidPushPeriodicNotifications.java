package com.cos.bsymWeb.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson() throws JSONException{
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"fu8mrrwU29Y:APA91bFJiQGx6WP8c2xZCdzhbg4qfRno9_4trMWifal7QYh9thZSeCBzjl1PS7axTr0TXzs9wYLgBPXdcsxtt8XJ_f0x7laySY8V702SJr_qlnAcmtcqS6J5lcZx6y9N85kWnuLhCTow"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }
        
        body.put("registration_ids", array);
        //String text = URLEncoder.encode("글자글자이거시 한글이다", "UTF-8");
		JSONObject notification = new JSONObject();
		
		try {
			notification.put("title",URLEncoder.encode("우와 이제 된다", "UTF-8"));//URLEncoder.encode("방송요리맛집이당ㅋㅋㅋ", "UTF-8"));
			notification.put("body",URLEncoder.encode("와아아아아앙", "UTF-8"));//+localDate.getDayOfWeek().name()+"!");
	
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		body.put("data", notification);
		System.out.println(body.toString());

		return body.toString();
    }
}