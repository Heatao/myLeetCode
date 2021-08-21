-- 178 分数排名
-- 如果不用rank函数的话会很难

-- 解法一：用窗口函数dense_rank()
-- Rank加``是因为Rank本身是mysql的关键字
SELECT
Score ,
dense_rank() over (order by Score desc)  as 'Rank'
-- 不指定 partition by 相当于所有行数据一个 partition, 数据进行区内排序
-- dense_rank() 相当于每一行数据一个窗口, 对数据进行比较
FROM Scores;

-- 解法二：用自连接 + group by
-- 下面这种解法充分利用了SQL的执行顺序是from, where, select
-- 其次找出排名也就相当于找出大于等于该数的不重复数字有几个（数相同排名相同）.用group by 是因为需要对每个数据进行排名
select s1.Score, count(distinct(s2.score)) `Rank`
from
Scores s1,Scores s2
where
s1.score <= s2.score
group by s1.Id
order by `Rank`

-- 上面这种写法相当于：--
SELECT Score,
 (SELECT count(DISTINCT score) FROM Scores WHERE score >= s.score) AS 'Rank'
FROM Scores s
ORDER BY Score DESC;

-- 补充知识
-- 窗口函数的区别
--     rank() over (PARTITION BY xx ORDER BY xx [DESC])
--     排名为相同时记为同一个排名, 并且参与总排序
--
--     dense_rank() over (PARTITION BY xx ORDER BY xx [DESC])
--     排名相同时记为同一个排名, 并且不参与总排序
--
--     row_number() over (over (PARTITION BY xx ORDER BY xx [DESC]))
--     排名相同时记为下一个排名
