--创建表
--创建序列
--创建序列触发器
--插入值
--commit
create user email identified by a;
grant dba to email;
grant connect to email;
grant resource to email;

----好友列表
drop table files;
create table user_person(
       userId int references userInfo(userId),--用户邮箱号
       personId int not null,  --联系人帐号
       personName varchar2(20) unique,   --联系人备注
       personType varchar2(20) not null --联系人分组
);
select * from tab;
select * from user_person;
commit;
select personname,persontype from user_person where personid=?
distinct;
update user_person set personname='nihao',persontype='家人' where personid=100149; 
select personId from user_person where userId=100164 and personName='';
insert into user_person values(100164,100162,'hehe','我的好友');
insert into user_person values(100164,100163,'hahalisi','我的好友');
insert into user_person values(100164,100148,'进','好朋友');
insert into user_person values(100164,100149,'bubu','好朋友');
select userid from userInfo where userName='lsy';
insert into user_person values(100164	,100162,'谁','同事');

select 
u.userid
from userInfo u
inner join user_person up
on u.userid=up.userid
where u.username='lsy';
select userid from userInfo where username='lsy';
select personid from user_person where userid=100164 and personname='阿达';

delete user_person where personid=100148;



select p.personname,p.personid,u.username,p.persontype from userInfo u inner join user_person p on u.userid=p.userid where p.userid=100148;




-------用户信息表
drop table userInfo cascade constraints;
select * from userinfo;
create table userInfo(
       userId int primary key,--邮箱号
       userName varchar2(20) not null,--用户名
       userPwd varchar2(20) not null,--密码
       usersex char(2) check (usersex in('男','女')), --性别
       birthday varchar2(20),--生日
       tel varchar2(20) unique,--电话号码
       idCard varchar2(20) unique not null, --身份证号
       querstion varchar2(40) ,--密保问题        
       answer varchar2(40) not null,--密保问题答案
       userA1 varchar2(20), --预留1
       userA2 varchar2(20), --预留2
       userA3 varchar2(20) --预留3
);
insert into Userinfo values(seq_userInfo.Nextval,'zs','a','男',sysdate,'12345678902','111111111111131111','你的爸爸是谁','我',null,null,null);
insert into userInfo values(seq_userInfo.nextval,'lsy','a','男','','','','你的爸爸是谁？','我',1,1,1);

select * from userInfo;

delete userInfo where userid =100009;
commit;

drop sequence seq_userInfo;
--创建序列
create sequence seq_userInfo  start with 100001 increment by 1; 

insert into userInfo values(seq_userInfo.nextval,'crazy','a','男',to_date('2013-07-08','yyyy-MM-dd'),'15575464876','430626199505202415','你的爸爸是谁?','我',0,0,0);
insert into userInfo values(seq_userInfo.nextval,'lan','a', '男',to_date('1996-09-09','yyyy-MM-dd'),15616644584,'430626199409093328','你的爸爸是谁?','我',0,0,0);
insert into userInfo values(seq_userInfo.nextval,'lan1','a','男',to_date('1996-09-09','yyyy-MM-dd'),15616644564,'430626199209093328','你的爸爸是谁?','我',0,0,0);
insert into userInfo values(seq_userInfo.nextval,'lan2','a','男',to_date('1996-09-09','yyyy-MM-dd'),15616644554,'430626199309093328','你的爸爸是谁?','我',0,0,0);
insert into userInfo values(seq_userInfo.nextval,'lan3','a','男',to_date('1996-09-09','yyyy-MM-dd'),15616644544,'430626199109093328','你的爸爸是谁?','我',0,0,0);
insert into userInfo values(seq_userInfo.nextval,'lan4','a','男',to_date('1996-09-09','yyyy-MM-dd'),15616644534,'430626199009093328','你的爸爸是谁?','我',0,0,0);
--此处的1 为常数，没有实际意义
commit;

--文件表    
drop table files;
create table files(
       filesid int primary key,
       emailid int references email(emailId),
       filesname varchar2(20),
       NewFile blob
);

