-- 176 第二高的薪水
-- 第n高/低的情况，可以用排序后limit i offset j来做，offset就是不要前面的多少

-- sql中 offset 用法是去掉几行元素。
select distinct Salary as SecondHighestSalary from Employee order by Salary desc limit 1 offset 1;

-- 这道题还需要考虑只有一条纪录的情况
-- 解决null问题可以用临时表，也可以用ifnull
-- 注意下面的expr1如果是子查询需要用括号喔
select ifnull((select distinct Salary from Employee order by Salary desc limit 1 offset 1), null) as SecondHighestSalary;

-- 补充：mysql中的ifnull，nullif，isnull
-- isnull(expr) 的用法：如expr 为null，那么isnull() 的返回值为 1，否则返回值为 0。
-- IFNULL(expr1,expr2)的用法：假如 expr1 不为 NULL ，则 IFNULL() 的返回值为 expr1; 否则其返回值为 expr2。
-- NULLIF(expr1,expr2)  的用法：如果expr1=expr2成立，那么返回值为NULL，否则返回值为expr1。这和CASE WHEN expr1=expr2 THEN NULL ELSE expr1 END相同。