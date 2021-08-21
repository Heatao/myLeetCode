--按照两列去重，然后任意取一行--
select any(*) from (select * from accesslog group by http_host, url path)

select * from (select * , row_number() over(partition by a,b order by c) as rk from t1) where rk=1