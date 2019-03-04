package com.fast.fastdfs;

import com.fast.fastdfs.core.PublicArgs;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;
@Component
public class MultipartConfig {
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(PublicArgs.BYTE1024*PublicArgs.SIZE+"KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(PublicArgs.BYTE1024*PublicArgs.MAX_SIZE+"KB");
        return factory.createMultipartConfig();
    }
}
