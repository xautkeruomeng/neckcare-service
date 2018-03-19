package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.RecordNeckAngleService;
import Service.RecordTimeService;

public class RecordTimeServlet extends HttpServlet{

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//接收客户端信息
		String time = request.getParameter("time");
		String userName = request.getParameter("userName");
		String avergeAngle = request.getParameter("avergeAngle");
		String maxAngle = request.getParameter("maxAngle");
		
		time = new String(time.getBytes("ISO-8859-1"),"UTF-8");
		userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
		avergeAngle = new String(avergeAngle.getBytes("ISO-8859-1"),"UTF-8");
		maxAngle = new String(maxAngle.getBytes("ISO-8859-1"),"UTF-8");

		//新建服务对象
		RecordTimeService recordService = new RecordTimeService();
		boolean isTrue = recordService.recordTime(time,userName,avergeAngle,maxAngle);
		
		//返回信息到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(isTrue);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//接收客户端信息
		String time = request.getParameter("time");
		String userName = request.getParameter("userName");
		String avergeAngle = request.getParameter("avergeAngle");
		String maxAngle = request.getParameter("maxAngle");
		
		time = new String(time.getBytes("ISO-8859-1"),"UTF-8");
		userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
		avergeAngle = new String(avergeAngle.getBytes("ISO-8859-1"),"UTF-8");
		maxAngle = new String(maxAngle.getBytes("ISO-8859-1"),"UTF-8");

		//新建服务对象
		RecordTimeService recordService = new RecordTimeService();
		boolean isTrue = recordService.recordTime(time,userName,avergeAngle,maxAngle);
		
		//返回信息到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(isTrue);
	}
}
