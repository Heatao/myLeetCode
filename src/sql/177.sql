-- 177 第n高的薪水
-- mysql自定义函数

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  -- 这里不用自定义变量，是因为自定义变量不能放倒下面的select中去用（貌似可以，这一块还需要捋一捋）
  SET N=N-1;
  RETURN (
      -- Write your MySQL query statement below.
      select ifnull((select distinct salary from Employee order by salary desc limit 1 offset N), null)
  );
END

-- 补充，mysql中=和:=
-- 在set中=和:=是一样的，但是在select中:=才表示赋值，而=是判断是否相等