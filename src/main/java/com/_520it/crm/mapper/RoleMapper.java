package com._520it.crm.mapper;

import com._520it.crm.domain.Role;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);
	Long queryPageCount(QueryObject qo);
	List<Role> queryPageData(QueryObject qo);

    void insertPermission(@Param("roleId") Long roleId,@Param("perId") Long perId);

    List<Long> queryByShowRole(Long empId);

    void deleteByRole(Long id);
}