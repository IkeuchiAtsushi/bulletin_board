//package bulletin_board.service;
//
//import static bulletin_board.utils.CloseableUtil.*;
//import static bulletin_board.utils.DBUtil.*;
//
//import java.sql.Connection;
//import java.util.List;
//
//public class MessageService {
//
//	public void register(Message message) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			MessageDao messageDao = new MessageDao();
//			messageDao.insert(connection, message);
//
//			commit(connection);
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//
//	private static final int LIMIT_NUM = 1000;
//
//	public List<UserMessage> getMessage() {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserPostsDao messageDao = new UserPostsDao();
//			List<UserMessage> ret = messageDao.getUserMessages(connection, LIMIT_NUM);
//
//			commit(connection);
//
//			return ret;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//}
