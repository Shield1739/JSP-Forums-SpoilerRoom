package utp.ts.spoilerroom.servlets.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.ThreadController;

@WebServlet(name = "DeletePostServlet", value = "/DeletePost")
public class DeletePostServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int threadId;
		int postId;
		int postParentId = -1;

		try
		{
			threadId = Integer.parseInt(request.getParameter("deleteThreadId"));
			postId = Integer.parseInt(request.getParameter("deletePostId"));
			if (!request.getParameter("deletePostParentIdInput").equals(""))
			{
				postParentId = Integer.parseInt(request.getParameter("deletePostParentIdInput"));
			}
		}
		catch (NumberFormatException e)
		{
			response.sendRedirect("Error");
			return;
		}

		if (postParentId == -1)
		{
			ThreadController.deleteThread(threadId);
			response.sendRedirect("Board?board=" + request.getParameter("boardId"));
		}
		else
		{
			ThreadController.deletePost(postId, postParentId);
			response.sendRedirect("Thread?thread=" + threadId);
		}
	}
}
