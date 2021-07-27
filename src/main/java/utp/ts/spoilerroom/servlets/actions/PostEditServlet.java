package utp.ts.spoilerroom.servlets.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.ThreadController;
import utp.ts.spoilerroom.models.AccountModel;

@WebServlet(name = "PostEditServlet", value = "/PostEdit")
public class PostEditServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int threadId = Integer.parseInt(request.getParameter("threadId"));
		int postId = Integer.parseInt(request.getParameter("postId"));
		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");
		String editedContent = request.getParameter("postEditContent");
		editedContent = editedContent.replace("\\", "\\\\");
		editedContent = editedContent.replace("'", "\\''");

		if (accountModel != null)
		{
			ThreadController.editPost(postId, accountModel.getAccountEntity().getAccount().getId(), editedContent);
		}

		response.sendRedirect("Thread?thread=" + request.getParameter("threadId"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("HOW GET?");
	}
}
