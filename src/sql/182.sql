-- 182 查找重复的电子邮箱

select distinct p1.Email
from Person p1, Person p2
where p1.Email=p2.Email and p1.Id!=p2.Id;

-- others
select Email from Person group by Email having count(Email) > 1