-- 175 组合两个表

-- full outer join在mysql中不适用
select FirstName, LastName, City, State
    from Person
    full outer join Address
    on Person.PersonId=Address.PersonId;

-- mysql可以用union来做到full outer join
select FirstName, LastName from Person left join Address on Person.PersonId=Address.PersonId
union
select City, State from Person right join Address on Person.PersonId=Address.PersonId

-- 175这道题，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息。。。
-- 证明是左连接，left join即left outer join
select FirstName, LastName, City, State from Person left join Address on Person.PersonId=Address.PersonId