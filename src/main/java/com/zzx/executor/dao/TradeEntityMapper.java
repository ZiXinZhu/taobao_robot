package com.zzx.executor.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TradeEntityMapper {


    @Insert("Insert INTO alipay_cookies  (cookie,url_name,uid) values (#{cookie},#{urlName},#{uid});")
    int insertCookie(@Param("cookie") String cookie,
                   @Param("urlName") String urlName,
                   @Param("uid") String uid);
    @Delete("DELETE FROM alipay_cookies where uid=#{uid}")
    int deleteCookie(@Param("uid") String uid);


}