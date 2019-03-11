#安装erlang
yum install erlang
#安装MQ
wget http://www.rabbitmq.com/releases/rabbitmq-server/v3.5.0/rabbitmq-server-3.5.0-1.noarch.rpm
rpm -ivh rabbitmq-server-3.5.0-1.noarch.rpm
#启动
service rabbitmq-server start
#开放端口
5672  15672
#开启管理插件
rabbitmq-plugins enable rabbitmq_management
#重启
service rabbitmq-server restart
#RabbitMQ服务管理命令
启动服务：rabbitmq-server -detached【 /usr/local/rabbitmq/sbin/rabbitmq-server  -detached 】
查看状态：rabbitmqctl status
关闭服务：rabbitmqctl stop
列出角色：rabbitmqctl list_users
开启某个插件：rabbitmq-plugins enable xxx
关闭某个插件：rabbitmq-plugins disable xxx
注意：重启服务器后生效。
