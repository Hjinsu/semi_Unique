package com.life.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.life.biz.schedule_biz;
import com.life.dao.Util;
import com.life.dto.member_dto;
import com.life.dto.schedule_dto;


@WebServlet("/schedule.do")
public class schedule_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public schedule_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		schedule_biz biz = new schedule_biz();
		
		member_dto member_dto = (member_dto)request.getSession().getAttribute("member_dto");
		if(command.equals("calendar")) {
			response.sendRedirect("schedule.jsp");
		}
		else if(command.equals("cpreview")) {
			String id = member_dto.getMember_id();
			String yyyyMMdd = request.getParameter("yyyyMMdd");
			int count = biz.getCalViewCount(id, yyyyMMdd);

			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("cnt", count);
			
			String obj = JSONObject.toJSONString(map);
			
			PrintWriter out = response.getWriter();
			out.println(obj);
		}else if(command.equals("list")) {
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String mdate = year+Util.isTwo(month)+Util.isTwo(date);

			List<schedule_dto> list = biz.selectList(member_dto.getMember_id(), mdate);
			
			request.setAttribute("list", list);
			dispatch("schedulelist.jsp", request, response);
		}else if(command.equals("insertcal")) {
			String schedule_id = request.getParameter("id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String schedule_title = request.getParameter("title");
			String schedule_content = request.getParameter("content");

			String schedule_date = year+Util.isTwo(month)+Util.isTwo(date)+Util.isTwo(hour)+Util.isTwo(min);
			
			schedule_dto dto = new schedule_dto();
			dto.setSchedule_id(schedule_id);
			dto.setSchedule_content(schedule_content);
			dto.setSchedule_title(schedule_title);
			dto.setSchedule_date(schedule_date);

			biz.insertCalBoard(dto);
			response.sendRedirect("schedule.do?command=list&year="+year+"&month="+month+"&date="+date);
		}else if(command.equals("insert")) {
			dispatch("scheduleinsert.jsp", request, response);
		}else if(command.equals("muldel")) {
			String [] seq = request.getParameterValues("chk");
			String [] id =  {member_dto.getMember_id()};
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			
			biz.mulDel(seq, id);
			
			response.sendRedirect("schedule.do?command=list&year="+year+"&month="+month+"&date="+date);
		}else if(command.equals("detail")) {
			int schedule_no = Integer.parseInt(request.getParameter("schedule_no"));
			
			schedule_dto dto = biz.selectone(schedule_no, member_dto.getMember_id());
			request.setAttribute("schedule_dto", dto);
			dispatch("scheduledetail.jsp", request, response);
		}else if(command.equals("del")) {
			int schedule_no = Integer.parseInt(request.getParameter("schedule_no"));
			
			biz.del(schedule_no, member_dto.getMember_id());
			dispatch("schedule.do?command=list", request, response);
		}else if(command.equals("update")) {
			int schedule_no = Integer.parseInt(request.getParameter("schedule_no"));
			
			schedule_dto dto = biz.selectone(schedule_no, member_dto.getMember_id());
			
			request.setAttribute("schedule_dto", dto);
			dispatch("scheduleupdate.jsp", request, response);
		}else if(command.equals("updateres")) {
			int schedule_no = Integer.parseInt(request.getParameter("schedule_no"));
			schedule_dto dto = biz.selectone(schedule_no, member_dto.getMember_id());
			
			String schedule_title = request.getParameter("schedule_title");
			String schedule_content = request.getParameter("schedule_content");
			String year = dto.getSchedule_date().substring(0, 4);
			String month = dto.getSchedule_date().substring(4, 6);
			String date = dto.getSchedule_date().substring(6, 8);
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			
			String schedule_date = year + month + date + hour + min;
			
			dto.setSchedule_title(schedule_title);
			dto.setSchedule_content(schedule_content);
			dto.setSchedule_date(schedule_date);
			
			biz.update(dto);
			
			response.sendRedirect("schedule.do?command=list&year="+year+"&month="+month+"&date="+date);
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
