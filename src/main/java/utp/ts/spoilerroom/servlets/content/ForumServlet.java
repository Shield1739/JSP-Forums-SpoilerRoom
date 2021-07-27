package utp.ts.spoilerroom.servlets.content;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import utp.ts.spoilerroom.controllers.AccountController;
import utp.ts.spoilerroom.controllers.ForumController;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.SectionModel;
import utp.ts.spoilerroom.utilities.DateUtils;

@WebServlet(name = "ForumServlet", value = "/Forum")
public class ForumServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ForumController forumController = new ForumController();
		List<SectionModel> sectionModelList = forumController.getSectionModelList();

		request.setAttribute("sectionModelList", sectionModelList);

		request.setAttribute("dateUtils", new DateUtils());

		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");
		if (accountModel != null)
		{
			AccountController.updateLastActiveDate(accountModel.getAccountEntity().getAccount().getId());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/content/forum.jsp");
		dispatcher.forward(request, response);
	}
}
