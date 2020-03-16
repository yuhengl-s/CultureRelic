package com.edu.zju.culture.mbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.zju.culture.common.*;
import com.edu.zju.culture.mbg.entity.Permission;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.IPermissionService;
import com.edu.zju.culture.vo.PermissionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y4oung
 * @date 2020/3/15 12:24 PM
 * @description
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(PermissionVo permissionVo) {
        //查询所有菜单
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constant.TYPE_MNEU);
        queryWrapper.eq("available", Constant.AVAILABLE_TRUE);
        //设置只能查到菜单
        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if (user.getType() == Constant.USER_TYPE_SUPER) {
            list = permissionService.list(queryWrapper);
        } else {
            //根据用户ID+角色+权限去查询 (待完成）
            list = permissionService.list(queryWrapper);
        }

        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission p : list) {
            Integer id = p.getId();
            Integer pid = p.getPid();
            String title = p.getTitle();
            String icon = p.getIcon();
            String href = p.getHref();
            Integer type = p.getType().equals("menu") ? 0 : 1;
            Boolean spread = p.getOpen() == Constant.OPEN_TRUE ? true : false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread, type));
        }
        //构造层级关系
        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);
        return list2;
    }

}
