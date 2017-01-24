package com.xiaoma.framework.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.xiaoma.framework.constant.Constants.SortBy;
import com.xiaoma.framework.dao.BaseDao;
import com.xiaoma.framework.dao.Page;
import com.xiaoma.framework.model.StatusEnabled;
import com.xiaoma.framework.utils.GenericsUtils;

//@Repository("baseDaoImpl")
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
		if (id == null)
			return null;// 如果id为空,不再查询
		return sqlSession.selectOne(sqlMapNamespace + POSTFIX_SELECT, id);
	}

	@Override
	public int insert(T o) {
		return sqlSession.insert(sqlMapNamespace + POSTFIX_INSERT, o);
	}

	@Override
	public int insert4Selective(T o) {
		return sqlSession.insert(sqlMapNamespace + POSTFIX_INSERT_SELECTIVE, o);
	}

	public void insertBatch(List<T> list) {
		if (list == null || list.size() == 0)
			return;
		int total = list.size();// 总记录数
		int once = 1000;// 1000条拼接一次
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
		return sqlSession.delete(sqlMapNamespace + POSTFIX_DELETE_PK, id);
	}

	@Override
	public int delete(T o) {
		if (o instanceof StatusEnabled) {
			StatusEnabled entity = (StatusEnabled) o;
			entity.setEnabled(StatusEnabled.STATUS_INACTIVE);
			return sqlSession.update(sqlMapNamespace + POSTFIX_UPDATE_SELECTIVE, entity);
		} else {
			return sqlSession.delete(sqlMapNamespace + POSTFIX_DELETE, o);
		}

	}

	@Override
	public List<T> findAll() {
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT);
	}

	@Override
	public List<T> findBy(String name, Object value) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(name, value);
		return findByMap(map);
	}

	@Override
	public List<T> findBy(String name, Object value, boolean isLike, String sortName, SortBy sortBy) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(name, value);
		return findByMap(map, isLike, sortName, sortBy);
	}

	@Override
	public List<T> findBy(String name, Object value, boolean isLike) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(name, value);
		return findByMap(map, isLike, null, null);
	}

	@Override
	public List<T> findBy(String name, Object value, String sortName, SortBy sortBy) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(name, value);
		return findByMap(map, false, sortName, sortBy);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findUniqueBy(String name, Object value) {
		Assert.hasText(name);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put(name, value);
			map.put("findBy", "True");
			return (T) sqlSession.selectOne(sqlMapNamespace + POSTFIX_SELECT_MAP, map);
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<T> findByMap(Map<String, Object> map) {
		if (map == null)
			return findAll();
		return findByMap(map, false, null, null);
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

	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, String sortName, SortBy sortBy, int top) {
		if (map == null)
			map = new HashMap<String, Object>();
		if (isLike) {
			map.put("findByLike", "true");
		} else {
			map.put("findBy", "true");
		}

		map.put("startRow", 0);
		map.put("pageSize", top);
		if (StringUtils.isNotBlank(sortName)) {
			if (sortName.indexOf("'") != -1)
				sortName.replace("'", "");
			map.put("sortName", sortName);
			map.put("sortBy", sortBy.toString());
		}
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_PAGE, map);
	}

	public List<T> findByMapTopN(Map<String, Object> map, int top) {
		return findByMapTopN(map, false, null, null, top);
	}

	public List<T> findByMapTopN(Map<String, Object> map, boolean isLike, int top) {
		return findByMapTopN(map, isLike, null, null, top);
	}

	public List<T> findByMapTopN(Map<String, Object> map, String sortName, SortBy sortBy, int top) {
		return findByMapTopN(map, false, sortName, sortBy, top);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map, boolean isLike, String sortName, SortBy sortBy) {
		if (map == null)
			return findAll();
		if (isLike) {
			map.put("findByLike", "true");
		} else {
			map.put("findBy", "true");
		}
		if (StringUtils.isNotBlank(sortName)) {
			// 需要对sortName做过滤,防止sql注入
			if (sortName.indexOf("'") != -1)
				sortName.replace("'", "");
			map.put("sortName", sortName);
			map.put("sortBy", sortBy.toString());
		}
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_MAP, map);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map, boolean isLike) {
		return findByMap(map, isLike, null, null);
	}

	@Override
	public List<T> findByMap(Map<String, Object> map, String sortName, SortBy sortBy) {
		return findByMap(map, false, sortName, sortBy);
	}

	@Override
	public void find4Page(Page<T> page, Map<String, Object> map) {
		find4Page(page, map, false);
	}

	@Override
	public void find4Page(Page<T> page, Map<String, Object> map, boolean isLike) {
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
		ids = checkSqlParam(ids);
		return sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_IDS, ids);
	}

	@Override
	public void find4Page(Page<T> page, String tblNameSuffix, Map<String, Object> map) {
		find4Page(page, tblNameSuffix, map, false);

	}

	@Override
	public void find4Page(Page<T> page, String tblNameSuffix, Map<String, Object> map, boolean isLike) {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put("tblNameSuffix", tblNameSuffix);
		find4Page(page, map, isLike);

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
