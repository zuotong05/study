package com.unisafecap.framework.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.unisafecap.framework.common.enums.Order;
import com.unisafecap.framework.common.utils.GenericsUtils;
import com.unisafecap.framework.common.utils.PageBean;
import com.unisafecap.framework.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	// 从spring注入原有的sqlSessionTemplate
	@Autowired
	private SqlSessionTemplate sqlSession;

	protected Class<T> entityClass;
	protected String sqlMapNamespace;

	public static final String POSTFIX_SELECT = ".select";
	public static final String POSTFIX_INSERT = ".insert";
	public static final String POSTFIX_INSERT_SELECTIVE = ".insert4Selective";
	public static final String POSTFIX_UPDATE = ".update";
	public static final String POSTFIX_UPDATE_SELECTIVE = ".update4Selective";
	public static final String POSTFIX_DELETE_PK = ".deleteByPrimaryKey";

	public static final String POSTFIX_DELETE = ".delete";
	public static final String POSTFIX_SELECT_MAP = ".selectByMap";
	public static final String POSTFIX_SELECT_COUNT = ".selectCount";
	public static final String POSTFIX_SELECT_PAGE = ".selectPage";
	public static final String POSTFIX_INSERT_BATCH = ".insertBatch";
	public static final String POSTFIX_SELECT_IDS = ".selectByIds";

	public String getSqlMapNamespace() {
		return sqlMapNamespace;
	}

	protected SqlSessionTemplate getSqlSession() {
	System.out.println(sqlSession.getConfiguration().getDatabaseId());	
		return sqlSession;
	}

	/**
	 * 在构造函数中将泛型T.class赋给entityClass. sqlNamespace 带包名
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		sqlMapNamespace = entityClass.getName();
	}

	@Override
	public T get(Serializable id) {
		return get(id, null);

	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id, String tableNameSuffix) {
		if (id == null)
			return null;// 如果id为空,不再查询
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			if (StringUtils.isNotBlank(tableNameSuffix)) {
				map.put("tableNameSuffix", tableNameSuffix);
			}
			return (T) sqlSession.selectOne(sqlMapNamespace + POSTFIX_SELECT, map);
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public int insert(T o) {
		return sqlSession.insert(sqlMapNamespace + POSTFIX_INSERT, o);
	}

	@Override
	public int insert4Selective(T o) {
		return sqlSession.insert(sqlMapNamespace + POSTFIX_INSERT_SELECTIVE, o);
	}

	@Override
	public void insertBatch(List<T> list) {
		if (list == null || list.size() == 0)
			return;
		int total = list.size();
		int once = 500;
		for (int i = 1; i <= (total / once) + 1; i++) {
			List<T> temp = list.subList((i - 1) * once, i * once > total ? total : i * once);
			sqlSession.insert(sqlMapNamespace + POSTFIX_INSERT_BATCH, temp);
		}
	}

	@Override
	public int update(T o) {
		return sqlSession.update(sqlMapNamespace + POSTFIX_UPDATE, o);
	}

	@Override
	public int update4Selective(T o) {
		return sqlSession.update(sqlMapNamespace + POSTFIX_UPDATE_SELECTIVE, o);
	}

	@Override
	public int delete(Serializable id) {
		return delete(id, null);
	}

	@Override
	public int delete(Serializable id, String tableNameSuffix) {
		if (id == null)
			return 0;// 如果id为空,不再查询
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			if (StringUtils.isNotBlank(tableNameSuffix)) {
				map.put("tableNameSuffix", tableNameSuffix);
			}
			return sqlSession.delete(sqlMapNamespace + POSTFIX_DELETE_PK, map);
		}
		catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int delete(T o) {
		return sqlSession.delete(sqlMapNamespace + POSTFIX_DELETE, o);

	}

	@Override
	public List<T> findAll() {
		return findAll(null);
	}

	@Override
	public List<T> findAll(String tableNameSuffix) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(tableNameSuffix)) {
			map.put("tableNameSuffix", tableNameSuffix);
		}
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT);
	}

	@Override
	public List<T> findBy(String name, Object value) {
		return findBy(name, value, null, false, null, null);
	}

	@Override
	public List<T> findBy(String name, Object value, String tableNameSuffix) {
		return findBy(name, value, tableNameSuffix, false, null, null);
	}

	@Override
	public List<T> findBy(String name, Object value, String tableNameSuffix, boolean isLike, String orderBy, Order order) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(name, value);
		if (StringUtils.isNotBlank(tableNameSuffix)) {
			map.put("tableNameSuffix", tableNameSuffix);
		}
		return findByMap(map, isLike, orderBy, order);
	}

	@Override
	public List<T> findBy(String name, Object value, boolean isLike, String orderBy, Order order) {
		return findBy(name, value, null, isLike, orderBy, order);
	}

	@Override
	public List<T> findBy(String name, Object value, String tableNameSuffix, boolean isLike) {
		return findBy(name, value, tableNameSuffix, isLike, null, null);
	}

	@Override
	public List<T> findBy(String name, Object value, boolean isLike) {
		return findBy(name, value, null, isLike, null, null);
	}

	@Override
	public List<T> findBy(String name, Object value, String tableNameSuffix, String orderBy, Order order) {
		return findBy(name, value, tableNameSuffix, false, orderBy, order);
	}

	@Override
	public List<T> findBy(String name, Object value, String orderBy, Order order) {
		return findBy(name, value, null, false, orderBy, order);
	}

	@Override
	public T findUniqueBy(String name, Object value) {
		return findUniqueBy(name, value, null);
	}

	@Override
	public T findUniqueBy(String name, Object value, String tableNameSuffix) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(name, value);
		if (StringUtils.isNotBlank(tableNameSuffix)) {
			map.put("tableNameSuffix", tableNameSuffix);
		}
		return findUniqueByMap(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findUniqueByMap(Map<String, Object> map) {
		try {
			map.put("findBy", "true");
			return (T) sqlSession.selectOne(sqlMapNamespace + POSTFIX_SELECT_MAP, map);
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, String orderBy, Order order, int top) {
		if (map == null)
			map = new HashMap<String, Object>();
		if (isLike) {
			map.put("findByLike", "true");
		} else {
			map.put("findBy", "true");
		}
		map.put("startRow", 0);
		map.put("pageSize", top);
		if (StringUtils.isNotBlank(orderBy)) {
			if (orderBy.indexOf("'") != -1)
				orderBy.replace("'", "");
			map.put("sortName", orderBy);
			map.put("sortBy", order.toString());
		}
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_PAGE, map);
	}

	@Override
	public List<T> findByMapTopN(Map<String, Object> map, int top) {
		return findByMapTopN(map, false, null, null, top);
	}

	@Override
	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, int top) {
		return findByMapTopN(map, isLike, null, null, top);
	}

	@Override
	public List<T> findByMapTopN(Map<String, Object> map, String orderBy, Order order, int top) {
		return findByMapTopN(map, false, orderBy, order, top);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map, boolean isLike, String orderBy, Order order) {
		if (map == null)
			return findAll();
		if (isLike) {
			map.put("findByLike", "true");
		} else {
			map.put("findBy", "true");
		}
		if (StringUtils.isNotBlank(orderBy)) {
			// 需要对sortName做过滤,防止sql注入
			if (orderBy.indexOf("'") != -1)
				orderBy.replace("'", "");
			map.put("sortName", orderBy);
			map.put("sortBy", order.toString());
		}
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_MAP, map);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map, boolean isLike) {
		return findByMap(map, isLike, null, null);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map, String orderBy, Order order) {
		return findByMap(map, false, orderBy, order);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map) {
		return findByMap(map, false, null, null);
	}

	@Override
	public void find4Page(PageBean<T> page, Map<String, Object> map) {
		find4Page(page, map, false);
	}

	@Override
	public void find4Page(PageBean<T> page, Map<String, Object> map, boolean isLike) {
		if (map == null)
			map = new HashMap<String, Object>();
		if (isLike) {
			map.put("findByLike", "true");
		} else {
			map.put("findBy", "true");
		}
		Integer totalRows = sqlSession.selectOne(sqlMapNamespace + POSTFIX_SELECT_COUNT, map);
		page.setTotalCount(totalRows);
		if (totalRows != null && totalRows > 0) {
			map.put("startRow", page.getStartRow());
			map.put("pageSize", page.getPageSize());
			if (StringUtils.isNotBlank(page.getOrderBy())) {
				if (page.getOrderBy().indexOf("'") != -1)
					page.getOrderBy().replace("'", "");
				map.put("sortName", page.getOrderBy());
				map.put("sortBy", page.getOrder());
			}
			List<T> list = sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_PAGE, map);
			page.setResult(list);
		}
	}

	@Override
	public List<T> findByIds(String ids) {
		return findByIds(ids, null);
	}

	@Override
	public List<T> findByIds(String ids, String tableNameSuffix) {
		ids = checkSqlParam(ids);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idlist", ids);
			if (StringUtils.isNotBlank(tableNameSuffix)) {
				map.put("tableNameSuffix", tableNameSuffix);
			}
			return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_IDS, map);
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 参数校验, 防止sql注入
	 * 
	 * @param param
	 * @return
	 */
	private String checkSqlParam(String param) {
		if (StringUtils.isBlank(param))
			return "";
		if (param.indexOf("'") != -1) {
			param = param.replace("'", "");
		}
		return param;
	}

}
