package com.poscodx.guestbook.repository;

import org.springframework.stereotype.Repository;

import com.poscodx.guestbook.repository.template.JdbcContext;
import com.poscodx.guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepositoryWithJdbcContext {
	private JdbcContext jdbcContext;

	public GuestbookRepositoryWithJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	public int deleteByNoAndPassword(Long no, String password) {
		return jdbcContext.executeUpdate("delete from guestbook where no = ? and password = ?",
				new Object[] { no, password });

//		return jdbcContext.executeUpdate(new StatementStrategy() {
//			@Override
//			public PreparedStatement makeStatement(Connection connection) throws SQLException {
//				PreparedStatement pstmt = connection
//						.prepareStatement("delete from guestbook where no = ? and password = ?");
//				pstmt.setLong(1, no);
//				pstmt.setString(2, password);
//				return pstmt;
//			}
//		});
	}

	public int insert(GuestbookVo vo) {
		return jdbcContext.executeUpdate("insert into guestbook values(null, ?, ?, ?, now())",
				new Object[] { vo.getName(), vo.getPassword(), vo.getContents() });
//		return jdbcContext.executeUpdate(new StatementStrategy() {
//			@Override
//			public PreparedStatement makeStatement(Connection connection) throws SQLException {
//				PreparedStatement pstmt = connection
//						.prepareStatement("insert into guestbook values(null, ?, ?, ?, now())");
//				pstmt.setString(1, vo.getName());
//				pstmt.setString(2, vo.getPassword());
//				pstmt.setString(3, vo.getContents());
//
//				return pstmt;
//			}
//		});
	}
}
