package com.muban.demo.controller;

import com.muban.demo.mapper.UserInfoMapper;
import com.muban.demo.model.UserInfo;
import com.muban.demo.util.JSONResponse;
import com.muban.demo.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;
    //精品内容商品展示
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getFeaturedContentList(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
//        String postTitle = request.getParameter("postTitle");
//        String author = request.getParameter("author");
        int limit = RequestUtil.getIntPara(request, "limit", 5);
        int offset = RequestUtil.getIntPara(request, "offset", 0);
//        String domainId = request.getParameter("domainId");
        //文章地址
//        String permalink = request.getParameter("permalink");
//        List<FeaturedContent> contents = featuredContentService.getFeaturedContentList(postTitle, author,domainId,permalink, limit, offset);
        String phone = request.getParameter("phone");
           List<UserInfo> userInfos=userInfoMapper.userInfos(phone,limit,offset);
            data.put("contents", userInfos);
        if (offset == 0) {
            int totalCount = userInfoMapper.userInfoCount(phone);
            data.put("totalCount", totalCount);
        }

        JSONResponse response=JSONResponse.newResponse().addData(data);
        return response;
    }
}
