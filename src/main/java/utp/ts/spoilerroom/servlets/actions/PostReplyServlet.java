package utp.ts.spoilerroom.servlets.actions;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.ThreadController;
import utp.ts.spoilerroom.models.AccountModel;


@WebServlet(name = "PostReplyServlet", value = "/PostReply")
public class PostReplyServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int threadId = Integer.parseInt(request.getParameter("threadId"));
		int lastPostId = Integer.parseInt(request.getParameter("lastPostId"));
		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");
		String replyContent = request.getParameter("postReplyContent");
		replyContent = replyContent.replace("\\", "\\\\");
		replyContent = replyContent.replace("'", "\\''");

		if (accountModel != null)
		{
			ThreadController.postReply(threadId, lastPostId, accountModel.getAccountEntity().getAccount().getId(), replyContent);
		}

		response.sendRedirect("Thread?thread=" + request.getParameter("threadId"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("HOW GET?");
	}
}