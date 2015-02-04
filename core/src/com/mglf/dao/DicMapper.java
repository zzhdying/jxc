package com.mglf.dao;

import java.util.List;
import java.util.Map;

import com.mglf.entity.Dic;

public interface DicMapper {
    public int deleteByPrimaryKey(String id);

    public int insert(Dic record);

    public List<Dic> selectBy(Map<String,Object> map);

    public int updateByPrimaryKeySelective(Dic record);

    public int updateByPrimaryKey(Dic record);
}