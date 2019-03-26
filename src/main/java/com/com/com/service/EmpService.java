package com.com.com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EmpService {

	void getList(HttpServletResponse res) throws Exception;

	void saveEmp(HttpServletResponse res, HttpServletRequest req) throws Exception;

	void deleteEmp(HttpServletResponse res, HttpServletRequest req) throws Exception;

}
