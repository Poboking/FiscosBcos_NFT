package org.knight.app.biz.storage;

import lombok.AllArgsConstructor;
import org.knight.app.biz.exception.BizException;
import org.knight.app.biz.exception.storage.ImageTypeIllegalException;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.dao.domain.StorageEntity;
import org.knight.infrastructure.dao.domain.SystemSettingEntity;
import org.knight.infrastructure.repository.impl.StorageRepositoryImpl;
import org.knight.infrastructure.repository.impl.SystemSettingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 16:23
 */
@Service
@AllArgsConstructor
@SuppressWarnings("all")
public class StorageService {
    @Autowired
    private StorageRepositoryImpl storageRepository;
    @Autowired
    private SystemSettingRepositoryImpl systemSettingRepository;

    /**
     * 上传文件至最新配置定义的文件Path下, 并赋值ID保存
     *
     * @param inputStream 文件输入流
     * @param fileSize    文件大小 - 字节
     * @param fileType    文件类型
     * @param fileName    文件名称
     * @return 文件ID
     */
    public String upload(InputStream inputStream, long fileSize, String fileType, String fileName) {
        if (!fileType.startsWith("image/")) {
            throw new ImageTypeIllegalException("只能上传图片");
        }
        String id = IdUtils.snowFlakeId();
        // TODO: 2024/3/18 临时写死存储路径
        String localStoragePath = "D://TEMP_3_18_LocalStoragePath";
        SystemSettingEntity systemSetting = systemSettingRepository.getLatestByLatestUpdateTime();
        if (systemSetting != null) {
            localStoragePath = systemSetting.getLocalStoragePath();
        }
        Path targetDirectory = Paths.get(localStoragePath);
        try {
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
            }
            Path targetFilePath = targetDirectory.resolve(id + fileName.substring(fileName.lastIndexOf(".")));
            Files.copy(inputStream, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + id, e);
        }finally {
            try {
                // 关闭输入流
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // 处理关闭输入流时的异常
                e.printStackTrace();
            }
        }
        StorageEntity storage = new StorageEntity();
        storage.setId(id);
        storage.setFileName(fileName);
        storage.setFileType(fileType);
        storage.setFileSize(fileSize);
        storage.setUploadTime(Timestamp.valueOf(LocalDateTime.now()));
        storageRepository.save(storage);
        return "/image/" + id + fileName.substring(fileName.lastIndexOf("."));
    }
}
