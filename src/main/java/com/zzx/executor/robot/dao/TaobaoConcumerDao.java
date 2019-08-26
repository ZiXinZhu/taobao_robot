package com.zzx.executor.robot.dao;

import com.zzx.executor.robot.entity.TaoBaoConsumerPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaobaoConcumerDao {

    /**
     * 通过查找大于对应id的所有买家
     * @return
     */
    @Select("select * from taobao_consumer where  is_failure=0 order by id desc")
    List<TaoBaoConsumerPO> findAll();

}
