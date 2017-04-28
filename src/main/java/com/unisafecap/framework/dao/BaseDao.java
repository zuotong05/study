package com.unisafecap.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.unisafecap.framework.common.enums.Order;

/**
 * <P>
 * DAO操作的对象类型
 * </P>
 * 
 * @author 左通
 * @since 2017年1月13日
 * @version V1.0
 */
public interface BaseDao<T> {

	/**
	 * 按id获取对象
	 * 
	 * @param id
	 * @return T 对象
	 */
	public T get(Serializable id);

	/**
	 * 插入单个对象
	 * 
	 * @param model
	 *            对象
	 * @return int
	 */
	public int insert(T model);

	/**
	 * 插入单个对象,如果o属性不为空,则插入，为空则不插入，可以做数据库默认值
	 * 
	 * @param model
	 *            对象
	 * @return int
	 */
	public int insert4Selective(T model);

	/**
	 * 根据主键更新对象
	 * 
	 * @param model
	 *            对象
	 * @return int
	 */
	public int update(T model);

	/**
	 * 根据主键更新对象,如果o属性不为空,则更新,为空则不更新
	 * 
	 * @param model
	 *            对象
	 * @return int
	 */
	public int update4Selective(T model);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return int
	 */
	public int delete(Serializable id);

	/**
	 * 根据对象信息删除 例如,传入 对象 User, id=1,name=a , 则删除 id=1,name=a的记录 如果传入对象 User,name=a, 则删除 name=a的记录
	 * 
	 * @param model
	 *            对象
	 * @return int
	 */
	public int delete(T model);

	/**
	 * 查询所有记录
	 * 
	 * @return List 对象列表
	 */
	public List<T> findAll();

	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return List 符合条件的对象列表
	 */
	public List<T> findBy(String name, Object value);

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
	public List<T> findBy(String name, Object value, boolean isLike);

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
	public List<T> findBy(String name, Object value, String orderBy, Order order);

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
	public List<T> findBy(String name, Object value, boolean isLike, String orderBy, Order order);

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map);

	/**
	 * 根据map 查询对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMap(Map<String, Object> map, boolean isLike);

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
	public List<T> findByMap(Map<String, Object> map, String orderBy, Order order);

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
	public List<T> findByMap(Map<String, Object> map, boolean isLike, String orderBy, Order order);

	/**
	 * 根据属性名和属性值查询单个对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的唯一对象,如果有多条,则返回null
	 */
	public T findUniqueBy(String name, Object value);

	/**
	 * 根据map 查询单个对象
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 符合条件的唯一对象,如果有多条,则返回null
	 */
	public T findUniqueByMap(Map<String, Object> map);

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
	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike,  String orderBy, Order order, int top);

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
	public List<T> findByMapTopN(Map<String, Object> map, String orderBy, Order order, int top);

	/**
	 * 根据map 查询前几条对象
	 * 
	 * @param map
	 *            key=属性名,value=属性值
	 * @param top
	 *            数量
	 * @return 符合条件的对象列表
	 */
	public List<T> findByMapTopN(Map<String, Object> map, int top);

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
	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, int top);

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param page
	 * @param map
	 *            key=属性名,value=属性值
	 */
	public void find4Page(Page<T> page, Map<String, Object> map);

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param page
	 * @param tblNameSuffix
	 *            表名后缀,用于历史备份表,如果yztf_order_payment_2016
	 * @param map
	 */
	public void find4Page(Page<T> page, String tblNameSuffix, Map<String, Object> map);

	/**
	 * 根据查询条件和分页信息,查询对象
	 * 
	 * @param page
	 * @param map
	 * @param isLike
	 *            是否模糊匹配(只有属性类型为字符串,才生效)
	 */
	public void find4Page(Page<T> page, Map<String, Object> map, boolean isLike);

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
	public void find4Page(Page<T> page, String tblNameSuffix, Map<String, Object> map, boolean isLike);

	/**
	 * 根据ids,查询对象
	 * 
	 * @param ids
	 *            ids字符串
	 * @return
	 */
	public List<T> findByIds(String ids);

	/**
	 * 批量插入
	 * 
	 * @param list
	 *            对象列表
	 */
	public void insertBatch(List<T> list);

}
