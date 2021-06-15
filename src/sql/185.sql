-- 185 部门工资前三高的所有员工

-- 没做出来，参考评论区高斯恍惚同学
-- 对于这种分组内取前几名的问题，可以先group by然后用having count()来筛选
-- 比如这题，找每个部门的工资前三名，那么先在子查询中用Employee和自己做连接，
-- 连接条件是【部门相同但是工资比我高】，那么接下来按照having count(Salary) <= 2来筛选的原理是：
-- 如果【跟我一个部门而且工资比我高的人数】不超过2个，那么我一定是部门工资前三
select d.Name as Department,e.Name as Employee,e.Salary as Salary
from Employee as e left join Department as d
on e.DepartmentId = d.Id
where e.Id in
(
    select e1.Id
    from Employee as e1 left join Employee as e2
    on e1.DepartmentId = e2.DepartmentId and e1.Salary < e2.Salary      -- 这样进行自连接的话会少很多条目，性能更好
    group by e1.Id
    having count(distinct e2.Salary) <= 2
)
and e.DepartmentId in (select Id from Department)
order by d.Id asc,e.Salary desc