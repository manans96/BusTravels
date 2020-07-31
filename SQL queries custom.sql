select u.user_name as username, a.password as password, u.enabled as enabled from user u, userauth a where id_user=id_user_auth=1 and user_name = 'extra2020';

select user_name as username, role as authority from user where user_name = "test20";