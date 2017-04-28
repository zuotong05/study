package com.unisafecap.framework.manager;

import java.util.List;
import java.util.Map;

import com.unisafecap.framework.common.enums.Order;
import com.unisafecap.framework.dao.BaseDao;
import com.unisafecap.framework.dao.Page;

public abstract class BaseManager<T> {

	public BaseManager() {}

	/**
	 * 由业务类实现
	 * 
	 * @return
	 */
	public abstract BaseDao<T> getBaseDao();

	/**
	 * 根据id,查询单个
	 * 
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return getBaseDao().get(id);
	}

	/**
	 * 插入单个对象
	 * 
	 * @param model
	 * @return
	 */
	public int create(T model) {
		return getBaseDao().insert(model);
	}

	/**
	 * 插入单个对象,如果o属性不为空,则插入
	 * 
	 * @param model
	 * @return
	 */
	public int create4Selective(T model) {
		return getBaseDao().insert4Selective(model);
	}

	/**
	 * 根据主键更新对象,更新对象所有字段
	 * 
	 * @param model
	 * @return
	 */
	public int update(T model) {
		return getBaseDao().update(model);
	}

	/**
	 * 根据主键更新对象,如果o属性不为空,则更新,为空则不更新
	 * 
	 * @param model
	 * @return
	 */
	public int update4Selective(T model) {
		return getBaseDao().update4Selective(model);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param o
	 * @return
	 */
	public int delete(Long id) {
		return getBaseDao().delete(id);
	}

	public int deleteLogical(Long id) {
		return getBaseDao().deleteLogical(id);
	}

	/**
	 * 根据对象信息删除 例如,传入 对象 User, id=1,name=a , 则删除 id=1,name=a的记录 如果传入对象 User,name=a, 则删除 name=a的记录
	 * 
	 * @param o
	 * @return
	 */
	public int delete(T model) {
		return getBaseDao().delete(model);
	}

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return getBaseDao().findAll();
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(String name, Object value) {
		return getBaseDao().findBy(name, value);
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(String name, Object value, boolean isLike) {
		return getBaseDao().findBy(name, value, isLike);
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param orderBy
	 *            排序的字段(默认正序)
	 * @param order
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(String name, Object value, String orderBy, Order order) {
		return getBaseDao().findBy(name, value, false, orderBy, order);
	}

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param orderBy
	 *            排序的字段(默认正序)
	 * @param order
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(String name, Object value, boolean isLike, String orderBy, Order order) {
		return getBaseDao().findBy(name, value, isLike, orderBy, order);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map) {
		return getBaseDao().findByMap(map);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map, boolean isLike) {
		return getBaseDao().findByMap(map, isLike);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param orderBy
	 *            排序的字段(默认正序)
	 * @param order
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map, String orderBy, Order order) {
		return getBaseDao().findByMap(map, orderBy, order);
	}

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param orderBy
	 *            排序的字段(默认正序)
	 * @param order
	 *            正序 or 倒序
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map, boolean isLike, String orderBy, Order order) {
		return getBaseDao().findByMap(map, isLike, orderBy, order);
	}

	/**
	 * 根据属性名和属性值查询单个对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的唯一对象,如果有多条,则返回null
	 */
	public T findUniqueBy(String name, Object value) {
		return getBaseDao().findUniqueBy(name, value);
	}

	/**
	 * 根据map 查询单个对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的唯一对象,如果有多条,则返回null
	 */
	public T findUniqueByMap(Map<String, Object> map) {
		return getBaseDao().findUniqueByMap(map);
	}

	/**
	 * 根据map 查询前几条对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param orderBy
	 *            排序的字段(默认正序)
	 * @param order
	 *            正序 or 倒序
	 * @param top
	 *            数量
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, String orderBy, Order order, int top) {
		return getBaseDao().findByMapTopN(map, isLike, orderBy, order, top);
	}

	/**
	 * 根据map 查询前几条对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param orderBy
	 *            排序的字段(默认正序)
	 * @param order
	 *            正序 or 倒序
	 * @param top
	 *            数量
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMapTopN(Map<String, Object> map, String orderBy, Order order, int top) {
		return getBaseDao().findByMapTopN(map, orderBy, order, top);
	}

	/**
	 * 根据map 查询前几条对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param top
	 *            数量
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMapTopN(Map<String, Object> map, int top) {
		return getBaseDao().findByMapTopN(map, top);
	}

	/**
	 * 根据map 查询前几条对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @param top
	 *            数量
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, int top) {
		return getBaseDao().findByMapTopN(map, isLike, top);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param pageBean
	 * @param map
	 */
	public void find4Page(Page<T> page, Map<String, Object> map) {
		getBaseDao().find4Page(page, map);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param page
	 * @param tblNameSuffix
	 *            表名后缀,用于历史备份表,如果yztf_order_payment_2016
	 * @param map
	 */
	public void find4Page(Page<T> page, String tblNameSuffix, Map<String, Object> map) {
		getBaseDao().find4Page(page, tblNameSuffix, map);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param pageBean
	 * @param map
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 */
	public void find4Page(Page<T> page, Map<String, Object> map, boolean isLike) {
		getBaseDao().find4Page(page, map, isLike);
	}

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param page
	 * @param tblNameSuffix
	 *            表名后缀,用于历史备份表,如果yztf_order_payment_2016
	 * @param map
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 */
	public void find4Page(Page<T> page, String tblNameSuffix, Map<String, Object> map, boolean isLike) {
		getBaseDao().find4Page(page, tblNameSuffix, map, isLike);
	}

	/**
	 * 根据ids,查询对象
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(String ids) {
		return getBaseDao().findByIds(ids);
	}
}
