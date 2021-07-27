package utp.ts.spoilerroom.servlets.forms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.AccountController;
import utp.ts.spoilerroom.models.AccountModel;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forms/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AccountController accountController;

		String email = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");

		String message = "";

		if (email.equals("") || password.equals(""))
		{
			message = "Empty Fields";
		}
		else
		{
			accountController = new AccountController(email, password);
			AccountModel accountModel = accountController.getAccountModel();

			if (accountModel != null)
			{
				request.getSession().setAttribute("loggedAccountModel", accountModel);
				response.sendRedirect("Home");
				return;
			}
			else
			{
				message = "Invalid password/email";
			}
		}

		request.setAttribute("loginErrorMessage", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forms/login.jsp");
		dispatcher.forward(request, response);
	}
}