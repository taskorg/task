package com.abc.task.schedule.award;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.abc.task.enums.MCAccount;
import com.abc.task.enums.MCAccountLogStatus;
import com.abc.task.enums.UCAccount;
import com.abc.task.enums.UCAccountLogStatus;
import com.abc.task.enums.WealthType;

@Component
public class AcountSchedule {
	private static final Log log = LogFactory.getLog(AcountSchedule.class);
	@Resource
	private DataSource dataSourceUc;

	@Scheduled(fixedDelay = 3000)
	protected void dealAccountTempLog() {
		if (log.isDebugEnabled()) {
			log.debug("dealAccountTempLog_start");
		}
		Connection con = null;
		try {
			con = dataSourceUc.getConnection();
			con.setAutoCommit(false);
			__dealAccountTempLog(con);
			con.commit();
		} catch (Exception e) {
			log.error(e, e);
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					log.error(e1, e1);
				}
			}
		} finally {
			JdbcUtils.closeConnection(con);
		}
		if (log.isDebugEnabled()) {
			log.info("dealAccountTempLog_end");
		}
	}

	private void __dealAccountTempLog(Connection con) throws SQLException {
		final int BUF_SIZE = 500;
		Map<Long, Map<MCAccount, Integer>> account_map = new HashMap<Long, Map<MCAccount, Integer>>();
		PreparedStatement stmt_mc_insert = null;
		PreparedStatement stmt_uc_insert = null;
		PreparedStatement stmt_mc_account = null;
		PreparedStatement stmt_mc_update = null;
		Statement stmt = null, stmt_delete = null;
		ResultSet rs = null, rs_account = null;
		try {
			stmt_mc_insert = con
					.prepareStatement("insert into mc_account_log (merchant_id,merchant_name,account_id,wealth_type,account,wealth,serial_number,sub_serial_number,remark,create_time,status,operator,wealth_balance) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt_uc_insert = con
					.prepareStatement("insert into uc_delay_account_log (member_id,member_name,wealth_type,account,wealth,serial_number,sub_serial_number,remark,status,operator,create_time,delay_hours) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt_mc_account = con
					.prepareStatement("select first,second from mc_account where id=?");
			stmt_mc_update = con
					.prepareStatement("update mc_account set first=?,second=? where id=?");
			stmt = con.createStatement();
			stmt_delete = con.createStatement();
			rs = stmt
					.executeQuery("select * from account_log_tmp order by id asc");
			List<Long> log_ids = new ArrayList<Long>(BUF_SIZE);
			int i = 0;
			while (rs.next()) {
				log_ids.add(rs.getLong("id"));
				int mcWealth = rs.getInt("mc_wealth");
				int ucWealth = rs.getInt("uc_wealth");
				WealthType wealthType = WealthType.valueOf(rs
						.getString("wealth_type"));
				UCAccountLogStatus status = UCAccountLogStatus.valueOf(rs
						.getString("status"));
				if (mcWealth != 0) {
					long account_id = rs.getLong("mc_account_id");
					Map<MCAccount, Integer> money = account_map.get(account_id);
					if (money == null) {
						stmt_mc_account.setLong(1, account_id);
						rs_account = stmt_mc_account.executeQuery();
						rs_account.next();
						money = new HashMap<MCAccount, Integer>(2);
						money.put(MCAccount.FIRST, rs_account.getInt("first"));
						money.put(MCAccount.SECOND, rs_account.getInt("second"));
						rs_account.close();
						account_map.put(account_id, money);
					}
					String mcAccount = rs.getString("mc_account");
					MCAccount mcAccountE = MCAccount.valueOf(mcAccount);
					int col = 0;
					int merchantId = rs.getInt("merchant_id");
					stmt_mc_insert.setInt(++col, merchantId);
					stmt_mc_insert.setString(++col,
							rs.getString("merchant_name"));
					stmt_mc_insert.setInt(++col, rs.getInt("mc_account_id"));
					stmt_mc_insert.setString(++col, wealthType.name());
					stmt_mc_insert.setString(++col, mcAccount);
					stmt_mc_insert.setInt(++col, mcWealth);
					stmt_mc_insert.setString(++col,
							rs.getString("serial_number"));
					stmt_mc_insert.setString(++col,
							rs.getString("sub_serial_number"));
					stmt_mc_insert.setString(++col, rs.getString("remark"));
					stmt_mc_insert.setTimestamp(++col,
							rs.getTimestamp("create_time"));
					stmt_mc_insert.setString(++col,
							MCAccountLogStatus.UNCHECK.name());
					stmt_mc_insert.setString(++col, rs.getString("operator"));

					int balance_before = money.get(mcAccountE);
					int balance = balance_before + mcWealth;
					if (balance < 0) {
						log.warn("balance less than 0:" + merchantId);
						break;
					}
					money.put(mcAccountE, balance);
					stmt_mc_insert.setInt(++col, balance);
					stmt_mc_insert.addBatch();
				}
				if (ucWealth != 0) {
					int col = 0;
					stmt_uc_insert.setInt(++col, rs.getInt("member_id"));
					stmt_uc_insert
							.setString(++col, rs.getString("member_name"));
					stmt_uc_insert.setString(++col, wealthType.name());
					stmt_uc_insert.setString(++col, rs.getString("uc_account"));
					stmt_uc_insert.setInt(++col, ucWealth);
					stmt_uc_insert.setString(++col,
							rs.getString("serial_number"));
					stmt_uc_insert.setString(++col,
							rs.getString("sub_serial_number"));
					stmt_uc_insert.setString(++col, rs.getString("remark"));
					stmt_uc_insert.setString(++col, status.name());
					stmt_uc_insert.setString(++col, rs.getString("operator"));
					stmt_uc_insert.setTimestamp(++col,
							rs.getTimestamp("create_time"));
					stmt_uc_insert.setInt(++col, rs.getInt("delay_hours"));
					stmt_uc_insert.addBatch();
				}
				if (++i >= BUF_SIZE) {
					__dealMCAccountTempLog_execute_batch(con, account_map,
							stmt_uc_insert, stmt_mc_insert, stmt_mc_update,
							stmt_delete, log_ids);
					i = 0;
				}
			}

			if (i > 0) {
				__dealMCAccountTempLog_execute_batch(con, account_map,
						stmt_uc_insert, stmt_mc_insert, stmt_mc_update,
						stmt_delete, log_ids);
			}

		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeResultSet(rs_account);
			JdbcUtils.closeStatement(stmt);
			JdbcUtils.closeStatement(stmt_delete);
			JdbcUtils.closeStatement(stmt_mc_insert);
			JdbcUtils.closeStatement(stmt_uc_insert);
			JdbcUtils.closeStatement(stmt_mc_update);
			JdbcUtils.closeStatement(stmt_mc_update);
		}
	}

	private void __dealMCAccountTempLog_execute_batch(Connection con,
			Map<Long, Map<MCAccount, Integer>> account_map,
			PreparedStatement stmt_uc_insert, PreparedStatement stmt_mc_insert,
			PreparedStatement stmt_mc_update, Statement stmt_delete,
			List<Long> log_ids) throws SQLException {
		int[] mc_batch_result = stmt_mc_insert.executeBatch();
		int[] uc_batch_result = stmt_uc_insert.executeBatch();
		String ids_joined = StringUtils.join(log_ids, ',');
		if (log.isDebugEnabled()) {
			log.debug("__dealAccountTempLog_execute_batch: "
					+ Arrays.toString(mc_batch_result)
					+ ", ids will be deleted: " + ids_joined);
			log.debug("__dealAccountTempLog_execute_batch: "
					+ Arrays.toString(uc_batch_result)
					+ ", ids will be deleted: " + ids_joined);
		}
		int effect_count = stmt_delete
				.executeUpdate("delete from account_log_tmp where id in ("
						+ ids_joined + ")");
		if (log_ids.size() != effect_count) {
			throw new RuntimeException("delete effect count ne ids.size");
		}
		log_ids.clear();
		if (account_map.size() > 0) {
			for (Entry<Long, Map<MCAccount, Integer>> entry : account_map
					.entrySet()) {
				Map<MCAccount, Integer> ps = entry.getValue();
				stmt_mc_update.setInt(1, ps.get(MCAccount.FIRST));
				stmt_mc_update.setInt(2, ps.get(MCAccount.SECOND));
				stmt_mc_update.setLong(3, entry.getKey());
				stmt_mc_update.addBatch();
			}
			stmt_mc_update.executeBatch();
			account_map.clear();
		}
		con.commit();
	}

	@Scheduled(fixedDelay = 60000)
	protected void dealDelayAccountLog() {
		if (log.isDebugEnabled()) {
			log.debug("dealDelayAccountLog_start");
		}
		Connection con = null;
		try {
			con = dataSourceUc.getConnection();
			con.setAutoCommit(false);
			__dealDelayAccountLog(con);
			con.commit();
		} catch (Exception e) {
			log.error(e, e);
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					log.error(e1, e1);
				}
			}
		} finally {
			JdbcUtils.closeConnection(con);
		}
		if (log.isDebugEnabled()) {
			log.debug("dealDelayAccountLog_end");
		}
	}

	private void __dealDelayAccountLog(Connection con) throws SQLException {
		final int BUF_SIZE = 500;
		Map<Integer, Map<UCAccount, Integer>> account_map = new HashMap<Integer, Map<UCAccount, Integer>>();
		PreparedStatement stmt_uc_insert = null;
		PreparedStatement stmt_uc_update = null;
		PreparedStatement stmt_uc_account = null;
		Statement stmt = null, stmt_delete = null;
		ResultSet rs = null, rs_account = null;
		try {
			stmt_uc_insert = con
					.prepareStatement("insert into uc_account_log (member_id,member_name,wealth_type,account,wealth,serial_number,sub_serial_number,remark,create_time,status,operator,wealth_balance) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt_uc_account = con
					.prepareStatement("select s1,s2 from uc_account where member_id=?");
			stmt_uc_update = con
					.prepareStatement("update uc_account set s1=?,s2=? where member_id=?");
			stmt = con.createStatement();
			stmt_delete = con.createStatement();
			rs = stmt
					.executeQuery("select * from uc_delay_account_log where status != 'UNCHECK' order by id asc");
			List<Integer> log_ids = new ArrayList<Integer>(BUF_SIZE);
			int i = 0;
			while (rs.next()) {
				int delayId = rs.getInt("id");
				log_ids.add(delayId);
				int memberId = rs.getInt("member_id");
				WealthType wealthType = WealthType.valueOf(rs
						.getString("wealth_type"));
				int wealth = rs.getInt("wealth");
				int delayHours = rs.getInt("delay_hours");
				Timestamp createTime = rs.getTimestamp("create_time");
				UCAccountLogStatus status = UCAccountLogStatus.valueOf(rs
						.getString("status"));
				Map<UCAccount, Integer> money = account_map.get(memberId);
				if (money == null) {
					stmt_uc_account.setLong(1, memberId);
					rs_account = stmt_uc_account.executeQuery();
					rs_account.next();
					money = new HashMap<UCAccount, Integer>(2);
					money.put(UCAccount.S1, rs_account.getInt("s1"));
					money.put(UCAccount.S2, rs_account.getInt("s2"));
					rs_account.close();
					account_map.put(memberId, money);
				}
				String account = rs.getString("account");
				UCAccount accountE = UCAccount.valueOf(account);
				int balance_before = money.get(accountE);
				int balance = balance_before + wealth;
				if (balance < 0) {
					log.warn("balance less than 0:" + memberId);
					break;
				}

				if (status.equals(UCAccountLogStatus.INVALID)) {
					balance = balance_before;
				} else if (status.equals(UCAccountLogStatus.VALID)) {
					Calendar now = Calendar.getInstance();
					now.add(Calendar.HOUR, -delayHours);
					if (createTime.after(now.getTime())) {
						continue;
					}
					status = UCAccountLogStatus.SEND;
				} else {
					log.error("uc_delay_account_log is error,id:" + memberId);
					continue;
				}
				money.put(accountE, balance);
				int col = 0;
				stmt_uc_insert.setInt(++col, memberId);
				stmt_uc_insert.setString(++col, rs.getString("member_name"));
				stmt_uc_insert.setString(++col, wealthType.name());
				stmt_uc_insert.setString(++col, account);
				stmt_uc_insert.setInt(++col, wealth);
				stmt_uc_insert.setString(++col, rs.getString("serial_number"));
				stmt_uc_insert.setString(++col,
						rs.getString("sub_serial_number"));
				stmt_uc_insert.setString(++col, rs.getString("remark"));
				stmt_uc_insert.setTimestamp(++col, createTime);
				stmt_uc_insert.setString(++col, status.name());
				stmt_uc_insert.setString(++col, rs.getString("operator"));

				stmt_uc_insert.setInt(++col, balance);
				stmt_uc_insert.addBatch();
				if (++i >= BUF_SIZE) {
					__dealDelayAccountLog_execute_batch(con, account_map,
							stmt_uc_insert, stmt_uc_update, stmt_delete,
							log_ids);
					i = 0;
				}
			}

			if (i > 0) {
				__dealDelayAccountLog_execute_batch(con, account_map,
						stmt_uc_insert, stmt_uc_update, stmt_delete, log_ids);
			}

		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeResultSet(rs_account);
			JdbcUtils.closeStatement(stmt);
			JdbcUtils.closeStatement(stmt_delete);
			JdbcUtils.closeStatement(stmt_uc_insert);
			JdbcUtils.closeStatement(stmt_uc_account);
			JdbcUtils.closeStatement(stmt_uc_update);
		}
	}

	private void __dealDelayAccountLog_execute_batch(Connection con,
			Map<Integer, Map<UCAccount, Integer>> account_map,
			PreparedStatement stmt_uc_insert, PreparedStatement stmt_uc_update,
			Statement stmt_delete, List<Integer> log_ids) throws SQLException {
		int[] uc_batch_result = stmt_uc_insert.executeBatch();
		String ids_joined = StringUtils.join(log_ids, ',');
		if (log.isDebugEnabled()) {
			log.debug("__dealDealyAccountLog_execute_batch: "
					+ Arrays.toString(uc_batch_result)
					+ ", ids will be deleted: " + ids_joined);
		}
		int effect_count = stmt_delete
				.executeUpdate("delete from uc_delay_account_log where id in ("
						+ ids_joined + ")");
		if (log_ids.size() != effect_count) {
			throw new RuntimeException("delete effect count ne ids.size");
		}
		log_ids.clear();
		if (account_map.size() > 0) {
			for (Entry<Integer, Map<UCAccount, Integer>> entry : account_map
					.entrySet()) {
				Map<UCAccount, Integer> ps = entry.getValue();
				stmt_uc_update.setInt(1, ps.get(UCAccount.S1));
				stmt_uc_update.setInt(2, ps.get(UCAccount.S2));
				stmt_uc_update.setLong(3, entry.getKey());
				stmt_uc_update.addBatch();
			}
			stmt_uc_update.executeBatch();
			account_map.clear();
		}
		con.commit();
	}
}
