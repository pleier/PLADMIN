package com.github.pleier.dao.impl;

import com.github.pleier.dao.BaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by pleiyang@outlook.com on 2017/3/4.
 */
@Repository("daoSupport")
public class BaseDaoImpl implements BaseDao{
    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 保存对象
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Object save(String str, Object obj) throws Exception {
        return sqlSessionTemplate.insert(str,obj);
    }

    /**
     * 更新对象
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Object update(String str, Object obj) throws Exception {
        return sqlSessionTemplate.update(str,obj);
    }

    /**
     * 删除对象
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String str, Object obj) throws Exception {
        return sqlSessionTemplate.delete(str,obj);
    }

    /**
     *通过对象查找对象
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Object findForObject(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectOne(str,obj);
    }

    /**
     *通过对象查找对象集合
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Object findForList(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectList(str,obj);
    }

    @Override
    public Object findForMap(String sql, Object obj, String key, String value) throws Exception {
        return sqlSessionTemplate.selectMap(sql,obj,key);
    }
}
