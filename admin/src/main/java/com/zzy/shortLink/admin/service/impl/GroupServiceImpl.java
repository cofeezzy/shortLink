package com.zzy.shortLink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.shortLink.admin.common.biz.user.UserContext;
import com.zzy.shortLink.admin.dao.entity.GroupDO;
import com.zzy.shortLink.admin.dao.mapper.GroupMapper;
import com.zzy.shortLink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.zzy.shortLink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.zzy.shortLink.admin.service.GroupService;
import com.zzy.shortLink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .name(groupName)
                .build();

        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        //TODO 获取用户名
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0)
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> list = baseMapper.selectList(queryWrapper);

        return BeanUtil.copyToList(list, ShortLinkGroupRespDTO.class);
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO reqDTO) {
        LambdaUpdateWrapper<GroupDO> eq = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, reqDTO.getGid())
                .eq(GroupDO::getDelFlag, 0);

        GroupDO groupDO = new GroupDO();
        groupDO.setName(reqDTO.getName());
        baseMapper.update(groupDO, eq);
    }

    private boolean hasGid(String gid){

        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class).eq(GroupDO::getGid, gid)
                //TODO 设置用户名
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO hasGroup = baseMapper.selectOne(eq);
        return hasGroup == null;
    }

}