# 表结构 创建表的语句
-- 数据库 psi 脚本文件

-- 检查数据库是否存在，不存在创建该数据库
drop database if exists psi;
create database psi default character set utf8 collate utf8_general_ci;

-- 表 goods

-- unsigned not null  auto_increment
-- 去掉负数    不为空    自增长

-- varchar 可变字符长度 可以根据数据大小自动变更长度,  char 固定字符长度 在数据库表生成是就会全面默认占满
-- 汉子占一个字符两个字节  字母占一个字符一个字节

drop table if exists goods;
create table goods
(
  id bigint unsigned not null  auto_increment comment 'ID',
  name varchar(20) comment '商品名称',
  category bigint unsigned comment '类别ID',
  merchant bigint unsigned comment '商家ID',
  price int comment '价格(单位/分),用整形不用浮点型保证精度不丢失',
  stock int comment '库存',
  primary key (id)
) engine = InnoDB
  default charset = utf8 comment = '商品表';

-- 表 category
drop table if exists category;
create table category
(
  id bigint unsigned not null  auto_increment comment 'ID',
  name varchar(20) comment '类别名称',
  parent bigint comment '上一级',
  primary key (id)
) engine = InnoDB
  default charset = utf8 comment = '商品类别表';

-- 表 merchant
drop table if exists merchant;
create table merchant
(
  id bigint unsigned not null  auto_increment comment 'ID',
  name varchar(20) comment '商家名称',
  telephone bigint comment '联系方式',
  primary key (id)
) engine = InnoDB
  default charset = utf8 comment = '商家表';