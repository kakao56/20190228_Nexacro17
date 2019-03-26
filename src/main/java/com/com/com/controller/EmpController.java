package com.com.com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.com.com.service.EmpService;


@Controller
public class EmpController {

	@Resource(name="service")
	private EmpService empService;	
	
	@RequestMapping("getlist")
	public void getList(HttpServletResponse res) throws Exception {
		empService.getList(res);
	}

	@RequestMapping("saveemp")
	public void saveEmp(HttpServletResponse res, HttpServletRequest req) throws Exception {
		empService.saveEmp(res, req);
	}
	
	@RequestMapping("deleteemp")
	public void deleteEmp(HttpServletResponse res, HttpServletRequest req) throws Exception {
		empService.deleteEmp(res, req);
	}
}
