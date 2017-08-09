package bulletin_board.dao;

import static bulletin_board.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletin_board.beans.Posts;
import bulletin_board.exception.SQLRuntimeException;

public class PostsDao {

	public void insert(Connection connection, Posts posts) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append("subject");
			sql.append(", text");
			sql.append(", category");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", created_at");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // branchId
			sql.append(", ?"); // departmentId
			sql.append(", CURRENT_TIMESTAMP"); // createdAt
			sql.append(", ?"); // userId
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, posts.getSubject());
			ps.setString(2, posts.getText());
			ps.setString(3, posts.getCategory());
			ps.setInt(4, posts.getBranchId());
			ps.setInt(5, posts.getDepartmentId());
			ps.setInt(6, posts.getUserId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
