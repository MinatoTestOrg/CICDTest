package com.minato.cicdtest.base.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.vs.rappit.base.dal.providers.PersistenceType;
import com.vs.rappit.base.acl.AllowedFields;
import com.vs.rappit.base.authentication.logic.AppUserPrivilegeCache;
import com.vs.rappit.base.acl.IPerimeterManager;
import com.minato.cicdtest.base.model.TestTable1Base;
import com.minato.cicdtest.base.model.ApplicationUserBase;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public abstract class TestTable1PerimeterBaseImpl<T extends TestTable1Base> implements IPerimeterManager<T> {
	
	@Autowired
	private AppUserPrivilegeCache<ApplicationUserBase> userCache;
	
	@Override
	public boolean canCreate(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public boolean canUpdate(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public boolean canDelete(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public boolean canRead(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public String getAccessQuery(PersistenceType type) {
		return null;
	}

	@Override
	public AllowedFields getSelectFields(PersistenceType type) {
		AllowedFields allowedFields = new AllowedFields();
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		setReadFields(userBase, allowedFields);
		setWriteFields(userBase, allowedFields);
		return allowedFields;
	}
	
	protected void setReadFields(ApplicationUserBase userBase, AllowedFields allowedFields) {
		List<String> allowedAccessFields = new ArrayList<>();
		allowedAccessFields.addAll(getTechnicalFields());
		if(BooleanUtils.isTrue(userBase.isDevAdmin())) {
			allowedAccessFields.add("*");
			allowedFields.setAllowedReadFields(allowedAccessFields);
			return;
		}
		
		allowedFields.setAllowedReadFields(allowedAccessFields);
	}
	protected void setWriteFields(ApplicationUserBase userBase, AllowedFields allowedFields) {
		List<String> allowedAccessFields = new ArrayList<>();
		allowedAccessFields.addAll(getTechnicalFields());
		if(BooleanUtils.isTrue(userBase.isDevAdmin())) {
			allowedAccessFields.add("*");
			allowedFields.setAllowedWriteFields(allowedAccessFields);
			return;
		}
		
		allowedFields.setAllowedWriteFields(allowedAccessFields);
	}
	protected List<String> getTechnicalFields() {
		String[] technicalFields = {"sid", "createdBy", "createdDate", "modifiedBy", "modifiedDate", "recDeleted"};
		return Arrays.asList(technicalFields);
	}
	
	
}
