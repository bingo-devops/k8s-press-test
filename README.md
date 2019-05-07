# 说明

## 构建

* 准备java8 + maven环境，执行如下命令构建jar包和Docker镜像

```bash
mvn package -DskipTests=true
```

* 准备mysql数据库

1. 安装mysql数据库
2. 创建数据库`k8s_press_test`
3. 创建表
```sql
CREATE TABLE `note`
(
  `id`                 varchar(50)   NOT NULL,
  `title`              varchar(255)  NOT NULL,
  `body`               varchar(255)  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
```

## 运行

### 通过Docker运行

```bash
docker run \
-e "spring.datasource.url=jdbc:mysql://mysql:3306/k8s_press_test?characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false" \
-e "spring.datasource.username=root" \
-e "spring.datasource.password=Pass@word1" \
-p 8080:8080 hub.bingosoft.net/bingodevops/k8s-press-test
```
其中数据库连接地址和用户名密码需换成实际的值

## 测试入口

* 测试CPU
```
<site_url>/testCpu?content=a-sentence-to-encrypt&num=100
```
其中content传需要加密的字符串，num传加密次数

* 测试数据库读写
```
<site_url>/testIo?num=10
```
其中num代表数据库读写次数