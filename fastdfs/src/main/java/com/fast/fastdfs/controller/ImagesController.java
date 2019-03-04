package com.fast.fastdfs.controller;

import com.alibaba.fastjson.JSONObject;
import com.fast.fastdfs.core.PublicArgs;
import com.fast.fastdfs.utils.FastdfsDFSClientLast;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片封装Controller
 * @author gaosheng
 */
@RestController
@RequestMapping("/gs/fastdfs")
public class ImagesController {
    @RequestMapping("/uploadImges.do")
    public JSONObject uploadImges(@RequestParam("file") MultipartFile file)throws Exception {
        JSONObject result = new JSONObject();
        if (file == null) {
            result.put("code", -200);
            result.put("data", "");
            result.put("message", "FAIL");
            return result;
        }
        try {
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //上传到图片服务器
            FastdfsDFSClientLast instance = FastdfsDFSClientLast.getInstance();
            instance.getFastdfsDFSClientLast(PublicArgs.TRACKERSERVER);
            String imgUrl = instance.uploadFile(file.getBytes(), extName);
            imgUrl = PublicArgs.IMGHOST+imgUrl;
            result.put("code", 200);
            result.put("data", imgUrl);
            result.put("message", "SUCCESS");
            return result;
        }catch(Exception e){
            e.printStackTrace();
            result.put("code", 200);
            result.put("data", "");
            result.put("message", "FAIL");
            return result;
        }
    }
}
