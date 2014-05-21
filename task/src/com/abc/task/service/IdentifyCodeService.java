package com.abc.task.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.abc.task.exception.DataBaseException;

@Service("identifyCodeService")
public class IdentifyCodeService {
	@Resource
	private JdbcTemplate jdbcTemplateUc;

	public int insertCode(String key, String code) throws DataBaseException {
		try {
			int count = jdbcTemplateUc.queryForInt(
					"select count(id) from uc_identify_code where k=?",
					new Object[] { key });
			if (count == 0) {
				return jdbcTemplateUc.update(
						"insert into uc_identify_code(k,c) values(?,?)",
						new Object[] { key, code });
			} else {
				return jdbcTemplateUc.update(
						"update uc_identify_code set c=? where k = ?",
						new Object[] { code, key });
			}
		} catch (Exception e) {
			throw new DataBaseException(e);
		}
	}

	public boolean selectCode(String key, String code) throws DataBaseException {
		try {
			int count = jdbcTemplateUc.queryForInt(
					"select count(id) from uc_identify_code where k=? and c=?",
					new Object[] { key, code });
			if (count == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new DataBaseException(e);
		}
	}
}
