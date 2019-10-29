package com.life.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.biz.message_biz;
import com.life.dto.member_dto;
import com.life.dto.message_dto;

@WebServlet("/whisper.do")
public class whisper_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public whisper_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		message_biz message_biz = new message_biz();
		member_dto member_dto = (member_dto)request.getSession().getAttribute("member_dto");
		if(command.equals("message_box")) {
			List<message_dto> message_list = message_biz.selectlist(member_dto.getMember_id());
			
			request.setAttribute("message_list", message_list);
			dispatch("message_box.jsp", request, response);
			
		}else if(command.equals("selectone")) {
			String message_no = request.getParameter("message_no");
			String message_receive_id = member_dto.getMember_id();
			
			message_dto message_dto = message_biz.selectone(message_no, message_receive_id);
			message_biz.update(message_receive_id, message_no);
			
			request.setAttribute("message_dto", message_dto);
			dispatch("message_selectone.jsp", request, response);
		}else if(command.equals("resend")) {
			String message_receive_id = request.getParameter("message_receive_id");
			
			response.sendRedirect("message_resend.jsp?message_receive_id="+URLEncoder.encode(message_receive_id,"UTF-8"));
				
		}else if(command.equals("insert")) {
			response.sendRedirect("message_send.jsp");
			
		}else if(command.equals("insertres")) {
			String message_send_id = request.getParameter("message_send_id");
			String message_receive_id = request.getParameter("message_receive_id");
			String message_content = request.getParameter("message_content");
			
			message_dto message_dto = new message_dto();
			
			message_dto.setMessage_content(message_content);
			message_dto.setMessage_receive_id(message_receive_id);
			message_dto.setMessage_send_id(message_send_id);
			
			message_biz.insert(message_dto);
			
			response.sendRedirect("whisper.do?command=message_box");
		}else if(command.equals("delete")) {
			String message_no = request.getParameter("message_no");
			String message_receive_id = request.getParameter("message_receive_id");
			
			message_biz.delete(message_receive_id, message_no);
			
			response.sendRedirect("whisper.do?command=message_box");
		}else if(command.equals("muldel")) {
			String [] seq = request.getParameterValues("chk");
			String [] message_receive_id = {member_dto.getMember_id()};
			message_biz.muldel(seq, message_receive_id);
						
			response.sendRedirect("whisper.do?command=message_box");
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
