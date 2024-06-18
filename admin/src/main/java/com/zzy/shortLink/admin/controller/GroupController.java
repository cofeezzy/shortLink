package com.zzy.shortLink.admin.controller;

import com.zzy.shortLink.admin.common.convention.result.Result;
import com.zzy.shortLink.admin.common.convention.result.Results;
import com.zzy.shortLink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.zzy.shortLink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.zzy.shortLink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.zzy.shortLink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO reqDTO){
        groupService.saveGroup(reqDTO.getName());
        return Results.success();
    }

    /**
     * c查询短链接分组集合
     */
    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组名
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> update(@RequestBody ShortLinkGroupUpdateReqDTO reqDTO){
        groupService.updateGroup(reqDTO);
        return Results.success();
    }
}