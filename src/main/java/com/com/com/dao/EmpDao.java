package com.com.com.dao;

import java.util.List;
import java.util.Map;

public interface EmpDao {

	List<Map<String, Object>> getList();

	void saveEmp(Map<String, Object> item);

	void deleteEmp(Map<String, Object> map);

	void editEmp(Map<String, Object> item);

}
