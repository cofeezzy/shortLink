package com.zzy.shortLink.admin.controller;

import com.zzy.shortLink.admin.common.convention.result.Result;
import com.zzy.shortLink.admin.common.convention.result.Results;
import com.zzy.shortLink.admin.dto.req.RecycleBinSaveDTO;
import com.zzy.shortLink.admin.remote.dto.ShortLinkRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  回收站管理控制层
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    /**
     * 后续需要重构成Spring cloud feign
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService(){
    };

    /**
     * 保存回收站
     * @param recycleBinSaveDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveDTO recycleBinSaveDTO){
        shortLinkRemoteService.saveRecycleBin(recycleBinSaveDTO);
        return Results.success();
    }
}
