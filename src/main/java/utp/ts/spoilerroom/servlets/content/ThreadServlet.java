package utp.ts.spoilerroom.servlets.content;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import utp.ts.spoilerroom.controllers.AccountController;
import utp.ts.spoilerroom.controllers.ThreadController;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.ThreadModel;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.utilities.DateUtils;

@WebServlet(name = "ThreadServlet", value = "/Thread")
public class ThreadServlet extends HttpServlet
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
			id = Integer.parseInt(request.getParameter("thread"));
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

		Integer accountId = null;

		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");
		if (accountModel != null)
		{
			accountId = accountModel.getAccountEntity().getAccount().getId();
			AccountController.updateLastActiveDate(accountId);
		}

		int rowNumber = ((page - 1) * 10) + 1;

		ThreadController threadController = new ThreadController(id, rowNumber, accountId);
		ThreadModel threadModel = threadController.getThreadModel();

		if (threadModel == null || threadModel.getPostEntityList().size() == 0)
		{
			response.sendRedirect("Error");
			return;
		}

		request.setAttribute("threadModel", threadModel);
		request.setAttribute("postEntityList", threadModel.getPostEntityList());
		request.setAttribute("rowNumber", rowNumber);
		request.setAttribute("currentPage", page);
		request.setAttribute("threadPages", Math.ceil((double) threadModel.getThreadEntity().getThread().getPostCount()/10));

		request.setAttribute("dateUtils", new DateUtils());

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/content/thread.jsp");
		dispatcher.forward(request, response);
	}
}