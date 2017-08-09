package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.UserPosts;
import bulletin_board.exception.NoRowsUpdatedRuntimeException;
import bulletin_board.exception.SQLRuntimeException;

public class UserPostsDao {

	public List<UserPosts> getUserPosts(Connection connection, int num,
			String startDate,String endDate,String category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select ");
			sql.append("posts.subject AS subject, ");
			sql.append("posts.category AS category, ");
			sql.append("posts.text AS text, ");
			sql.append("posts.created_at AS created_at, ");
			sql.append("users.name AS name, ");
			sql.append("posts.user_id AS user_id, ");
			sql.append("posts.id AS id, ");
			sql.append("branches.name as branch_name , ");
			sql.append("departments.name as department_name  ");
			sql.append("from (posts join users , branches ,  departments) ");
			sql.append("where (users.id = posts.user_id) ");
			sql.append("and users.branch_id = branches.id ");
			sql.append("and users.department_id = departments.id ");
			sql.append("and ? <= created_at ");
			sql.append("and ? >= created_at ");


			sql.append("and category = ? ");



			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, startDate + " 23:59:59");
			ps.setString(2, endDate + " 23:59:59");
			ps.setString(3, category);

			System.out.println(ps.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPosts> ret = toUserPostsList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPosts> toUserPostsList(ResultSet rs)
			throws SQLException {

		List<UserPosts> ret = new ArrayList<UserPosts>();
		try {
			while (rs.next()) {
				String subject = rs.getString("subject");
				String category = rs.getString("category");
				String text = rs.getString("text");
				String name = rs.getString("name");
				String branchName = rs.getString("branch_name");
				String departmentName = rs.getString("department_name");
				int userId = rs.getInt("user_id");
				int postId = rs.getInt("id");
				java.sql.Timestamp createdAt = rs.getTimestamp("created_at");

				UserPosts post = new UserPosts();
				post.setSubject(subject);
				post.setCategory(category);
				post.setText(text);
				post.setName(name);
				post.setBranchName(branchName);
				post.setDepartmentName(departmentName);
				post.setUserId(userId);
				post.setId(postId);
				post.setCreatedAt(createdAt);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public void postsDelete(Connection connection,int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM posts ");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
