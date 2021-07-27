package utp.ts.spoilerroom.servlets.content;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.AccountController;
import utp.ts.spoilerroom.controllers.HomeController;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.HomeModel;
import utp.ts.spoilerroom.utilities.DateUtils;

@WebServlet(name = "HomeServlet", value = "/Home")
public class HomeServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeController homeController = new HomeController();
		HomeModel homeModel = homeController.getHomeModel();

		request.setAttribute("streamingMediaList", homeModel.getStreamingMediaEntityList());
		request.setAttribute("streamingMediaListSlides", (Math.ceil(homeModel.getStreamingMediaEntityList().size()/4.0)-1));

		request.setAttribute("mostActiveThreadsList", homeModel.getMostActiveThreadModelList());
		request.setAttribute("latestThreadsList", homeModel.getLatestThreadModelList());
		request.setAttribute("announcementThreadsList", homeModel.getAnnouncementsThreadModelList());

		request.setAttribute("topAccount", homeModel.getAccountModel().getAccountEntity().getAccount());

		request.setAttribute("memberCount", homeModel.getMemberCount());
		request.setAttribute("threadCount", homeModel.getThreadCount());
		request.setAttribute("postCount", homeModel.getPostCount());

		request.setAttribute("dateUtils", new DateUtils());

		AccountModel accountModel = (AccountModel) request.getSession().getAttribute("loggedAccountModel");
		if (accountModel != null)
		{
			AccountController.updateLastActiveDate(accountModel.getAccountEntity().getAccount().getId());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/content/home.jsp");
		dispatcher.forward(request, response);
	}
}
