package com.muban.demo.mapper;


import com.muban.demo.model.UserInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserInfoMapper {
   @SelectProvider(type = UserInfoDaoBuilder.class, method = "getUserInfos")
   List<UserInfo> userInfos(@Param("phone") String phone, @Param("limit")int limit,@Param("offset")int offset);

   @SelectProvider(type = UserInfoDaoBuilder.class, method = "getUserInfosTotal")
  int userInfoCount(@Param("phone") String phone);

   class UserInfoDaoBuilder {
      public String getUserInfos(String phone, int limit,int offset) {
         String sql = new SQL() {
            {
               SELECT("*");
               FROM("user_info");
               if (StringUtils.isNotBlank(phone)) {
                  WHERE("phone like concat('%',#{phone},'%')");
               }
               ORDER_BY("id desc");
            }
         }.toString();
         if (offset <= 0) {
            sql = sql + " limit " + limit;
         } else {
            sql = sql + " limit " + offset + ", " + limit;
         }
         return sql;
      }


      public String getUserInfosTotal(@Param("phone") String phone, @Param("limit")int limit,@Param("offset")int offset) {
         String sql = new SQL() {
            {
               SELECT("count(1)");
               FROM("user_info");
               if (StringUtils.isNotBlank(phone)) {
                  WHERE("phone like concat('%',#{phone},'%')");
               }
               ORDER_BY("id desc");
            }
         }.toString();
         return sql;
      }
   }
}
