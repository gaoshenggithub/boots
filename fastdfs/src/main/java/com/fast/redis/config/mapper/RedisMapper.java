package com.fast.redis.config.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface RedisMapper {
    @Select("SELECT id,loginName,name,phone,passprotNo,gender,mail FROM LxUser WHERE id =#{id}")
    Map<String,Object> getUserData(Map<String,Object> map);

    @Update("UPDATE LxUser SET loginName=#{loginName} WHERE id=#{id}")
     int updateUserData(Map<String,Object> map);
    /**
     * ,name=#{name}," +
     "phone=#{phone},passproNo=#{passproNo},gender=#{gender},mail=#{mail}
     */

    @Delete("UPDATE LxUser SET delFlag = #{delFlag} WHERE id=#{id}")
    int deleteUserData(Map<String,Object> map);

    @Select("SELECT * FROM LxUser")
    List<Map<String,Object>> finUsersData();
}
