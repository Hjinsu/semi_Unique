package com.life.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.life.biz.member_biz;
import com.life.dto.member_dto;

@WebServlet("/signup.do")
public class signup_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		
		member_biz biz = new member_biz();
		
		if(command.equals("regist")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String addr = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String interest = request.getParameter("interest");
			
			member_dto member_dto = new member_dto();
			member_dto.setMember_id(id);
			member_dto.setMember_pw(pw);
			member_dto.setMember_name(name);
			member_dto.setMember_birth(birth);
			member_dto.setMember_addr(addr);
			member_dto.setMember_phone(phone);
			member_dto.setMember_email(email);
			member_dto.setMember_gender(gender);
			member_dto.setMember_interest(interest);

			int res = biz.signupChk(member_dto);

			if(res==1) {
				PrintWriter out = response.getWriter();
				out.println("성공");
			}else {
				PrintWriter out = response.getWriter();
				out.println(2);
			}
		
		}else if(command.equals("idchk")) {
			String id = request.getParameter("id");
			
			member_dto member_dto = biz.idChk(id);
			
			if(member_dto == null) {
				PrintWriter out = response.getWriter();
				out.println("성공");		
			}else {		
				PrintWriter out = response.getWriter();
				out.println(2);
			}		
		
		}else if(command.equals("kakaoregist")) {
			String id = request.getParameter("kakaoid");
			String pw = request.getParameter("kakaopw");
			String name = request.getParameter("kakaoname");
			String birth = request.getParameter("kakaobirth");
			String addr = request.getParameter("kakaoaddr");
			String phone = request.getParameter("kakaophone");
			String email = request.getParameter("kakaoemail");
			String gender = request.getParameter("kakaogender");
			String interest = request.getParameter("kakaointerest");
			
			member_dto member_dto = new member_dto();
			member_dto.setMember_id(id);
			member_dto.setMember_pw(pw);
			member_dto.setMember_name(name);
			member_dto.setMember_birth(birth);
			member_dto.setMember_addr(addr);
			member_dto.setMember_phone(phone);
			member_dto.setMember_email(email);
			member_dto.setMember_gender(gender);
			member_dto.setMember_interest(interest);

			int res = biz.signupChk(member_dto);
			
			if(res==1) {
				PrintWriter out = response.getWriter();
				out.println("성공");
			}else {
				PrintWriter out = response.getWriter();
				out.println(2);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	public void alert (HttpServletResponse response, String msg, String url) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println(" <script type=\"text/javascript\">"
				   +"	  alert('"+msg+"');"
				   +"     location.href='"+url+"';" 
		           +" </script> ");
	}


}
