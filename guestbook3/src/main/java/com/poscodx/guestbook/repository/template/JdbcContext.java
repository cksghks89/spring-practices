package com.poscodx.guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.poscodx.guestbook.vo.GuestbookVo;

@Component
public class JdbcContext {
	private DataSource dataSource;

	public JdbcContext(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
		List<T> result = new ArrayList<>();
		return executeQueryWithStatementStrategy((connection) -> {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			return pstmt;
		}, rowMapper);
	}

	public <T> T executeQueryForObject(String sql) {
		return null;
	}

	public <T> List<T> executeQueryForObject(String sql, Object[] paramter) {
		return null;
	}

	public int update(StatementStrategy statementStrategy) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			pstmt = statementStrategy.makeStatement(conn);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int update(String sql, Object[] parameters) {
		return executeUpdateWithStatementStrategy((connection) -> {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setObject(i + 1, parameters[i]);
			}
			return pstmt;
		});
	}

	private <E> List<E> executeQueryWithStatementStrategy(StatementStrategy statementStrategy, RowMapper<E> rowMapper) {
		List<E> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			pstmt = statementStrategy.makeStatement(conn);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				E t = rowMapper.mapRow(rs, rs.getRow());
				result.add(t);
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private int executeUpdateWithStatementStrategy(StatementStrategy statementStrategy) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			pstmt = statementStrategy.makeStatement(conn);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
