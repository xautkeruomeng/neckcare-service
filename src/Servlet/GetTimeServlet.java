package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.GetTimeService;
import Service.RecordTimeService;

public class GetTimeServlet extends HttpServlet{

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		//接收客户端信息
		String userName = request.getParameter("userName");
		userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
		
		List<Map<String,Object>> lists= new ArrayList<Map<String,Object>>();
		
		//新建服务对象
		GetTimeService recordService = new GetTimeService();
		lists = recordService.getTime(userName);
		
		//返回信息到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(lists);
	}
public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		//接收客户端信息
		String userName = request.getParameter("userName");
	
		List<Map<String,Object>> lists= new ArrayList<Map<String,Object>>();
		
		//新建服务对象
		GetTimeService recordService = new GetTimeService();
		lists = recordService.getTime(userName);
		
		//返回信息到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(lists);
	}
}
