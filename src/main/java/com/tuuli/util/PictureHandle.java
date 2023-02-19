package com.tuuli.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author tuuli
 * @time Created in 2023/2/20 0:52
 * @description
 */
public class PictureHandle {
    /**
     * 保存图片的工具类
     *
     * @param file 需保存的图片
     * @return 唯一的图片名字
     * @throws IOException
     */
    public static String savePicture(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename(); // 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
        //为防止文件重名被覆盖，为每个文件都生成不同的名字
        UUID uuid = UUID.randomUUID();//生成一个唯一标识符
        String newFileName = uuid.toString().replaceAll("-", "") + suffixName;

        // 文件上传后存储的位置
        File savePos = new File("src/main/resources/static/questionsImages");
        // 获取存放位置的规范路径
        String realPath = savePos.getCanonicalPath();

        File dir = new File(realPath, newFileName);//创建文件流，对文件操作
        File filepath = new File(realPath);
        if (!filepath.exists()) {
            //路径不存在则创建
            filepath.mkdirs();
        }
        file.transferTo(dir);//把图片写进去

        return newFileName;
    }
}
