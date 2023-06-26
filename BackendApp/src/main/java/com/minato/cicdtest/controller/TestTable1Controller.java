package com.minato.cicdtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.ResponseEntity;
import com.vs.rappit.base.factory.InstanceFactory;
import com.minato.cicdtest.base.controller.TestTable1BaseController;
import com.minato.cicdtest.service.ITestTable1Service;
import com.minato.cicdtest.service.TestTable1Service;
import com.minato.cicdtest.model.TestTable1;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/testtable1s/", produces = "application/json")
public class TestTable1Controller extends TestTable1BaseController<ITestTable1Service<TestTable1>, TestTable1> {
	private static XLogger LOGGER = XLoggerFactory.getXLogger(TestTable1Controller.class);
	public TestTable1Controller(TestTable1Service testtable1Service) {
		super(testtable1Service);
	}
}
