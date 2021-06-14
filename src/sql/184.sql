-- 184 部门工资最高的员工
-- 常考点：分组后的top1

-- 下面是错误的，max这里只会返回一个，而且也不需要自连接
select Department.Name as Department, e.Name as Employee, e.Salary
from (
    select e1.Name, e1.Salary, e1.DepartmentId
    from Employee e1
    join (select Name, max(Salary) from Employee group by DepartmentId) e2
    where e1.Name=e2.Name
) e, Department
where e.DepartmentId=Department.Id

-- 看了官方题解，才知道有这种组合in的操作
select Department.Name as Department, Employee.Name as Employee, Employee.Salary
from Employee, Department
where
    (Employee.DepartmentId, Employee.Salary)
    in
    (select DepartmentId, max(Salary) from Employee group by DepartmentId)
    and Employee.DepartmentId=Department.Id;

-- 评论区提到用窗口函数
SELECT S.NAME, S.EMPLOYEE, S.SALARY
  FROM (SELECT D.NAME,
               T.NAME EMPLOYEE,
               T.SALARY,
               ROW_NUMBER() OVER(PARTITION BY T.DEPARTMENTID ORDER BY T.SALARY DESC) RN
          FROM EMPLOYEE T
          LEFT JOIN DEPARTMENT D
            ON T.DEPARTMENTID = D.ID) S
 WHERE S.RN = 1