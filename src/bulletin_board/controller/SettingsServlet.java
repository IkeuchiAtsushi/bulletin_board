package bulletin_board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@WebServlet(urlPatterns = { "/settings" })
@MultipartConfig(maxFileSize = 100000)
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserService userService = new UserService();

		if(StringUtils.isNotBlank(request.getParameter("id")) == false ||
				!(request.getParameter("id").matches("^[0-9]+$")) ||
					userService.getUser(Integer.parseInt(request.getParameter("id")))== null){

			List<String> messages = new ArrayList<String>();
			messages.add("不正な値が入力されました");

			session.setAttribute("errorMessages", messages);
			response.sendRedirect("userManagement");
			return;
		}

		User editUser = new UserService().getUser(Integer.parseInt(request.getParameter("id")));
		session.setAttribute("editUser", editUser);

		User loginUser = (User) session.getAttribute("loginUser");
		request.setAttribute("loginUser", loginUser);

		List<Branch> branch = new BranchService().getBranches();
		request.setAttribute("branches", branch);

		List<Department> departments = new DepartmentService().getDepartments();
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("/settings.jsp").forward(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User editUser = getEditUser(request);
		session.setAttribute("editUser", editUser);

		List<Branch> branch = new BranchService().getBranches();
		request.setAttribute("branches", branch);

		List<Department> departments = new DepartmentService().getDepartments();
		request.setAttribute("departments", departments);

		if (isValid(request, messages) == true) {

			new UserService().update(editUser);

			session.setAttribute("editUser", editUser);
			session.removeAttribute("editUser");

			response.sendRedirect("userManagement");
		} else {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("/settings.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		editUser.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		int branchId = (Integer.parseInt(request.getParameter("branchId")));
		int departmentId = (Integer.parseInt(request.getParameter("departmentId")));
		String passwordComfirm = request.getParameter("passwordConfirm");
		int id = (Integer.parseInt(request.getParameter("id")));

		UserService userService = new UserService();
		User user = userService.getUser(loginId);

		if (StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (!loginId.matches("\\w{6,20}") && StringUtils.isBlank(loginId) == false) {
			messages.add("ログインIDは半角英数字6文字以上～20文字以下で入力してください");
		}
		if(user != null && id != user.getId()){
			messages.add("このログインIDはすでに使われています");
		}
		if (!password.matches("^[-@+*;:#$%&\\w]{6,20}+$") && StringUtils.isBlank(password) == false) {
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
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
