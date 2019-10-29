package com.life.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.life.biz.board_biz;
import com.life.biz.map_biz;
import com.life.biz.schedule_biz;
import com.life.biz.weather_biz;
import com.life.dao.Paging;
import com.life.dto.board_dto;
import com.life.dto.comment_dto;
import com.life.dto.map_dto;
import com.life.dto.member_dto;
import com.life.dto.schedule_dto;
import com.life.dto.weather_dto;

@WebServlet("/meetingboard.do")
public class meetingboard_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public meetingboard_controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		Paging paging = new Paging();
		
		board_biz board_biz = new board_biz();
		map_biz map_biz = new map_biz();
		weather_biz weather_biz = new weather_biz();
		schedule_biz schedule_biz = new schedule_biz(); 
		member_dto member_dto = (member_dto)request.getSession().getAttribute("member_dto");
		if (command.equals("meetingboard")) {
			
			String search = request.getParameter("search");
			String searchtxt = request.getParameter("searchtxt");
			
			Map<String, String> board_map = new HashMap<String, String>();
			board_map.put(search, searchtxt);
			List<board_dto> board_list = board_biz.searchlist(board_map);
				
			int page = Integer.parseInt(request.getParameter("page"));
			
			request.setAttribute("board_list", board_list);
			request.setAttribute("search", search);
			request.setAttribute("searchtxt", searchtxt);
			
			paging.setTotalpage(board_list.size());
			
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
			
			dispatch(request, response, "meetingboard.jsp");
		
			
		} else if (command.equals("selectone")) {
			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));
			int board_viewnum = Integer.parseInt(request.getParameter("board_viewnum"));
			
			List<comment_dto> comment_list = board_biz.selectlist_comment(board_no_seq);
			
			map_dto map_dto = map_biz.selectOne(board_no_seq);
			weather_dto weather_dto = weather_biz.selectOne(board_no_seq);
			System.out.println(board_no_seq);
			board_dto dto_viewnum = new board_dto();
			dto_viewnum.setBoard_viewnum(board_viewnum);
			dto_viewnum.setBoard_no_seq(board_no_seq);

			board_biz.updateviewnum(dto_viewnum);

			board_dto board_dto = board_biz.selectone(board_no_seq);
			String [] ss = null;
			if(board_dto.getBoard_attend_user() != null) {
				ss = board_dto.getBoard_attend_user().split("/");
				request.setAttribute("attend", ss);
			}
			
			request.setAttribute("board_dto", board_dto);
			request.setAttribute("map_dto", map_dto);
			request.setAttribute("weather_dto", weather_dto);
			request.setAttribute("comment_list", comment_list);

			dispatch(request, response, "meetingboard_selectone.jsp");
			
		} else if (command.equals("meetingupdate")) {
			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));

			board_dto boardupdate_dto = board_biz.selectone(board_no_seq);
			weather_dto weather_dto = weather_biz.selectOne(board_no_seq);

			request.setAttribute("boardupdate_dto", boardupdate_dto);
			request.setAttribute("weather_dto", weather_dto);
			dispatch(request, response, "meetingboard_update.jsp");

		} else if (command.equals("meetingupdateres")) {
			
			String board_date = request.getParameter("board_date");
			String time_date = board_date.replace("T", " ");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date board_timelimit = null;
			try {
				board_timelimit = sdf.parse(time_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));
			String board_writer = request.getParameter("board_writer");
			String board_title = request.getParameter("board_title");
			String board_content = request.getParameter("board_content");
			String board_location = request.getParameter("board_location");
			String board_tag = request.getParameter("board_tag");
			String board_genderlimit = request.getParameter("board_genderlimit");
			int board_age_min = Integer.parseInt(request.getParameter("board_age_min"));
			int board_age_max = Integer.parseInt(request.getParameter("board_age_max"));
			int board_peoplelimit = Integer.parseInt(request.getParameter("board_peoplelimit"));

			double map_lng = Double.parseDouble(request.getParameter("lng"));
			double map_lat = Double.parseDouble(request.getParameter("lat"));
			String map_addr = request.getParameter("map_addr");

			String weather_reh = request.getParameter("reh");
			String weather_tmn = request.getParameter("tmn");
			String weather_tmx = request.getParameter("tmx");
			String weather_t3h = request.getParameter("t3h");
			String weather_sky = request.getParameter("sky");

//----------------board--------------------------------------
			board_dto boardres_dto = new board_dto();

			boardres_dto.setBoard_no_seq(board_no_seq);
			boardres_dto.setBoard_writer(board_writer);
			boardres_dto.setBoard_title(board_title);
			boardres_dto.setBoard_content(board_content);
			boardres_dto.setBoard_location(board_location);
			boardres_dto.setBoard_tag(board_tag);
			boardres_dto.setBoard_genderlimit(board_genderlimit);
			boardres_dto.setBoard_age_min(board_age_min);
			boardres_dto.setBoard_age_max(board_age_max);
			boardres_dto.setBoard_peoplelimit(board_peoplelimit);
			boardres_dto.setBoard_timelimit(board_timelimit);
//--------------------map-----------------------------------
			map_dto map_dto = new map_dto();

			map_dto.setMap_latitude(map_lat);
			map_dto.setMap_longitude(map_lng);
			map_dto.setMap_place(map_addr);
			map_dto.setMap_no_seq(board_no_seq);

//--------------------weather-----------------------------------	
			weather_dto weather_dto = new weather_dto();

			weather_dto.setWeather_reh(weather_reh);
			weather_dto.setWeather_tmn(weather_tmn);
			weather_dto.setWeather_tmx(weather_tmx);
			weather_dto.setWeather_t3h(weather_t3h);
			weather_dto.setWeather_sky(weather_sky);
			weather_dto.setWeather_board_no(board_no_seq);
			
			if ((weather_biz.update(weather_dto) > 0) && (map_biz.update(map_dto) > 0) &&
			(board_biz.update(boardres_dto) > 0)) {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			}else {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			}
		} else if (command.equals("meetingdelete")) {

			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));

			if ((board_biz.delete(board_no_seq) > 0) && (map_biz.delete(board_no_seq) > 0)
					&& (weather_biz.delete(board_no_seq) > 0)) {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			} else {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			}

		} else if (command.equals("insertboard")) {

			response.sendRedirect("meetingboard_insert.jsp");

		} else if (command.equals("insertboardres")) {
			
			String board_date = request.getParameter("board_date");
			String time_date = board_date.replace("T", " ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date board_timelimit = null;
			try {
				board_timelimit = sdf.parse(time_date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String board_writer = request.getParameter("board_writer");
			String board_title = request.getParameter("board_title");
			String board_content = request.getParameter("board_content");
			String board_location = request.getParameter("board_location");
			String board_tag = request.getParameter("board_tag");
			String board_genderlimit = request.getParameter("board_genderlimit");
			int board_age_min = Integer.parseInt(request.getParameter("board_age_min"));
			int board_age_max = Integer.parseInt(request.getParameter("board_age_max"));
			int board_peoplelimit = Integer.parseInt(request.getParameter("board_peoplelimit"));

			double map_lng = Double.parseDouble(request.getParameter("lng"));
			double map_lat = Double.parseDouble(request.getParameter("lat"));
			String map_addr = request.getParameter("map_addr");

			String weather_reh = request.getParameter("reh");
			String weather_tmn = request.getParameter("tmn");
			String weather_tmx = request.getParameter("tmx");
			String weather_t3h = request.getParameter("t3h");
			String weather_sky = request.getParameter("sky");

//----------------board--------------------------------------
			board_dto boardinsert_dto = new board_dto();

			boardinsert_dto.setBoard_writer(board_writer);
			boardinsert_dto.setBoard_title(board_title);
			boardinsert_dto.setBoard_content(board_content);
			boardinsert_dto.setBoard_location(board_location);
			boardinsert_dto.setBoard_tag(board_tag);
			boardinsert_dto.setBoard_genderlimit(board_genderlimit);
			boardinsert_dto.setBoard_age_min(board_age_min);
			boardinsert_dto.setBoard_age_max(board_age_max);
			boardinsert_dto.setBoard_peoplelimit(board_peoplelimit);
			boardinsert_dto.setBoard_timelimit(board_timelimit);
			
//--------------------map-----------------------------------
			map_dto map_dto = new map_dto();
			map_dto.setMap_latitude(map_lat);
			map_dto.setMap_longitude(map_lng);
			map_dto.setMap_place(map_addr);
//----------------weather--------------------------------------
			weather_dto weather_dto = new weather_dto();

			weather_dto.setWeather_reh(weather_reh);
			weather_dto.setWeather_tmn(weather_tmn);
			weather_dto.setWeather_tmx(weather_tmx);
			weather_dto.setWeather_t3h(weather_t3h);
			weather_dto.setWeather_sky(weather_sky);

			if ((board_biz.insert(boardinsert_dto) > 0) && (map_biz.insert(map_dto) > 0)
					&& (weather_biz.insert(weather_dto) > 0)) {
				//int board_no_seq = board_biz.curselect();
				//schedule_biz.insertBoard(board_no_seq, board_writer);
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			} else {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			}
		} else if (command.equals("insertAS")) {
			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));

			board_dto insertAS_dto = board_biz.selectone(board_no_seq);

			request.setAttribute("insertAS_dto", insertAS_dto);

			dispatch(request, response, "meetingboard_insertAS.jsp");

		} else if (command.equals("insertASres")) {

			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));

			board_dto insertASres_dto = board_biz.selectone(board_no_seq);

			String board_writer = request.getParameter("board_writer");
			String board_title = request.getParameter("board_title");
			String board_content = request.getParameter("board_content");

			insertASres_dto.setBoard_no_seq(board_no_seq);
			insertASres_dto.setBoard_writer(board_writer);
			insertASres_dto.setBoard_title(board_title);
			insertASres_dto.setBoard_content(board_content);

			board_biz.updateAS(board_no_seq);

			if (board_biz.insertAS(insertASres_dto) > 0) {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			} else {
				response.sendRedirect("meetingboard.do?command=meetingboard&page=1");
			}

		} else if(command.equals("comment_insert")) {
			
			int comment_board_no = Integer.parseInt(request.getParameter("board_no_seq"));
			String comment_content = request.getParameter("comment_content");
			String comment_writer = request.getParameter("comment_writer");
			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));
			int board_viewnum = Integer.parseInt(request.getParameter("board_viewnum"));
			
			comment_dto comment_dto = new comment_dto();
			comment_dto.setComment_writer(comment_writer);
			comment_dto.setComment_board_no(comment_board_no);
			comment_dto.setComment_content(comment_content);
			
			if (board_biz.insert_comment(comment_dto) > 0) {
				response.sendRedirect("meetingboard.do?command=selectone&board_no_seq="+board_no_seq+"&board_viewnum="+board_viewnum);
			} else {
				response.sendRedirect("meetingboard.do?command=selectone&board_no_seq="+board_no_seq+"&board_viewnum="+board_viewnum);
			}
			
		} else if(command.equals("comment_delete")) {
			
			int comment_no_seq = Integer.parseInt(request.getParameter("comment_no_seq"));
			int board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));
			int board_viewnum = Integer.parseInt(request.getParameter("board_viewnum"));
			
			if (board_biz.delete_comment(comment_no_seq,member_dto.getMember_id()) > 0) {
				response.sendRedirect("meetingboard.do?command=selectone&board_no_seq="+board_no_seq+"&board_viewnum="+board_viewnum);
			} else {
				response.sendRedirect("meetingboard.do?command=selectone&board_no_seq="+board_no_seq+"&board_viewnum="+board_viewnum);
			}
		}else if(command.equals("attendupdate")) {
			int board_no_seq = 0;
			if(request.getParameter("board_no_seq") != null)
				board_no_seq = Integer.parseInt(request.getParameter("board_no_seq"));
			
			int board_viewnum = 0;
			if(request.getParameter("board_viewnum") != null)
				board_viewnum = Integer.parseInt(request.getParameter("board_viewnum"));
			String uu = board_biz.attenduser_select(board_no_seq);
			String attend_user = "";
			if(uu == null)
				attend_user = request.getParameter("attend_user")+"/";
			else
				attend_user = board_biz.attenduser_select(board_no_seq) + request.getParameter("attend_user")+"/";
			
			int res = board_biz.attenduser(board_no_seq, attend_user);
						
			int res2 = schedule_biz.insertBoard(board_no_seq, request.getParameter("attend_user"));
			
			if(res > 0) {
				response.sendRedirect("meetingboard.do?command=selectone&board_no_seq="+board_no_seq+"&board_viewnum="+board_viewnum);
			}else {
				response.sendRedirect("meetingboard.do?command=selectone&board_no_seq="+board_no_seq+"&board_viewnum="+board_viewnum);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

	public void alert(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		out.println("<script language='javascript'>");
		out.println("alert('" + msg + "');");
		out.println("location.href='home.jsp?'");
		out.println("</script>");
		out.flush();
		response.flushBuffer();
		out.close();
		
	}
	
	
	
}
