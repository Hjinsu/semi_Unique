package com.life.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.biz.member_biz;
import com.life.dto.member_dto;

@WebServlet("/myinformation.do")
public class myinformation_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myinformation_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		
		member_biz biz = new member_biz();
		
		if(command.equals("memberinfo")) {
			response.sendRedirect("myinformation.jsp");
			
		}else if(command.equals("UpdateMemberInfo")) {
			
			member_dto member_dto = new member_dto();
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String addr = request.getParameter("addr");
			String phone = request.getParameter("phone");
			String interest = request.getParameter("interest");			
		
			member_dto.setMember_id(id);
			member_dto.setMember_pw(pw);
			member_dto.setMember_addr(addr);
			member_dto.setMember_phone(phone);
			member_dto.setMember_interest(interest);

			int res = biz.updateMemberinfo(member_dto); 
			
			if(res > 0) {			
				alert(response,"수정성공 다시 로그인 해주세요","login.do?command=logout");
			}
		}else if(command.equals("DeleteMemberInfo")) {
			
			String id = request.getParameter("id");

			int res = biz.dropoutMember(id);
			
			if(res > 0){
				alert(response,"삭제성공","login.do?command=logout");
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void alert (HttpServletResponse response, String msg, String url) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println(" <script type=\"text/javascript\">"
				   +"	  alert('"+msg+"');"
				   +"     location.href='"+url+"';" 
		           +" </script> ");
	}

}
