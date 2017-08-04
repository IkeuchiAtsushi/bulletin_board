package bulletin_board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletin_board.beans.User;
import bulletin_board.beans.UserPosts;
import bulletin_board.service.PostsService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/index.jsp"})
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{

		List<UserPosts>posts = new PostsService().getPosts();

		request.setAttribute("Posts", posts);

		List<User> users = new UserService().getUsers();
		request.setAttribute("users", users);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
