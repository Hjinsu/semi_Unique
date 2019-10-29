package com.life.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.biz.account_biz;
import com.life.dao.Paging;
import com.life.dto.account_dto;
import com.life.dto.member_dto;

@WebServlet("/account.do")
public class account_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public account_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String command = request.getParameter("command");

	  Paging paging = new Paging();
	  account_biz biz = new account_biz();
	  member_dto member_dto = (member_dto)request.getSession().getAttribute("member_dto");
	  if (command.equals("insertres")) {
		  String id =member_dto.getMember_id();
		  int in = Integer.parseInt(request.getParameter("in"));
		  int out = Integer.parseInt(request.getParameter("out"));
		  String content = request.getParameter("content");
		  int totalcash = biz.selectTotalCash(id);
		  
		  account_dto dto = new account_dto();
		  dto.setAccount_id(id);
		  dto.setAccount_in_cash(in);
		  dto.setAccount_out_cash(out);
		  dto.setAccount_io_content(content);
		  dto.setAccount_totalcash(totalcash);
		  
		  request.setAttribute("account_dto", dto);
		  
		  int res = biz.insert(dto, id);
		  
		  if(res > 0) {
			 response.sendRedirect("account.do?command=account&page=1");
		  } else {
			  response.sendRedirect("account.do?command=account&page=1");
		  }
	  } else if(command.equals("account")) {
		  
		  List<account_dto> list = new ArrayList<account_dto>();

		  String account_id =member_dto.getMember_id();
		  list = biz.selectList(account_id);
		  
		  int page = Integer.parseInt(request.getParameter("page"));
			
		  paging.setTotalpage(list.size());
			
		  if(page<=1) {
			  page = 1;
		  } else if(page >=paging.getTotalpage()) {
			  page = paging.getTotalpage();
		  }

		  paging.setPage(page);
			
		  paging.setStartboard();
		  paging.setEndboard();
		  paging.setStartpage();
		  paging.setEndpage();
			
		  request.setAttribute("paging", paging);
			
		  request.setAttribute("list", list);
		  dispatch(request, response, "account.jsp"); 
	  } else if(command.equals("selectone")) {
		  int totalcash = Integer.parseInt(request.getParameter("totalcash"));
		  
		  account_dto account_dto = new account_dto();
		  
		  account_dto = biz.selectOne(totalcash);
		  
		  request.setAttribute("account", account_dto);
		  
		  dispatch(request, response, "account.do?command=account&page=1"); 
	  }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
