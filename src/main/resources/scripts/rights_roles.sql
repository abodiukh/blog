insert into user_roles(name) values('reader'),('writer'),('editor'),('admin');
insert into user_rights(name) values('read'),('write'),('create'),('delete'),('publish'),('administer');
insert into user_roles_rights(role_id, right_id) select user_roles.id, user_rights.id from user_roles, user_rights where user_roles.name = 'admin';
insert into user_roles_rights(role_id, right_id) select user_roles.id, user_rights.id from user_roles, user_rights where user_roles.name = 'writer' and user_rights.name in ('read', 'write', 'publish', 'delete');