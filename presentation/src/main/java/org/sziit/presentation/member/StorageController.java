package org.sziit.presentation.member;

import cn.hutool.core.util.ArrayUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sziit.app.biz.exception.BizException;
import org.sziit.app.biz.storage.StorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/12 22:07
 */
//@CheckUserLogin
@Log4j2
@RestController
@RequestMapping("/storage/")
@AllArgsConstructor
@Tag(name = "StorageController", description = "STORAGE_CONTROLLER")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    @Operation(description = "上传图片保存至本地C:\\home目录, 返回图片的storageId, 暂未完善")
    public List<String> upload(@RequestParam("file_data") MultipartFile[] files) throws IOException {
        if (ArrayUtil.isEmpty(files)) {
            throw new BizException("请选择要上传的图片");
        }
        List<String> storageIds = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String storageId = storageService.upload(file.getInputStream(), file.getSize(), file.getContentType(),
                    filename);
            storageIds.add(storageId);
        }
        return storageIds;
    }


}
