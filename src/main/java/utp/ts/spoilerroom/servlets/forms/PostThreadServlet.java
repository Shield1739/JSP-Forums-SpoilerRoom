package utp.ts.spoilerroom.servlets.forms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.BoardController;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.BoardModel;

@WebServlet(name = "PostThreadServlet", value = "/PostThread")
public class PostThreadServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");

		if (accountModel == null)
		{
			System.out.println("ERRROR");
			return; // ERROR
		}

		int accountId = accountModel.getAccountEntity().getAccount().getId();
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String threadTitle = request.getParameter("threadTitle");
		String newThreadContent = request.getParameter("threadContent");
		newThreadContent = newThreadContent.replace("\\", "\\\\");
		newThreadContent = newThreadContent.replace("'", "\\''");

		//TODO DO MEDIA ADD

		int newThreadId = BoardController.postNewThread(boardId, accountId, threadTitle, newThreadContent);

		response.sendRedirect("Thread?thread=" + newThreadId);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");

		if (accountModel != null)
		{
			BoardController boardController = new BoardController(boardId);
			BoardModel boardModel = boardController.getBoardModel();

			request.setAttribute("boardEntity", boardModel.getBoardEntity());

			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forms/postThread.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect("Board?board=" + boardId);
		}
	}
}