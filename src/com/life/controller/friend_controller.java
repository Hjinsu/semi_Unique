package com.life.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.biz.friend_biz;
import com.life.dto.friend_dto;
import com.life.dto.member_dto;

@WebServlet("/friend.do")
public class friend_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public friend_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		
		friend_biz friend_biz = new friend_biz();
		member_dto member_dto = new member_dto();
		member_dto = (member_dto)request.getSession().getAttribute("member_dto");
		if(command.equals("friend_box")) {
			friend_dto friend_dto = friend_biz.selectall(member_dto.getMember_id());
			
			request.setAttribute("friend_dto", friend_dto);
			dispatch("friend_box.jsp", request, response);
		}else if(command.equals("insert")) {
			String friend_id = request.getParameter("friend_id");
			PrintWriter out = response.getWriter();
			
			int res = friend_biz.insert(member_dto.getMember_id(), friend_id);
				
			if(res == 2) {
				out.print("이미 친구 추가된 아이디 입니다.");
			}else if(res == 1) {
				out.print("친구 추가를 했습니다.");
			}else {
				out.print("친구 목록이 가득 찼습니다.");
			}
		}else if(command.equals("del_friend")) {
			String friend_id = request.getParameter("friend_id");
			
			int res = friend_biz.del_friend(member_dto.getMember_id(), friend_id);
			
			response.sendRedirect("friend.do?command=friend_box");
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
