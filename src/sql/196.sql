-- 196 删除重复的电子邮箱

-- select * from (
--     delete from Person where Id in (
--         select p1.Id from Person p1, Person p2
--         where p1.Email=p2.Email and p1.Id>p2.Id
--     )
-- );

-- select *可以按照下面这种写法，但是delete不可，原因如下：
-- 在MYSQL中，不能先Select一个表的记录，再按此条件Update和Delete同一个表的记录，
-- 否则会出错：You can't specify target table 'xxx' for update in FROM clause.
-- 解决方法：使用嵌套Select——将Select得到的查询结果作为中间表，再Select一遍中间表作为结果集，即可规避错误。
delete from Person where Id in (
        select p1.Id from Person p1, Person p2
        where p1.Email=p2.Email and p1.Id>p2.Id
    );

delete from Person where Id in (select * from(
        select p1.Id from Person p1, Person p2
        where p1.Email=p2.Email and p1.Id>p2.Id
    ) t1 );

-- 官方题解，这里删除的语法和select很像，和之前用delete from的有点不同呐
DELETE p1 FROM Person p1,
    Person p2
WHERE
    p1.Email = p2.Email AND p1.Id > p2.Id;
