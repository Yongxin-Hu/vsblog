## 一个超简洁的个人博客系统


### 技术

`前端` : 

* element-ui
* axios
* vue

`后端` : 

* mybatis plus
* springboot
* mysql
* shiro
* redis
* jwt


### 功能
* [x] 登录验证
* [x] 文章展示
* [x] 文章添加
* [x] 文章删除
* [x] 文章修改
* [x] 文章查找
* [x] 文章归档
* [x] 文章分类
* [x] 留言


### 部署
> *以下是我在 `Centos7` 远程服务器上部署 `vsblog` 的流程.*

<details>
    <summary>1. 安装 JDK</summary>

    ```shell
    # install jdk
    yum install -y java-1.8.0-openjdk-devel.x86_64
    
    # check
    java -version
    ```
</details>

<details>
    <summary>2. 安装 MySQL8.0+</summary>

	```shell
	# see https://www.cnblogs.com/raicho/p/12511998.html
	```
</details>

<details>
    <summary>3. 安装 Redis</summary>

	```shell
	# install EREL
	sudo yum install epel-release
	sudo yum update
	
	# install Redis
	sudo yum -y install redis
	
	# start Redis
	sudo systemctl start redis
	```
</details>

<details>
    <summary>4. 导入 vueblog.sql 数据库</summary>

	```shell
	# mysql -u root -p < db.sql
	mysql -u root -p < 'the specified path of database file'
	```
</details>

<details>
    <summary>5. 运行项目</summary>

	```shell
	# kill the specified server port:8080
	kill -9 $(netstat -nlp | grep :8080 | awk '{print $7}' | awk -F"/" '{ print $1 }')
	
	# run
	nohup java -jar vueblog-0.0.1-SNAPSHOT.jar > temp.txt &
	
	# test
	http://ip:8080/
	```
</details>


<details>
    <summary>6. 打包Vue文件</summary>

    ```
    1.运行npm run build打包vue项目
    2.将打包后的dist传到服务器的  /usr/share/nginx/html/dist 下
    ```
</details>

<details>
    <summary>7. 配置Nginx</summary>

	```shell
	# first you need install the nginx
	sudo apt install nginx
	
	# then check the default configuration file
	nginx -t
	
	# vim /etc/nginx/nginx.conf
	# such as the example configuraion be added as follows
	 server {
	        listen       80 default_server;
	        listen       [::]:80 default_server;
	        server_name  yongxin.xyz;
	        root         /usr/share/nginx/html/dist; # Your vue profile location
	
	        # Load configuration files for the default server block.
	        include /etc/nginx/default.d/*.conf;
	
	        location / {
	                try_files $uri $uri/ @router;
	                index index.html;
	        }
	
	        location @router {
	            rewrite ^.*$ /index.html last; # 所有请求，返回index.html
	        }
	    }
	. . .
	
	# reload the nginx
	nginx -s reload
	```
</details>

### 配置Gittalk

将自己的Client ID和Client Secret以及其他选项填入Gittalk配置中。

![](https://raw.githubusercontent.com/Yongxin-Hu/PicBegRepo/main/20210531213527.png)