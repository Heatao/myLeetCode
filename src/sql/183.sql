-- 183 从不订购的客户

select Name as Customers
from Customers left join Orders
on Customers.Id=Orders.CustomerId
where Orders.Id is null;                       -- 这里=null是错的，要用 is null

-- official题解 not in+子查询
select customers.name as 'Customers'
from customers
where customers.id not in
(
    select customerid from orders
);