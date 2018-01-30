package io.ztech.music.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import io.ztech.music.delegates.ArtistProfilesDelegate;

/**
 * Servlet implementation class Artists
 */
@WebServlet("/artists")
public class Artists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Artists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject artistDetail = new JSONObject(request.getParameter("artist"));
		ArtistProfilesDelegate delegate = new ArtistProfilesDelegate();
		JSONObject result = delegate.insertArtistProfile(artistDetail);
		RequestDispatcher dispatcher = request.getRequestDispatcher("success");
		request.setAttribute("result", result);
		dispatcher.forward(request, response);
	}

}
