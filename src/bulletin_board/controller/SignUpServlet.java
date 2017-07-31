package bulletin_board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.User;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{
		List<String>messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User user = new User();
		user.setLoginId(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		user.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));

		if(isValid(request,messages) == true){


			new UserService().register(user);
			session.removeAttribute("users");

			response.sendRedirect("./");
		}else{
			session.setAttribute("errorMessages",messages);
			request.setAttribute("users", user);
			request.getRequestDispatcher("signup.jsp").forward(request, response);

		}
	}

	private boolean isValid(HttpServletRequest request,List<String>messages){
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		if(StringUtils.isEmpty(login_id) == true){
			messages.add("IDを入力してください");
		}
		if(StringUtils.isEmpty(password) == true){
			messages.add("パスワードを入力してください");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}
}
