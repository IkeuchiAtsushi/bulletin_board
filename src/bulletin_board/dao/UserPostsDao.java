package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.UserPost;
import bulletin_board.exception.NoRowsUpdatedRuntimeException;
import bulletin_board.exception.SQLRuntimeException;

public class UserPostsDao {

	public List<UserPost> getUserPosts(Connection connection, int num,
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
			sql.append("departments.name as department_name, ");
			sql.append("posts.branch_id AS branch_id, ");
			sql.append("posts.department_id as department_id ");
			sql.append("from (posts join users , branches ,  departments) ");
			sql.append("where (users.id = posts.user_id) ");
			sql.append("and users.branch_id = branches.id ");
			sql.append("and users.department_id = departments.id ");
			sql.append("and ? <= created_at ");
			sql.append("and ? >= created_at ");

			if (StringUtils.isNotBlank(category) == true) {
				sql.append("and category = ? ");
			}
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, startDate + " 00:00:00");
			ps.setString(2, endDate + " 23:59:59");

			if (StringUtils.isNotBlank(category) == true) {
			ps.setString(3, category);
			}

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toUserPostsList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPost> toUserPostsList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {
				String subject = rs.getString("subject");
				String category = rs.getString("category");
				String text = rs.getString("text");
				String name = rs.getString("name");
				String branchName = rs.getString("branch_name");
				String departmentName = rs.getString("department_name");
				int branchId = rs.getInt("branch_id");
				int departmentId = rs.getInt("department_id");
				int userId = rs.getInt("user_id");
				int postId = rs.getInt("id");
				java.sql.Timestamp createdAt = rs.getTimestamp("created_at");

				UserPost post = new UserPost();
				post.setSubject(subject);
				post.setCategory(category);
				post.setText(text);
				post.setName(name);
				post.setBranchName(branchName);
				post.setDepartmentName(departmentName);
				post.setBranchId(branchId);
				post.setDepartmentId(departmentId);
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
	public List<UserPost> getCategory(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select distinct category ");
			sql.append("from posts ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toCategoryList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<UserPost> toCategoryList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				UserPost post = new UserPost();
				post.setCategory(category);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
