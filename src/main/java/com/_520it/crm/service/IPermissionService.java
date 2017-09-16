package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Permission;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IPermissionService {
	PageResult queryPage(QueryObject qo);

    void reload();
}
