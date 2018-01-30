package io.ztech.music.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import io.ztech.music.delegates.RecordsDelegate;

/**
 * Servlet implementation class RecordDetail
 */
@WebServlet("/RecordDetail")
public class RecordDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordDetail() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONObject result = new RecordsDelegate().getRecordByName(request.getParameter("recordName"));
		PrintWriter out = response.getWriter();
		out.print(result);		
	}

}
