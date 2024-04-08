package org.knight.presentation.member;

import cn.hutool.core.util.ArrayUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.exception.BizException;
import org.knight.app.biz.exception.storage.ImageTypeIllegalException;
import org.knight.app.biz.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Tag(name = "StorageController", description = "STORAGE_CONTROLLER")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("upload")
    @Operation(description = "上传图片保存至本地C:\\home目录, 返回图片的storageId, 暂未完善")
    public List<String> upload(@RequestParam("file_data") MultipartFile[] files) throws IOException {
        if (ArrayUtil.isEmpty(files)) {
            throw new BizException("请选择要上传的图片");
        }
        List<String> storageIds = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            if (suffix == null || !suffix.matches("jpg|jpeg|png")) {
                throw new ImageTypeIllegalException("Bad Request: Image Type Illegal(Only .jpg .jpeg .png)");
            }
            String storageId = storageService.upload(file.getInputStream(), file.getSize(), file.getContentType(),
                    filename);
            storageIds.add(storageId);
        }
        return storageIds;
    }

    @GetMapping("/image/{storageId}")
    @ValidationStatusCode(code = "400")
    public String getImage(@PathVariable String storageId) {
        return storageService.getImage(storageId);
    }


}