delete files where emailid is not null;
drop sequence seq_files;
create sequence seq_files start with 101 increment by 1;
select * from files;
insert into files values(seq_files.nextval,10183,'a',null);
select filesid,filesname from files where emailid=10474;
----邮件表
drop table email;
create table email(
       emailId int primary key,--邮件编号
       fromId int references userInfo(userId),--发信人邮箱号
       toId int,--收件人邮箱号
       title varchar2(20),--主题
       con varchar2(100), --内容
       email_date varchar2(20) not null,--发送日期
       email_send int check (email_send in (0,1,2)),--发送状态 0未发送 1发送成功 2删除已发送
	     email_drop int check (email_drop in (0,1,2)),--是否删除  0 表示删除 1表示未删除,2彻底删除
	     inbox_type int check(inbox_type in(0,1)),--邮件类型   0未读,1已读
	     partition_type int check (partition_type in (0,1,2,3)), --邮件状态类型  0 表示为个人邮件  1表示群邮件  2表示草稿箱 3表示为垃圾邮件
       emailA1 varchar2(500),
       emailA2 varchar2(20),
       emailA3 varchar2(20),
       emailA4 varchar2(20),
       emailA5 varchar2(20)
);
commit;
update email set email_send=2 where emailid=10531;

select email_date from 

select title,toid,email_date,con 
from email 
where Emailid=?
select * from email where emailid=10507 and  partition_type=2

