package com.com.com.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.com.com.dao.EmpDao;

@Repository("dao")
public class EmpDoaImpl implements EmpDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map<String, Object>> getList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.getList");
	}

	@Override
	public void saveEmp(Map<String, Object> item) {
		// TODO Auto-generated method stub
		sqlSession.insert("mapper.saveEmp",item);
	}

	@Override
	public void deleteEmp(Map<String, Object> item) {
		// TODO Auto-generated method stub
		sqlSession.delete("mapper.deleteEmp", item);
	}

	@Override
	public void editEmp(Map<String, Object> item) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.editEmp", item);
	}

}
