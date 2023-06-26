package com.minato.cicdtest.service;

import com.vs.rappit.base.acl.IPerimeterManager;
import com.minato.cicdtest.base.service.TestTable1BaseService;
import com.minato.cicdtest.model.TestTable1;
import com.minato.cicdtest.service.TestTable1PerimeterImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service("TestTable1")
public class TestTable1Service extends TestTable1BaseService<TestTable1> implements ITestTable1Service<TestTable1>{

		@Autowired
		private  TestTable1PerimeterImpl  testtable1PerimeterImpl;

		public TestTable1Service(ChangelogService changelogService) {
		super(TestTable1.class);	
		setChangelogService(changelogService); 
		
	}
	
	
	protected IPerimeterManager<TestTable1> getPerimeterManager() {
		return testtable1PerimeterImpl;
	}
}