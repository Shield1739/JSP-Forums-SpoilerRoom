package utp.ts.spoilerroom.servlets.content;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.AccountController;
import utp.ts.spoilerroom.controllers.BoardController;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.BoardModel;
import utp.ts.spoilerroom.utilities.DateUtils;

@WebServlet(name = "BoardServlet", value = "/Board")
public class BoardServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id;
		int page = 1;

		try
		{
			id = Integer.parseInt(request.getParameter("board"));
			if (request.getParameter("page") != null)
			{
				page = Integer.parseInt(request.getParameter("page"));
			}
		}
		catch (NumberFormatException e)
		{
			response.sendRedirect("Error");
			return;
		}

		int rowNumber = ((page - 1) * 10) + 1;

		BoardController boardController = new BoardController(id, rowNumber);
		BoardModel boardModel = boardController.getBoardModel();

		if (boardModel==null || boardModel.getThreadModelList().size() == 0)
		{
			response.sendRedirect("Error");
			return;
		}

		request.setAttribute("boardEntity", boardModel.getBoardEntity());
		request.setAttribute("threadModelList", boardModel.getThreadModelList());
		request.setAttribute("rowNumber", rowNumber);
		request.setAttribute("currentPage", page);
		request.setAttribute("boardPages", Math.ceil((double) boardModel.getBoardEntity().getBoard().getThreadCount()/10));

		request.setAttribute("dateUtils", new DateUtils());

		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");
		if (accountModel != null)
		{
			AccountController.updateLastActiveDate(accountModel.getAccountEntity().getAccount().getId());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/content/board.jsp");
		dispatcher.forward(request, response);
	}
}
