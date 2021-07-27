package utp.ts.spoilerroom.servlets.forms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import utp.ts.spoilerroom.controllers.AccountController;

@WebServlet(name = "SignUpServlet", value = "/SignUp")
public class SignUpServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forms/signup.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AccountController accountController;

		String email = request.getParameter("signupEmail");
		String username = request.getParameter("signupUsername");
		String password = request.getParameter("signupPassword");

		String message;

		if (email.equals("") || username.equals("") || password.equals(""))
		{
			message = "Empty Fields";
		}
		else if (password.contains(" ") || password.length() < 3)
		{
			message = "Wrong Password Format";
		}
		else
		{
			accountController = new AccountController(email, username, password);
			response.sendRedirect("Login");
			return;
		}

		request.setAttribute("signUpErrorMessage", message);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forms/signup.jsp");
		dispatcher.forward(request, response);
	}
}