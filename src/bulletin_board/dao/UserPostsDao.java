//package bulletin_board.dao;
//
//import static bulletin_board.utils.CloseableUtil.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import bulletin_board.beans.UserPosts;
//import bulletin_board.exception.SQLRuntimeException;
//
//public class UserPostsDao {
//
//	public List<UserPosts> getUserPosts(Connection connection, int num) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT * FROM users ");
//			sql.append("ORDER BY insert_date DESC limit " + num);
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ResultSet rs = ps.executeQuery();
//			List<UserPosts> ret = toUserPostsList(rs);
//			return ret;
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}
//
//	private List<UserPosts> toUserPostsList(ResultSet rs)
//			throws SQLException {
//
//		List<UserPosts> ret = new ArrayList<UserPosts>();
//		try {
//			while (rs.next()) {
//				String subject = rs.getString("subject");
//				String text = rs.getString("text");
//				String category = rs.getString("category");
//				int branchId = rs.getInt("branchId");
//				int departmentId = rs.getInt("departmentId");
//				int userId = rs.getInt("userId");
//
//				UserPosts post = new UserPosts();
//				post.setSubject(subject);
//				post.setText(text);
//				post.setCategory(category);
//				post.setBranchId(branchId);
//				post.setDepartmentId(departmentId);
//				post.setUserId(userId);
//
//				ret.add(post);
//			}
//			return ret;
//		} finally {
//			close(rs);
//		}
//	}
//
//}
