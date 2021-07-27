package utp.ts.spoilerroom.servlets.content;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.AccountController;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.utilities.DateUtils;

@WebServlet(name = "AccountServlet", value = "/Account")
public class AccountServlet extends HttpServlet
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
			id = Integer.parseInt(request.getParameter("account"));
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

		AccountController accountController = new AccountController(id, rowNumber);
		AccountModel accountModel = accountController.getAccountModel();

		request.setAttribute("accountEntity", accountModel.getAccountEntity());
		request.setAttribute("postEntityList", accountModel.getPostEntityList());
		request.setAttribute("rowNumber", rowNumber);
		request.setAttribute("currentPage", page);
		request.setAttribute("accountPages", Math.ceil((double) accountModel.getAccountEntity().getAccount().getPostCount()/10));

		request.setAttribute("dateUtils", new DateUtils());


		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/content/account.jsp");
		dispatcher.forward(request, response);
	}
}
