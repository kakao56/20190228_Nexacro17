package com.com.com.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.com.com.dao.EmpDao;
import com.com.com.service.EmpService;
import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataTypes;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.VariableList;
import com.nexacro17.xapi.tx.HttpPlatformRequest;
import com.nexacro17.xapi.tx.HttpPlatformResponse;
import com.nexacro17.xapi.tx.PlatformType;

@Service("service")
public class EmpServiceImpl implements EmpService {

	@Resource(name="dao")
	private EmpDao empDao;

	@Override
	public void getList(HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = empDao.getList();
		
		if(list == null) {
			return;
		}
		
		PlatformData pData = new PlatformData();
		
		DataSet ds = new DataSet("empList");	
		ds.addColumn("m_idx", DataTypes.STRING, 210);
		ds.addColumn("m_id", DataTypes.STRING, 256);
		ds.addColumn("m_pw", DataTypes.STRING, 256);
		ds.addColumn("m_name", DataTypes.STRING, 256);
		ds.addColumn("m_level", DataTypes.STRING, 256);
		
		Iterator<Map<String, Object>> it = list.iterator();
		
		while(it.hasNext()) {
			
			Map<String, Object> item = it.next();
			
			int rownum = ds.newRow();
			
			ds.set(rownum, "m_idx", item.get("m_idx").toString());
			ds.set(rownum, "m_id", item.get("m_id").toString());
			ds.set(rownum, "m_pw", item.get("m_pw").toString());
			ds.set(rownum, "m_name", item.get("m_name").toString());
			ds.set(rownum, "m_level", item.get("m_level").toString());
		}
		
		pData.addDataSet(ds);
		
		VariableList vl = pData.getVariableList();
		vl.add("ErrorCode", "11");
		vl.add("ErrorMsg" , "ㅋㅋ");
		
		HttpPlatformResponse pRes = new HttpPlatformResponse(res,PlatformType.CONTENT_TYPE_XML,"UTF-8");
		pRes.setData(pData);
		pRes.sendData();
	
	}

	@Override
	public void saveEmp(HttpServletResponse res, HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		HttpPlatformRequest pReq = new HttpPlatformRequest(req);
		pReq.receiveData();
		
		PlatformData pData = pReq.getData();
		
		DataSet ds = pData.getDataSet("saveList");

		Map<String, Object> item = new HashMap<String, Object>();

		for(int i = 0; i<ds.getRowCount(); i++) {
			
			item.put("m_idx", ds.getString(i, "m_idx"));
			item.put("m_id", ds.getString(i, "m_id"));
			item.put("m_pw", ds.getString(i, "m_pw"));
			item.put("m_name", ds.getString(i, "m_name"));
			item.put("m_level", ds.getString(i, "m_level"));
			
			switch (ds.getRowType(i)) {
			
			case DataSet.ROW_TYPE_INSERTED:				
				empDao.saveEmp(item);			
				break;						
			case DataSet.ROW_TYPE_UPDATED:				
				empDao.editEmp(item);				
				break;
			}		
			
		}
		
		getList(res);
	}

	@Override
	public void deleteEmp(HttpServletResponse res, HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		HttpPlatformRequest pReq = new HttpPlatformRequest(req);
		pReq.receiveData();
		
		PlatformData pData = pReq.getData();
		
		DataSet ds = pData.getDataSet("deleteList");
		
		Map<String, Object> item = new HashMap<String, Object>();
		
		for(int i=0; i<ds.getRemovedRowCount(); i++) {
			
			item.put("m_idx", ds.getRemovedData(i, "m_idx"));
			
			empDao.deleteEmp(item);
		}
		
		getList(res);
	}
	

}
