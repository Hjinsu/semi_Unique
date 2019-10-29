package com.life.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/weather.do")
public class weather_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public weather_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		if(command.equals("tlqkf")) {
			//String serviceKey = "nA9pQv0Vhmd6vWVKFQqsWw%2BiB0ool6M8oNpRB783XHjk8ts%2BtC7OpCm2m8fD6SWdETgPOhG2qGLtadHWZGznjw%3D%3D";
			String serviceKey = "6c9eaf44bef3112d0c4252b0837c121c";
			String X = request.getParameter("lat");
			String Y= request.getParameter("long");
			String date = request.getParameter("date");
			int time = Integer.parseInt(request.getParameter("time"));
			
			//String url1 = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=" + serviceKey+"&base_date=" +date+"&base_time="+time+"&nx="+X+"&ny="+Y+"&_type=json";
			String url1 = "http://api.openweathermap.org/data/2.5/forecast?lat="+X+"&lon="+Y+"&APPID="+serviceKey;
			
			URL url = new URL(url1);
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = "";
			String result = "";
			
			while((line = bf.readLine()) != null) {
				result = result.concat(line);
			}
			
			JsonParser jsonParser = new JsonParser();
			
			JsonElement element = jsonParser.parse(result);
			JsonArray array_list = element.getAsJsonObject().get("list").getAsJsonArray();
			
			String dt = "";
			String da[] = null;
			String res = "{";
			//JsonObject tmp = array_list.getAsJsonObject().get("dt_txt").toString();
			for(int i = 0; i < array_list.size(); ++i) {
				dt = array_list.get(i).getAsJsonObject().get("dt_txt").toString();
				dt = dt.replaceAll("\"", "");
				dt = dt.replaceAll("-", "");
				dt = dt.replaceAll(":", "");
				da = dt.split(" ");
				
				if(da[0].equals(date)) {
					int weather_time = Integer.parseInt(da[1].substring(0, 2));
					int sub_time = time - weather_time;
					
					
					if(weather_time == 0) {
						if(sub_time == 23 || sub_time == 1) {
							res += "\"temp\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp") + ",";
							res += "\"temp_max\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp_max") + ",";
							res += "\"temp_min\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp_min") + ",";
							res += "\"humidity\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("humidity") + ",";
							res += "\"weather\":" + array_list.get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main") + "}";
						}
					}
					else {
						if(Math.abs(sub_time) == 1 || sub_time == 0) {
							res += "\"temp\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp") + ",";
							res += "\"temp_max\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp_max") + ",";
							res += "\"temp_min\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp_min") + ",";
							res += "\"humidity\":" + array_list.get(i).getAsJsonObject().get("main").getAsJsonObject().get("humidity") + ",";
							res += "\"weather\":" + array_list.get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description") + "}";
						}
					}
				}				
			}
			
			PrintWriter out = response.getWriter();
			out.println(res);
		} 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
