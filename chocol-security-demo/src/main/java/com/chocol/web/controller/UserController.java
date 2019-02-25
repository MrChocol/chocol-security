package com.chocol.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.chocol.dto.User;
import com.chocol.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 5,size = 20,sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable);
        return CollectionUtil.newArrayList(new User(), new User(), new User());
    }
}