select inbox_type from email where Emailid=10442 and toid='100022@qq.com'
delete files where emailid=10507;
delete email where emailid=10507;
select emailid,fromid,title,email_date from email where toid='100022@qq.com' and partition_type=0 
select * from email where con link '%a';
commit;
select * from email;
select * from email where inbox_type=0;
update email set email_drop=1 where emailid=10390;
--全部标记为已读
update email set inbox_type=1 where partition_type=0;
update email set inbox_type=1 where partition_type=0 and email_drop=1
--全部标记为未读
update email set inbox_type=0 where partition_type=0;
update email set partition_type=3 where emailid=10390;
select * from email where inbox_type=0 and Emailid=10294;
select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') from dual;
insert into email values(seq_email.nextval,100022,'100032','asadad','sdaca',to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'),1,1,0,0,null,null,null);
--创建序列
update email set inbox_type=1 where emailid=10199;
create sequence seq_email increment by 1 start with 10001;
select * from email;
delete email where fromid=100002;
update email set email_send=0,email_drop=1 where emailid=10191;
commit;
select e.emailid,e.fromid,e.title,e.email_date from email e inner join files f on e.emailid=f.emailid where e.toid=? and partition_type=0;

delete email where con is not null;
select emailid,fromid,title,email_date from email where toid='312' and partition_type=0;
--已发送
select emailid,fromid,title,email_date from email where fromId=100002;

--草稿箱
select emailid,toid,title,to_char(email_date,'yyyy-MM-dd HH:mm:ss') email_date from email where fromid=100022 and partition_type=2;
--查询未读邮件数量
select count(emailid) count from email where inbox_type=0;
--收件箱
select emailid,fromid,title,email_date from email where toid=? and partition_type=0;
--群邮件收件箱
select emailid,fromid,title,email_date from email where toid=100022 and partition_type=1;
--查看附件
select newFile from files where emailid=10192;
select newFile from files where emailid=10192;
select NEWFILE from files where emailid=10192;

from email e
inner join 
--创建序列触发器
drop trigger emailId;
create or replace trigger emailId
before insert on email
for each row
begin
    select seq_email.nextval into:new.emailId from dual;
end; 
commit;

100001
10001
select * from where userid=100001 and td=


--群邮件信息表
select * from flock;
drop table flock ;    cascade constraints;
create table flock(
       flockId int primary key,--群号
       flockName varchar2(20) unique not null,--群名字
       flockA1 varchar2(20), --预留1
       flockA2 varchar2(20) --预留1
);
--创建序列

drop sequence seq_flock_id;
create sequence seq_flock_id start with 1000 increment by 1;
insert into flock values(seq_flock_id.nextval,'一起玩耍',null,null);
commit;
select * from flock;
select f.flockname from flock f inner join flock_info fi on f.flockid=fi.flockid where fi.userid=100164;
insert into flock values();
select flockid from flock where flockname='源辰26班';
--群联系人表  代表此群拥有哪些人
drop table flock_info;
create table flock_info(
       flockId int references flock(flockId) ,--群号
       userId int references userInfo(userId)--群成员id
);
insert into flock_info values(1042,100163);
commit;
select * from userinfo;
select * from flock_Info;
--创建序列
create  sequence seq_flock increment by 1 start with 1001;
insert into flock values(seq_flock.nextval,'一起玩耍',null,null);

select e.emailid,f.flockname,e.title,e.email_date from flock f
inner join flock_info fi on f.flockid=fi.flockid left join userInfo u on u.userid=fi.userid left join email e on e.fromid=u.userid
where e.partition_type=1 and e.email_drop=1;

select 
e.emailid,f.flockname,e.title,e.email_date
from flock f
inner join flock_info fi
on f.flockid=fi.flockid
left join email e
on e.fromid=fi.userid
where e.toid='1000@qq.com';

select emailid,toid,title,email_date from email where fromid=100162 and partition_type=1;

select * from email;



--创建序列触发器
create or replace trigger flockId
before insert on flock
for each row
begin
    select seq_flock.nextval into:new.flockId from dual;
end;

insert into flock values(1,'一起愉快的玩耍 ' ,to_date('2013-07-08','yyyy-MM-dd'),0);
commit;

select flockid from flock_info where userid=100162
union
select emailid from email where toid='100162@qq.com' and partition_type=1;where partition_type=1
--
select * from email where toid=(select toid from email
                                intersect 
                                select 
                                f.flockid
                                from flock f
                                inner join flock_info fi
                                on f.flockid=fi.flockid
                                where userId=100164 ) and   partition_type=1;

select emailid,fromid,title,email_date from email where toid=1000 intersect
select toid from email where partition_type=1 intersect select  f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=100164                                
                                
                         
select emailid,fromid,title,email_date from email where toid=(select toid from email where partition_type=1
              intersect select  f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=100164)
select emailid from email where toid=(
select toid from email 
union
select toid from email where partition_type=1
              intersect select  f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=100164)
              
select  f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=100164;
  
----查询你有几个群
select  f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=100164
select emailid,title,con,email_date from email where (toid=1000 or toid=1002 or toid=1021) and fromid=100164;
commit;
       
select * from email;

select 

delete email where toid=1021;
commit;
--查询收信人（邮箱编号）
select toid from email where emailid=10681;

--查询群名称
select distinct flockname from email e left join flock_Info fi on e.fromid=fi.userid
inner join flock f on fi.flockid=f.flockid where flockid=1021;

select   e.emailid,f.flockname,e.email_date,e.inbox_type
from email e
left join flock f
on e.emailid=f.flockid
right join flock_info fi
on f.flockid= fi.flockid
where e.email_send=1 and e.partition_type=1 and e.email_drop=1 and fi.userid=100164
distinct
select distinct emailid,title,email_date from email where toid=100163 and partition_type=1
drop table email;
drop sequence seq_email;
drop sequence seq_userinfo;

--需要创建一张表用来记录用户登录时间
drop table logo_time;
create table logo_time(
        userId int references userInfo(userId),--用户邮箱号
        logo_time date,  --用户登录时间
        logo_addr varchar2(20) ,--用户登录地点
        logo_type varchar2(20) default '面板登录' --用户登录方式
);


--查询垃圾邮件
select * from email where con like '%fuck%' and emailid= 10725;
select * from email where emailid=10769

delete email where toid=1000 cascade;
commit;

select distinct fi.userid from flock f inner join flock_info fi on f.flockid=fi.flockid where f.flockid=1021; 

select distinct * from flock_info where userid=100163 and flockid=1021;

select flockname from flock where flockid=1000;
select filesid,filesname from files where emailid=10824
select u.username,p.personid,p.personname,p.persontype from userInfo u inner join user_person p on u.userid=p.userid where p.userid=100164;
select personId,personName,personType from user_person where userId=100164;
select username from Userinfo where userid=100163;
select personId,personName,personType from user_person where userId=100164


select title,con,EmailA1,emailA2 from email where Emailid=10995
