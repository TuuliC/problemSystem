package com.tuuli.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;

/**
 * @author tuuli
 * @time Created in 2023/2/22 23:22
 * @description 保存图片到阿里云OSS
 */
@Component
public class UploadUtil {
    //阿里域名
    private static String aliDomain;
    //地域节点
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    //bucket
    private static String bucket;
    //文件夹名
    private static String folder;

    @Value("${aliOSS.aliDomainValue}")
    private String aliDomainValue;
    @Value("${aliOSS.endpointValue}")
    private String endpointValue;
    @Value("${aliOSS.accessKeyIdValue}")
    private String accessKeyIdValue;
    @Value("${aliOSS.accessKeySecretValue}")
    private String accessKeySecretValue;
    @Value("${aliOSS.bucketValue}")
    private String bucketValue;
    @Value("${aliOSS.folderValue}")
    private String folderValue;

    @PostConstruct
    public void init(){
        aliDomain = aliDomainValue;
        endpoint = endpointValue;
        accessKeyId = accessKeyIdValue;
        accessKeySecret = accessKeySecretValue;
        bucket = bucketValue;
        folder = folderValue;
    }

    /**
     * 保存图片到阿里云OSS
     * @param file 需要保存的图片
     * @return 图片的url
     * @throws IOException
     */
    public static String uploadImage(MultipartFile file) throws IOException {
        //生成文件名
        String fileName = file.getOriginalFilename(); // 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
        UUID uuid = UUID.randomUUID();//生成一个唯一标识符
        String newFileName = folder + uuid.toString().replaceAll("-", "") + suffixName;
        //oss客户端对象
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        oss.putObject(bucket, newFileName, file.getInputStream());
        return aliDomain + "/" + newFileName;
    }
}
