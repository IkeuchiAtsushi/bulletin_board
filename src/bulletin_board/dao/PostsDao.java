//package bulletin_board.dao;
//
//import static bulletin_board.utils.CloseableUtil.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import bulletin_board.exception.SQLRuntimeException;
//
//public class PostsDao {
//
//	public void insert(Connection connection, Message message) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("INSERT INTO message ( ");
//			sql.append("user_id");
//			sql.append(", text");
//			sql.append(", insert_date");
//			sql.append(", update_date");
//			sql.append(") VALUES (");
//			sql.append("?"); // user_id
//			sql.append(", ?"); // text
//			sql.append(", CURRENT_TIMESTAMP"); // insert_date
//			sql.append(", CURRENT_TIMESTAMP"); // update_date
//			sql.append(")");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setInt(1, message.getUserId());
//			ps.setString(2, message.getText());
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}
//
//}
