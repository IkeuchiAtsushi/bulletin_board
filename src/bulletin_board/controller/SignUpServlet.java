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

import bulletin_board.beans.Branch;
import bulletin_board.beans.Department;
import bulletin_board.beans.User;
import bulletin_board.service.BranchService;
import bulletin_board.service.DepartmentService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		List<Branch> branch = new BranchService().getBranches();
		request.setAttribute("branches", branch);

		List<Department> departments = new DepartmentService().getDepartments();
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		List<String>messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

		List<Branch> branch = new BranchService().getBranches();
		request.setAttribute("branches", branch);

		List<Department> departments = new DepartmentService().getDepartments();
		request.setAttribute("departments", departments);

		if(isValid(request,messages) == true){

			new UserService().register(user);

			session.removeAttribute("users");

			response.sendRedirect("userManagement");
		}else{
			session.setAttribute("errorMessages",messages);
			request.setAttribute("users", user);

			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request,List<String>messages){
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int branchId = (Integer.parseInt(request.getParameter("branchId")));
		int departmentId = (Integer.parseInt(request.getParameter("departmentId")));
		String passwordComfirm = request.getParameter("passwordConfirm");

		UserService userService = new UserService();
		User user = userService.getUser(loginId);

		if (StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (!loginId.matches("\\w{6,20}") && StringUtils.isBlank(loginId) == false) {
			messages.add("ログインIDは半角英数字6文字以上～20文字以下で入力してください");
		}
		if(user != null){
			messages.add("このログインIDはすでに使われています");
		}
		if (StringUtils.isBlank(password) == true) {
			messages.add("パスワードをを入力してください");
		}
		if (!password.matches("^[-@+*;:#$%&\\w]{6,20}+$") && StringUtils.isBlank(password) == false && password.matches(passwordComfirm)) {
			messages.add("パスワードは記号も含む全ての半角文字6文字以上～20文字以下で入力してください");
		}
		if (!password.matches(passwordComfirm)) {
			messages.add("入力したパスワードと確認のパスワードが一致しません");
		}
		if (StringUtils.isBlank(name) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (10 < name.length() && StringUtils.isBlank(name) == false) {
			messages.add("アカウント名は10文字以下で入力してください");
		}
		if(branchId == 1 && departmentId >= 3){
			messages.add("支店・部署役職の組み合わせが不正です");
		}
		if(branchId >= 2 && departmentId <= 2){
			messages.add("支店・部署役職の組み合わせが不正です");
		}
		if(messages.size() == 0){
			return true;
		}else{
			return false;
		}
	}
}
