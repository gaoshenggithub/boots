#搭建图片服务器
下载文件
[https://github.com/happyfish100]
fastdfs/libfastcommon/fastdfs-nginx-module/fastdfs-client-java(java代码)

http://nginx.org/download/=====>1.12.0版本

yum -y gcc-c++ gcc   =====>(编译make.sh) 
yum -y pcre pcre-devel  =====>()
yum -y install zlib zlib-devel
=================安装libfastcommon-master======================
unzip libfastcommon-master.zip ===>libfastcommon-master ====>进入目录

./make.sh
./make.sh install

ln -s /usr/lib64/libfastcommon.so /usr/local/lib/libfastcommon.so
ln -s /usr/lib64/libfastcommon.so /usr/lib/libfastcommon.so
ln -s /usr/lib64/libfdfsclient.so /usr/local/lib/libfdfsclient.so
ln -s /usr/lib64/libfdfsclient.so /usr/lib/libfdfsclient.so
==================安装结束libfastcommon-master=====================

=================安装fastdfs=====================
unzip fastdfs-5.11.zip ====>进入目录
./make.sh
./make.sh install
==================安装结束=========================

=================其他操作============
cd /etc/fdfs

##1.启动tracker服务
====复制文件
cp client.conf.sample client.conf
cp storage.conf.sample storage.conf
cp storage_ids.conf.sample storage_ids.conf
cp tracker.conf.sample tracker.conf
====不要删除***.sample


mkdir -p /home/gs/fastdfs/fastdfs_tracker ======>路径1
根据自己的路径定义
多目录使用mkdir -p 多级目录
一级目录mkdir 目录
cd /etc/fdfs
vim tracker.conf
修改

disabled=false
port=22122
base_path=/home/gs/fastdfs/fastdfs_tracker====>定义为路径1(根据自己的路径而定义)
http.server_port=8080 ====>可以修改可以不修改但是不要占用其他端口

启动tracker服务
Centos7以下的版本
service fdfs_trackerd start

Centos7以上的版本
systemctl start fdfs_trackerd

之后可以看见
Starting fdfs_trackerd    =====不一定有=====

进入路径1
cd /home/gs/fastdfs/fastdfs_tracker/  
ls 下面会有两个目录data  logs
cd ..
pwd ====>/home/gs/fastdfs(根据自己的实际路径,为了方便管理)
mkdir fastdfs_storage  fastdfs_storage_data


##2.启动storage服务
vim /etc/fdfs/storage.conf
修改
disabled=false
group_name=group1 ===>单点图片服务器不要修改,集群需要修改
#设置storage的端口号，默认是23000，同一个组的storage端口号必须一致(集群)
port=23000 ===>不要修改
 #设置storage数据文件和日志目录 
base_path=/home/gs/fastdfs/fastdfs_storage/
#存储路径个数，需要和store_path个数匹配===>base_path路径
store_path_count=1 
#图片存放的路径
base_path0=/home/gs/fastdfs/fastdfs_storage_data 
tracker_server=[ip=>线上服务器使用公网,本地使用本地ip]:[port默认22122]
#设置 端口号 
http.server_port=8888 

#建立软连接
ln -s /usr/bin/fdfs_storaged /usr/local/bin
[centos7以下]
service fdfs_storaged start
[centos7以上]
systemctl start fdfs_storaged

#查看客户端连接状态
 /usr/bin/fdfs_monitor /etc/fdfs/storage.conf


#修改客户端配置文件
vim /etc/fdfs/client.conf
base_path=/usr/muyou/dev/fastdfs/fastdfs_tracker #tracker服务器文件路径
tracker_server=192.168.150.132:22122 #tracker服务器IP地址和端口号
http.tracker_server_port=8080 # tracker 服务器的 http端口号，必须和tracker的设置对应起
#定义为路径1==>也就是路径1的端口


#测试图片
/usr/bin/fdfs_upload_file  /etc/fdfs/client.conf 图片路径
事列:group1/M00/00/00/XXXXX.
图片最终的路径在fastdfs_storage_data的data目录下有自己上传的图片

安装nginx
yum -y install pcre pcre-devel  
yum -y install zlib zlib-devel  
yum -y install openssl openssl-devel

解压
tar -zxvf nginx-1.12.0.tar.gz
unzip fastdfs-nginx-module-master.zip
此命令需要在nginx目录下执行                            #解压后fastdfs-nginx-module所在的位置
./configure --prefix=/usr/local/nginx --add-module=/fastdfs-nginx-module-master的路径/src    

make
make install
/usr/local/nginx就是nginx安装路径(自定义)   
cd /usr/local/nginx/conf/nginx.conf

server {
        listen       端口号;
        server_name  localhost;无需修改

        location / {
            root   html;
            index  index.html index.htm;
        }

        location /group1/M00 {//新增
            root /usr/muyou/dev/fastdfs/fastdfs_storage_data/data;
            ngx_fastdfs_module;
        }
        
然后进入FastDFS安装时的解压过的目录，将http.conf和mime.types拷贝到/etc/fdfs目录下：
cp http.conf /etc/fdfs/
cp mime.types /etc/fdfs/
另外还需要把fastdfs-nginx-module安装目录中src目录下的mod_fastdfs.conf也拷贝到/etc/fdfs目录下：

cp /路径/fastdfs-nginx-module-master/src/mod_fastdfs.conf /etc/fdfs/
 base_path=/usr/muyou/dev/fastdfs/fastdfs_storage  #保存日志目录
 tracker_server=192.168.150.132:22122 #tracker服务器的IP地址以及端口号
 storage_server_port=23000 #storage服务器的端口号
 url_have_group_name = true #文件 url 中是否有 group 名
 store_path0=/usr/muyou/dev/fastdfs/fastdfs_storage_data   #存储路径
 group_count = 3 #设置组的个数，事实上这次只使用了group1
 
 在文件的最后，设置group
 
 [group1]
 group_name=group1
 storage_server_port=23000
 store_path_count=1
 store_path0=/usr/muyou/dev/fastdfs/fastdfs_storage_data
 store_path1=/usr/muyou/dev/fastdfs/fastdfs_storage_data
 
 # group settings for group #2
 # since v1.14
 # when support multi-group, uncomment following section as neccessary
 [group2]
 group_name=group2
 storage_server_port=23000
 store_path_count=1
 store_path0=/usr/muyou/dev/fastdfs/fastdfs_storage_data
 
 [group3]
 group_name=group3
 storage_server_port=23000
 store_path_count=1
 store_path0=/usr/muyou/dev/fastdfs/fastdfs_storage_data
 
 
 创建M00至storage存储目录的符号连接：
 
 
 ln  -s  /usr/muyou/dev/fastdfs/fastdfs_storage_data/data/ /usr/muyou/dev/fastdfs/fastdfs_storage_data/data/M00
 启动nginx:
 /usr/local/nginx/sbin/nginx


#配置tracker_nginx
第二个nginx安装修改名称
./configure --prefix=/usr/local/nginx2 --add-module=/路径/fastdfs-nginx-module-master/src    #解压后fastdfs-nginx-module所在的位置

make
make install

修改第二次安装nginx的配置文件
upstream fdfs_group1 {
        server 127.0.0.1:9999;
    }
    server {
        listen       9989;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location /group1/M00 {
            proxy_pass http://fdfs_group1;
        }
启动nginx  sbin目录当前nginx目录下
开放端口即可



