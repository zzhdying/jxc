/**
 * 
 */
package com.mglf.base;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author zhongzhuohan
 * @since 2011-4-9
 */
@Repository
public interface BaseMapper {

	/**
	 * @param entity
	 */
	public void insert(BaseEntity entity);

	/**
	 * @param entity
	 */
	public void update(BaseEntity entity);

	/**
	 * 删除一条记录
	 */
	public void delete(Map<String,Object> params);

	/**
	 * 批量删除
	 */
	public void deleteItems(Map<String,Object> params);

	/**
	 * 根据ID获取记录
	 */
	public BaseEntity get(String id);

	@SuppressWarnings("rawtypes")
	public List getAll();

	/**
	 * 根据查询条件，获取记录数 where:查询条件
	 */
	public int getCount(Map<String, Object> map);

	/**
	 * 根据条件查询
	 * 
	 * @param where
	 *            查询条件
	 * @param orderstr
	 *            排序条件
	 * @param limit
	 *            分页条件
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectWhere(Map<String, Object> map);

}
