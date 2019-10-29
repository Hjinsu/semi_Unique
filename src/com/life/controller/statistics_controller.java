package com.life.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.biz.account_biz;
import com.life.biz.health_biz;
import com.life.biz.statistics_biz;
import com.life.dao.Util;
import com.life.dto.account_dto;
import com.life.dto.health_dto;
import com.life.dto.member_dto;
import com.life.dto.statistics_dto;

@WebServlet("/statistics.do")
public class statistics_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public statistics_controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		account_biz acc_biz = new account_biz();
		health_biz health_biz = new health_biz();
		member_dto member_dto = (member_dto)request.getSession().getAttribute("member_dto");
		
		if(command.equals("statistics")){
			response.sendRedirect("statistics.jsp");
		}else if(command.equals("graph")) {
			String date = request.getParameter("date");
			String ss[] = date.split("-");
			
			int year = Integer.parseInt(ss[0]);
			int month = Integer.parseInt(ss[1]);
			int day = Integer.parseInt(ss[2]);
			
			Calendar c = Calendar.getInstance();

			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			c.set(Calendar.DATE, day);
			
			String data = account_day_value(c, year, month, acc_biz, member_dto.getMember_id()) + "§" + account_week_value(c, year, month, acc_biz, member_dto.getMember_id()) +
							"§" + kcal_day_value(c, date, health_biz, member_dto.getMember_id()) + "§" + kcal_week_value(year, month, day, health_biz, member_dto.getMember_id());
			
			PrintWriter out = response.getWriter();
			out.print(data);
			
		}else if(command.equals("today")) {
			Calendar c = Calendar.getInstance();

			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+ 1;
			int day = c.get(Calendar.DATE);
			
			String date = year + "-" + month + "-" + day;
			String data = account_day_value(c, year, month, acc_biz, member_dto.getMember_id()) + "§" + account_week_value(c, year, month, acc_biz, member_dto.getMember_id()) +
							"§" + kcal_day_value(c, date, health_biz, member_dto.getMember_id()) + "§" + kcal_week_value(year, month, day, health_biz, member_dto.getMember_id());
			
			PrintWriter out = response.getWriter();
			out.print(data);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	public String account_day_value(Calendar c, int year, int month, account_biz acc_biz,String member_id) {
		int sunday = 0;
		int saturday = 0;
		
		if(1 == c.get(Calendar.WEEK_OF_MONTH)) {
			sunday = 1;
			saturday = c.get(Calendar.DATE) + (7 - c.get(Calendar.DAY_OF_WEEK));
		}else {
			sunday = Math.abs(c.get(Calendar.DATE) - (c.get(Calendar.DAY_OF_WEEK) -1));
			saturday = c.get(Calendar.DATE) + (7 - c.get(Calendar.DAY_OF_WEEK));
			
			if(saturday >= c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				saturday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
		}
		
		String day_min_date = year + "-" + Util.isTwo(month+"") + "-" + Util.isTwo(sunday+"");
		String day_max_date = year + "-" + Util.isTwo(month+"") + "-" + Util.isTwo(saturday+"");
		
		List<account_dto> account_list = acc_biz.searchList(member_id, day_min_date, day_max_date);
		
		String labels = "일/월/화/수/목/금/토"; //요일
		String series_value =""; // 값
		
		int monday_value = 0;
		int tuesday_value = 0;
		int wednesday_value = 0;
		int thursday_value = 0;
		int Friday_value = 0;
		int saturday_value = 0;
		int sunday_value= 0;
		
		if(!account_list.isEmpty()) {
			for(int i = 0; i < account_list.size(); ++i) {
				if(account_list.get(i).getAccount_days().equals("월")) {
					monday_value += account_list.get(i).getAccount_out_cash();
				}else if(account_list.get(i).getAccount_days().equals("화")) {
					tuesday_value += account_list.get(i).getAccount_out_cash();
				}else if(account_list.get(i).getAccount_days().equals("수")) {
					wednesday_value += account_list.get(i).getAccount_out_cash();
				}else if(account_list.get(i).getAccount_days().equals("목")) {
					thursday_value += account_list.get(i).getAccount_out_cash();
				}else if(account_list.get(i).getAccount_days().equals("금")) {
					Friday_value += account_list.get(i).getAccount_out_cash();
				}else if(account_list.get(i).getAccount_days().equals("토")) {
					saturday_value += account_list.get(i).getAccount_out_cash();
				}else if(account_list.get(i).getAccount_days().equals("일")) {
					sunday_value += account_list.get(i).getAccount_out_cash();
				}else {
					monday_value += 0;
					tuesday_value += 0;
					wednesday_value += 0;
					thursday_value += 0;
					Friday_value += 0;
					saturday_value += 0;
					sunday_value += 0;
				}
			}
		}
		series_value = sunday_value +"/" + monday_value +"/" + tuesday_value +"/" + wednesday_value +"/" + 
						thursday_value +"/" + Friday_value +"/" + saturday_value;
		
		String data = labels+"_"+series_value;
		return data;
	}
	
	public String account_week_value(Calendar c, int year, int month, account_biz acc_biz, String member_id) {
		int month_startday = 1;
		int month_endday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DATE, month_endday);
		
		String week_min_date = year + "-" + Util.isTwo(month+"") + "-" + Util.isTwo(month_startday+"");
		String week_max_date = year + "-" + Util.isTwo(month+"") + "-" + Util.isTwo(month_endday+"");
		
		List<account_dto> account_week = acc_biz.searchList(member_id, week_min_date, week_max_date);

		String labels =""; //요일
		String series_value =""; // 값
		
		String data = "";

		for(int i = 1; i <= c.get(Calendar.WEEK_OF_MONTH); ++i) {
			int account_week_out = 0;
			if(i == c.get(Calendar.WEEK_OF_MONTH)) {
				for(int j = 0; j < 7; ++j) {
					if(((i-1)*7+j) < month_endday) {
						if(account_week.size() > ((i-1)*7+j) && !account_week.isEmpty()) {
							account_week_out += account_week.get((i-1)*7+j).getAccount_out_cash();
						}
						else
							account_week_out += 0;
					}
				}
				series_value += account_week_out +"";
				labels += i+"주";
			}else {
				for(int j = 0; j < 7; ++j) {
					if(((i-1)*7+j) < month_endday) {
						
						if(account_week.size() > ((i-1)*7+j) && !account_week.isEmpty()) {
							account_week_out += account_week.get((i-1)*7+j).getAccount_out_cash();
						}
						else
							account_week_out += 0;
					}
				}
				series_value += account_week_out +"/";
				labels += i+"주/";
			}
		}
		data = labels + "_" + series_value;
		return data;
	}

	public String kcal_day_value(Calendar c, String date, health_biz health_biz, String member_id) {
		List<health_dto> health_list = health_biz.searchList(member_id, date, date);
		
		String data = "";
		if(!health_list.isEmpty()) {
			for(health_dto dto : health_list) {
				data = Float.toString(dto.getHealth_kcal());
			}
		}else {
			data = "0";
		}
		return data;
	}
	
	public String kcal_week_value(int year, int month, int day, health_biz health_biz, String member_id) {
		String data = "";
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DATE, day);
		
		int sunday = 0;
		int saturday = 0;
		
		if(1 == c.get(Calendar.WEEK_OF_MONTH)) {
			sunday = 1;
			saturday = c.get(Calendar.DATE) + (7 - c.get(Calendar.DAY_OF_WEEK));
		}else {
			sunday = Math.abs(c.get(Calendar.DATE) - (c.get(Calendar.DAY_OF_WEEK) -1));
			saturday = c.get(Calendar.DATE) + (7 - c.get(Calendar.DAY_OF_WEEK));
			
			if(saturday >= c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				saturday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
		}
		
		String day_min_date = year + "-" + Util.isTwo(month+"") + "-" + Util.isTwo(sunday+"");
		String day_max_date = year + "-" + Util.isTwo(month+"") + "-" + Util.isTwo(saturday+"");
				
		List<health_dto> health_week = health_biz.searchList(member_id, day_min_date, day_max_date);
		float kcal = 0.f;
		for(health_dto dto: health_week) {
			kcal += dto.getHealth_kcal();
		} 
		data = Float.toString(kcal);
		return data;
	}
}
