package utp.ts.spoilerroom.servlets.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.ThreadController;

@WebServlet(name = "VoteServlet", value = "/Vote")
public class VoteServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		int postId;
		int accountId;
		int voteType;

		try
		{
			postId = Integer.parseInt(request.getParameter("postId"));
			accountId = Integer.parseInt(request.getParameter("accountId"));
			voteType = Integer.parseInt(request.getParameter("voteType"));
		}
		catch (NumberFormatException e)
		{
			response.sendRedirect("Error");
			return;
		}



		ThreadController.manageVote(postId, accountId, voteType);

		response.sendRedirect("Thread?thread=" + request.getParameter("threadId"));
	}
}
