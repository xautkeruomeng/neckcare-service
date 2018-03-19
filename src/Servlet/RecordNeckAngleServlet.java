package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.RecordNeckAngleService;
import Service.recordMyLocationService;

public class RecordNeckAngleServlet extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//接收客户端信息
		String neckAngle = request.getParameter("neckAngle");
		String userName = request.getParameter("userName");
		
		neckAngle = new String(neckAngle.getBytes("ISO-8859-1"),"UTF-8");
		userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");

		//新建服务对象
		RecordNeckAngleService recordService = new RecordNeckAngleService();
		boolean isTrue = recordService.recordAngle(neckAngle,userName);
		
		//返回信息到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(isTrue);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//接收客户端信息
		String neckAngle = request.getParameter("neckAngle");
		String userName = request.getParameter("userName");
		
		neckAngle = new String(neckAngle.getBytes("ISO-8859-1"),"UTF-8");
		userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
		
		//新建服务对象
		RecordNeckAngleService recordService = new RecordNeckAngleService();
		boolean isTrue = recordService.recordAngle(neckAngle,userName);
		
		//返回信息到客户端
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(isTrue);
	}
}
