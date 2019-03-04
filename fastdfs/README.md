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
=================其他操作开始=========
