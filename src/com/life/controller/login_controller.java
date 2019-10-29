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
import javax.websocket.SendResult;

import com.life.biz.member_biz;
import com.life.dto.member_dto;

@WebServlet("/login.do")
public class login_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("<" + command + ">");

		member_biz biz = new member_biz();

		if (command.equals("home")) {
			response.sendRedirect("home.jsp");

		} else if (command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			member_dto member_dto = biz.loginChk(id, pw);

			if (member_dto == null) {

				PrintWriter out = response.getWriter();
				out.println(2);

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("member_dto", member_dto);
				session.setMaxInactiveInterval(-1);

				PrintWriter out = response.getWriter();
				out.println(id);
			}

		} else if (command.equals("kakaologin")) {
			String id = request.getParameter("userID");
			String email = request.getParameter("userEmail");

			member_dto member_dto = biz.kakaologinChk(id, email);

			if (member_dto == null) {

				PrintWriter out = response.getWriter();
				out.println(id + "/" + email);

			} else {

				HttpSession session = request.getSession();
				session.setAttribute("member_dto", member_dto);
				session.setMaxInactiveInterval(10 * 60);

				PrintWriter out = response.getWriter();
				out.println(id);
			}

		} else if (command.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("home.jsp");
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

	public void alert(HttpServletResponse response, String msg, String url) throws IOException {

		PrintWriter out = response.getWriter();

		out.println(" <script type=\"text/javascript\">" + "	  alert('" + msg + "');" + "     location.href='" + url
				+ "';" + " </script> ");
	}

	public void alert2(HttpServletResponse response, String msg) throws IOException {

		PrintWriter out = response.getWriter();

		out.println(" <script type=\"text/javascript\">" + "	  alert('" + msg + "');" + " </script> ");
	}

}
