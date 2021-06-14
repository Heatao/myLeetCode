-- 180连续出现的数字
-- SQL中常考的点了，连续性问题
-- 要么用自连接，要么用窗口函数

-- 官方题解，用的是自连接
SELECT *
FROM
    Logs l1,
    Logs l2,
    Logs l3
WHERE
    l1.Id = l2.Id - 1
    AND l2.Id = l3.Id - 1
    AND l1.Num = l2.Num
    AND l2.Num = l3.Num

-- 直接CV题解区大佬的答案
-- 不仅考虑了未连续的情况，也考虑了不止3次的情况
-- https://leetcode-cn.com/problems/consecutive-numbers/solution/sql-server-jie-fa-by-neilsons/
SELECT DISTINCT Num as ConsecutiveNums FROM (
SELECT Num,COUNT(1) as SerialCount FROM
(SELECT Id,Num,
row_number() over(order by id) -
ROW_NUMBER() over(partition by Num order by Id) as SerialNumberSubGroup
FROM Logs) as Sub
GROUP BY Num,SerialNumberSubGroup HAVING COUNT(1) >= 3) as Result

-- 补充：row_number() over([partition by value_expression,...n] order by columnName)
-- 分组排序