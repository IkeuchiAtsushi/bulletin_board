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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.User;
import bulletin_board.beans.UserComment;
import bulletin_board.beans.UserPost;
import bulletin_board.service.CommentService;
import bulletin_board.service.PostsService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/index.jsp"})
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		request.setAttribute("loginUser", loginUser);

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String startDate = "2017/08/01 00:00:00";
		String endDate = sdf.format(date.getTime());
		String category = " ";

		if (StringUtils.isNotBlank(request.getParameter("startDate")) == true) {
			startDate = request.getParameter("startDate");

			request.setAttribute("startDate", startDate);
		}
		if (StringUtils.isNotBlank(request.getParameter("endDate")) == true) {
			endDate = request.getParameter("endDate");

			request.setAttribute("endDate", endDate);
		}
		if (StringUtils.isNotBlank(request.getParameter("category")) == true) {
			category = request.getParameter("category");

			request.setAttribute("selectCategory", category);
		}

		List<UserPost>categories = new PostsService().getCategory();

		session.setAttribute("categories", categories);


		List<UserPost>posts = new PostsService().getPosts(startDate,endDate,category);

		request.setAttribute("posts", posts);

		List<User> users = new UserService().getUsers();

		request.setAttribute("users", users);

		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
