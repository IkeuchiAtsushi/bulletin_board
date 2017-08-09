package bulletin_board.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.User;
import bulletin_board.beans.UserComment;
import bulletin_board.beans.UserPosts;
import bulletin_board.service.CommentService;
import bulletin_board.service.PostsService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/index.jsp"})
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String category = request.getParameter("category");

		if (StringUtils.isNotBlank(startDate) == true) {
			startDate = "2017/08/01 00:00:00";
		}
		if (StringUtils.isNotBlank(endDate) == true) {
			endDate = sdf.format(date.getTime());
		}
		if (StringUtils.isNotBlank(category) == true) {
			response.sendRedirect("./");
		}

		List<UserPosts>posts = new PostsService().getPosts(startDate,endDate,category);

		request.setAttribute("posts", posts);

		List<User> users = new UserService().getUsers();

		request.setAttribute("users", users);

		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
