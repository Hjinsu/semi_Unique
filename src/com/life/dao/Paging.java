package com.life.dao;

public class Paging {
	
	private int showboard = 10;
	private int startboard = 0;
	private int endboard = 0;
	private int totalboard = 0;

	private int startpage = 0;
	private int endpage = 0;
	private int page = 0;
	private int totalpage = 0;
	
	public void setStartboard() {
		//해당 페이지의 시작 게시물은
		//보여줄 게시물 수 * 현제 선택된 페이지 - 보여줄 게시물수 + 1
		// 5 * 2 - 5 + 1 = 6 
		this.startboard = showboard * page - showboard + 1;
	}
	public int getStartboard() {
		return startboard;
	}
	public void setEndboard() {
		//해당 페이지의 마지막 게시물은
		//보여줄 게시물 갯수 * 현재 페이지
		//5 * 2 -1 =9
		this.endboard = showboard * page;
		
		if(endboard >= totalboard)
			endboard = totalboard;
	}
	public int getEndboard() {
		return endboard;
	}
	public void setTotalpage(int board) {
		//전체 게시물과 화면에 띄울 게시물의 갯수를 나눠서 전체 페이지를 구한다.
		totalboard = board;
		
		totalpage = totalboard / showboard;
		//보여줄 페이지 * 전체 페이지 가 전체 게시물보다 값이 작으면 전체페이지를 1증가시킨다.
		//전체게시물이 6개 라면 총 2페이지가 되야한다.
		// 6 / 5 = 1
		//if(6 > 5 * 1) 면 true이기 때문에 토탈페이지는 2가 된다.   
		if(board > showboard * totalpage)
			++totalpage;
		
		// 현재 보고있는 페이지가 전체 페이지보다 더클 경우는 없지만 만약을 위해
		// 현재 보고있는페이지를 다시 셋팅 해준다.
		if(totalpage < page)
			setPage(totalpage);		
	}
	public int getTotalpage() {
		return totalpage;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	
	public void setStartpage() {
		//시작 페이지를 구해준다.  ((7 - 1) / 10) * 10 + 1 = 1
		this.startpage = (int)(((page - 1) / 3.0) * 3 + 1);
		
	}
	public int getStartpage() {
		return startpage;
	}
	
	public void setEndpage() {
		//보여줄 마지막 페이지를 구해준다. 1 + 10 - 1 = 10  
		this.endpage = startpage + 3 - 1;
		//만약 전체페이지가 10보다 작은데 마지막 페이지가 10이 될수가 없으니 
		//마지막 페이지를 전체페이지로 바꿔준다. 
		if(this.endpage > totalpage)
			this.endpage = totalpage;
	}
	
	public int getEndpage() {
		return this.endpage;
	}

}
