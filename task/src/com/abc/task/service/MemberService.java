package com.abc.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.abc.task.enums.Plat;
import com.abc.task.exception.DataBaseException;
import com.abc.task.vo.Member;

@Service("memberService")
public class MemberService {
	private static final Log log = LogFactory.getLog(MemberService.class);
	@Resource
	private JdbcTemplate jdbcTemplateUc;

	public Member isBindThirdPlat(Plat plat, final Map<String, String> params) {
		if (MapUtils.isEmpty(params)) {
			return null;
		}

		final Member u = new Member();
		jdbcTemplateUc
				.query("SELECT member_id,member_name FROM uc_member_thirdplat umb WHERE umb.plat=? and umb.open_id=? and umb.token=?",
						new String[] { plat.name(), params.get("openId"),
								params.get("token") },
						new RowCallbackHandler() {
							public void processRow(ResultSet rs)
									throws SQLException {
								u.setId(rs.getInt("member_id"));
								try {
									u.setName(rs.getString("member_name"));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
		if (u.getId() == 0) {
			return null;
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<String> bindPlats(int memberId) {
		try {
			return jdbcTemplateUc
					.queryForList(
							"SELECT plat FROM uc_member_thirdplat umb WHERE umb.member_id=?",
							String.class, memberId);
		} catch (DataAccessException e) {
			log.error(e, e);
			return ListUtils.EMPTY_LIST;
		}
	}

	public Member bindThirdPlat(Plat plat, Member u,
			Map<String, String> thirdPlatParams) throws DataBaseException {
		jdbcTemplateUc
				.update("INSERT INTO uc_member_thirdplat(member_id,member_name,plat,open_id,token) VALUES(?,?,?,?,?)",
						new Object[] { u.getId(), u.getName(), plat.toString(),
								thirdPlatParams.get("openId"),
								thirdPlatParams.get("token") });
		return u;
	}

	public int unBindThirdPlat(Plat plat, int memberId) {
		try {
			return jdbcTemplateUc
					.update("delete from uc_member_thirdplat where member_id = ? and plat = ?",
							new Object[] { memberId, plat.toString() });
		} catch (DataAccessException e) {
			log.error(e, e);
			return -1;
		}
	}

	public synchronized int createMember(final Member u) {
		final String sql = "insert into uc_member(name,email,mobile,password,status) VALUES(?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplateUc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = (PreparedStatement) connection
						.prepareStatement(sql, new String[] {"id"});
				int i = 0;
				ps.setString(++i, u.getName());
				ps.setString(++i, u.getEmail());
				ps.setString(++i, u.getMobile());
				ps.setString(++i, u.getPassword());
				ps.setString(++i, u.getStatus().name());
				return ps;
			}
		}, keyHolder);
		int id = keyHolder.getKey().intValue();
		jdbcTemplateUc.update(
				"insert into uc_account(member_id,member_name) VALUES(?,?)",
				new Object[] { id, u.getName() });
		return id;
	}

	public Member findUser(String column, String value, String fields)
			throws DataBaseException {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		StringBuilder sql = new StringBuilder(100);
		if (StringUtils.isBlank(fields)) {
			fields = "id";
		}
		sql.append("SELECT ").append(fields)
				.append(" FROM uc_member um WHERE um.").append(column)
				.append("=?");
		Member u = null;
		try {
			u = jdbcTemplateUc.queryForObject(sql.toString(),
					new String[] { value }, new MemberRowMapper());
		} catch (DataAccessException e) {
			throw new DataBaseException("");
		}
		return u;
	}

	public boolean checkName(String name) throws DataBaseException {
		try {
			return jdbcTemplateUc.queryForInt(
					"select count(id) from uc_member where name = ?", name) > 0 ? true
					: false;
		} catch (DataAccessException e) {
			throw new DataBaseException(e);
		}
	}

	public boolean checkEmail(String email) throws DataBaseException {
		try {
			return jdbcTemplateUc.queryForInt(
					"select count(id) from uc_member where email = ?", email) > 0 ? true
					: false;
		} catch (DataAccessException e) {
			throw new DataBaseException(e);
		}
	}

	public boolean checkMobile(String mobile) throws DataBaseException {
		try {
			return jdbcTemplateUc.queryForInt(
					"select count(id) from uc_member where mobile = ?", mobile) > 0 ? true
					: false;
		} catch (DataAccessException e) {
			throw new DataBaseException(e);
		}
	}

	public Member findUser(String email, String password)
			throws DataBaseException {
		if (StringUtils.isBlank(password)) {
			return null;
		}
		Member t;
		try {
			t = jdbcTemplateUc
					.queryForObject(
							"select id,name,email,mobile from uc_member where  email = ? and password = ?",
							BeanPropertyRowMapper.newInstance(Member.class),
							new Object[] { email, password });
		} catch (EmptyResultDataAccessException e) {
			t = null;
		} catch (DataAccessException e) {
			throw new DataBaseException(e);
		}
		return t;
	}

	public Member findUserById(int memberId) {
		try {
			return jdbcTemplateUc.queryForObject(
					"select id,name,email,mobile from uc_member where  id = ?",
					Member.class, memberId);
		} catch (DataAccessException e) {
			log.error(e, e);
			return null;
		}
	}

	public int updatePassword(int memberId, String password)
			throws DataBaseException {
		int count = 0;
		try {
			count = jdbcTemplateUc.update(
					"update uc_member set password = ? where  id = ?",
					BeanPropertyRowMapper.newInstance(Member.class),
					new Object[] { password, Integer.valueOf(memberId) });
		} catch (DataAccessException e) {
			throw new DataBaseException(e);
		}
		return count;
	}

	public int updateMember(int memberId, String password) {
		int count = 0;
		try {
			count = jdbcTemplateUc.update(
					"update uc_member set password = ? where  id = ?",
					BeanPropertyRowMapper.newInstance(Member.class),
					new Object[] { password, Integer.valueOf(memberId) });
		} catch (DataAccessException e) {

			throw new DataBaseException(e);
		}
		return count;
	}

	private class MemberRowMapper implements RowMapper<Member> {
		public Member mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {
			Member u = new Member();
			u.setName((String) rs.getString("name"));
			return u;
		}
	}

	public boolean isBlankPassword(int memberId) {
		try {
			String pwd = jdbcTemplateUc.queryForObject(
					"select password from uc_member where id = ?",
					String.class, memberId);
			if (StringUtils.isBlank(pwd)) {
				return true;
			}
			return false;
		} catch (DataAccessException e) {
			log.error(e, e);
			return true;
		}
	}
}
