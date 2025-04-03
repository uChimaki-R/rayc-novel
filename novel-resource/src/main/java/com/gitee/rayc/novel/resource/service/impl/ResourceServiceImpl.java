package com.gitee.rayc.novel.resource.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gitee.rayc.novel.core.domain.constant.SystemConfigConsts;
import com.gitee.rayc.novel.core.domain.enums.RespCodeEnum;
import com.gitee.rayc.novel.core.domain.exception.BusinessException;
import com.gitee.rayc.novel.core.domain.resp.RestResp;
import com.gitee.rayc.novel.resource.entity.ImgVerifyCode;
import com.gitee.rayc.novel.resource.cache.VerifyCodeManager;
import com.gitee.rayc.novel.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 资源（图片/视频/文档）相关服务实现类
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private final VerifyCodeManager verifyCodeManager;

    @Value("${novel.file.upload.path}")
    private String fileUploadPath;

    @Override
    public RestResp<ImgVerifyCode> getImgVerifyCode() throws IOException {
        String sessionId = IdWorker.get32UUID();
        return RestResp.ok(ImgVerifyCode.builder()
            .sessionId(sessionId)
            .img(verifyCodeManager.genImgVerifyCode(sessionId))
            .build());
    }

    @SneakyThrows
    @Override
    public RestResp<String> uploadImage(MultipartFile file) {
        LocalDateTime now = LocalDateTime.now();
        String savePath =
            SystemConfigConsts.IMAGE_UPLOAD_DIRECTORY
                + now.format(DateTimeFormatter.ofPattern("yyyy")) + File.separator
                + now.format(DateTimeFormatter.ofPattern("MM")) + File.separator
                + now.format(DateTimeFormatter.ofPattern("dd"));
        String oriName = file.getOriginalFilename();
        assert oriName != null;
        String saveFileName = IdWorker.get32UUID() + oriName.substring(oriName.lastIndexOf("."));
        File saveFile = new File(fileUploadPath + savePath, saveFileName);
        if (!saveFile.getParentFile().exists()) {
            boolean isSuccess = saveFile.getParentFile().mkdirs();
            if (!isSuccess) {
                throw new BusinessException(RespCodeEnum.USER_UPLOAD_FILE_ERROR);
            }
        }
        file.transferTo(saveFile);
        if (Objects.isNull(ImageIO.read(saveFile))) {
            // 上传的文件不是图片
            Files.delete(saveFile.toPath());
            throw new BusinessException(RespCodeEnum.USER_UPLOAD_FILE_TYPE_NOT_MATCH);
        }
        return RestResp.ok(savePath + File.separator + saveFileName);
    }

}
