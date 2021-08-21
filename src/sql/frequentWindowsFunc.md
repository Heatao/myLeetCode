### 常用时间函数

DENSE_RANK() OVER (partition by XX order by XX)

ROW_NUMBER() OVER(PARTITION BY XXX ORDER BY XXX DESC)

### 常见题型和解法

自连接

连接 left join xx in

子查询

分组排序 rank() 或者 dense_rank() over

分组，分组之后去重之类的 row_number() over ([partion by] order by [desc])，很有可能在子查询中用

in的使用，可能是多个字段in子查询结果

### 技巧

1. 先有思路在写，不要上来直接写

2. 先写子查询

3. 先写from

### 窗口函数

窗口函数的区别

假如3行分别为 7 7 8，那么：
 
rank() over (PARTITION BY xx ORDER BY xx [DESC])
排名为相同时记为同一个排名, 并且参与总排序 1 1 3

dense_rank() over (PARTITION BY xx ORDER BY xx [DESC])
排名相同时记为同一个排名, 并且不参与总排序 1 1 2

row_number() over (over (PARTITION BY xx ORDER BY xx [DESC]))
排名相同时记为下一个排名 1 2 3

### Group by

group by的时候，select要么是gourp by的字段，要么是聚合字段。

在 SQL 中增加 HAVING 子句原因是，WHERE 关键字无法与聚合函数一起使用。

having里一定要有一个聚合函数，然后进行过滤

### 随机取一行

select * from table where id > (max(id)-min(id))*rand() limit 1

如果需要没有空洞的随机，需要用到floor和limit Y,1

MySQL 处理 limit Y,1 的做法就是按顺序一个一个地读出来，丢掉前 Y 个，然后把下一个记录作为返回结果，因此这一步需要扫描 Y+1 行。再加上，第一步扫描的 C 行，总共需要扫描 C+Y+1 行，执行代价比随机算法 1 的代价要高。