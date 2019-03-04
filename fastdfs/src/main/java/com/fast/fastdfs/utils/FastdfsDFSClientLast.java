package com.fast.fastdfs.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * @author gs
 * <p>线程安全的方式(单线程)<p/>
 * 图片服务封装
 */
public class FastdfsDFSClientLast {
    //私有化
    private static FastdfsDFSClientLast fastClient;
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    //私有化
    private FastdfsDFSClientLast() {
    }

    //使用单例模式提供单线程
    public static FastdfsDFSClientLast getInstance() {
        if (fastClient == null) {
            synchronized (FastdfsDFSClientLast.class) {
                if (fastClient == null) {
                    //初始化
                    fastClient = new FastdfsDFSClientLast();
                }
            }
        }
        return fastClient;
    }

    /**
     * 初始化客户端和服务端加载资源文件
     */
    public void getFastdfsDFSClientLast(String conf) throws Exception {
        //判断是否有此字符
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileName 文件全路径
     * @param extName  文件扩展名，不包含（.）
     * @param metas    文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }


    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    public static void main(String[] args) {
        FastdfsDFSClientLast instance = FastdfsDFSClientLast.getInstance();
    }
}