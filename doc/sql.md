# 数据库

## user

```sql
create table if not exists user
(
	uid varchar(255) not null
		primary key,
	email varchar(255) null,
	group_type varchar(255) null,
	name varchar(255) null,
	password varchar(255) null,
	type bigint not null
);
```