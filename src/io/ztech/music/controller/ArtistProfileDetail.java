package io.ztech.music.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import io.ztech.music.delegates.ArtistProfilesDelegate;

/**
 * Servlet implementation class ArtistProfileDetail
 */
@WebServlet("/ArtistProfileDetail")
public class ArtistProfileDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistProfileDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//RequestDispatcher rd = request.getRequestDispatcher("");
		int id = Integer.parseInt(request.getParameter("id"));
		//request.setAttribute("artistDetail", new ArtistProfilesDelegate().getArtistProfile(id));
		//rd.forward(request, response);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(new ArtistProfilesDelegate().getArtistProfile(id));
	}

}
