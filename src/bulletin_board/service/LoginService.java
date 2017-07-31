package bulletin_board.service;

import static bulletin_board.utils.CloseableUtil.*;
import static bulletin_board.utils.DBUtil.*;

import java.sql.Connection;

import bulletin_board.beans.User;
import bulletin_board.dao.UserDao;
import bulletin_board.utils.CipherUtil;

public class LoginService {

	public User login(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao
					.getUser(connection, loginId , encPassword);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
