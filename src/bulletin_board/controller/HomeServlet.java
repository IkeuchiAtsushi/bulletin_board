package bulletin_board.controller;

import java.io.IOException;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletin_board.beans.User;
//import bulletin_board.beans.UserPosts;

@WebServlet(urlPatterns = {"/index.jsp"})
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{

		User user = (User)request.getSession().getAttribute("loginUser");

		//List<UserPosts>messages = new MessageService().getMessage();

		request.setAttribute("loginUser", user);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
