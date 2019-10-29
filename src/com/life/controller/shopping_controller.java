package com.life.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


@WebServlet("/shopping.do")
public class shopping_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public shopping_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		String url = null;

		
		if(command.equals("shop_main")) {
			url = "http://www.coocha.co.kr";
			Document doc = Jsoup.connect(url).get();
			
			Elements ele_list = doc.select("#dealListDiv");
			Elements ele_deal = ele_list.select("div.deal");
			Elements ele_a = ele_deal.select(" a");
			Elements ele_deal_inner = ele_deal.select("div.deal-inner");
			
			Elements ele_img = ele_deal_inner.select("div.img");
			Elements ele_img_img = ele_img.select("img");

			Elements ele_area = ele_deal_inner.select("div.areas");
			
			Elements ele_title = ele_area.select("div.title");
			
			Elements ele_prices = ele_area.select("div.prices");
			
			//ele_href, ele_imgattr, ele_title, ele_prices
			List<String> list_href = new ArrayList<String>();
			List<String> list_img = new ArrayList<String>();
			List<String> list_title = new ArrayList<String>();
			List<String> list_price = new ArrayList<String>();
			
			for(int i = 0; i < ele_deal.size(); ++i) {
				list_href.add("http://www.coocha.co.kr"+ele_a.get(i).attr("href"));
				list_img.add(ele_img_img.get(i).attr("src"));
				list_title.add(ele_title.get(i).text());
				list_price.add(ele_prices.get(i).toString());
			}			
			
			request.setAttribute("href", list_href);
			request.setAttribute("img", list_img);
			request.setAttribute("title", list_title);
			request.setAttribute("price", list_price);
			
			dispatch("shopping.jsp", request, response);
		}else if(command.equals("search")) {
			String search_item = request.getParameter("search_item");
			url = "http://www.coocha.co.kr/search/searchDealList?keyword="+URLEncoder.encode(search_item,"UTF-8");
			
			Document doc = Jsoup.connect(url).get();
			
			Elements ele_deal = doc.select("div.deal");
			Elements ele_a = ele_deal.select(" a");
			Elements ele_deal_inner = ele_deal.select("div.deal-inner");
			
			Elements ele_img = ele_deal_inner.select("div.img");
			Elements ele_img_img = ele_img.select("img");

			Elements ele_area = ele_deal_inner.select("div.areas");
			
			Elements ele_title = ele_area.select("div.title");
			
			Elements ele_prices = ele_area.select("div.prices");
			
			//ele_href, ele_imgattr, ele_title, ele_prices
			List<String> list_href = new ArrayList<String>();
			List<String> list_img = new ArrayList<String>();
			List<String> list_title = new ArrayList<String>();
			List<String> list_price = new ArrayList<String>();
			
			for(int i = 0; i < ele_deal.size(); ++i) {
				list_href.add("http://www.coocha.co.kr"+ele_a.get(i).attr("href"));
				list_img.add(ele_img_img.get(i).attr("src"));
				list_title.add(ele_title.get(i).text());
				list_price.add(ele_prices.get(i).toString());
			}			
			
			request.setAttribute("href", list_href);
			request.setAttribute("img", list_img);
			request.setAttribute("title", list_title);
			request.setAttribute("price", list_price);
			
			dispatch("shopping.jsp", request, response);
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
