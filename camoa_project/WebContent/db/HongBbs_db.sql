create table bbs_hong (
	hong_num number(5) not null, primary key(hong_num),
	hong_name varchar2(20) not null, 					
	hong_subject varchar2(100) not null,				
	hong_content varchar2(3000) not null,		 	
	hong_realpath varchar2(200),
	hong_filename varchar2(100),							
	hong_count number(5) default 0, 				
	hong_hit number(5) default 0,
	hong_date date not null 						
);

create table comment_hong(
	c_num number(5) not null,
	c_hong_name varchar2(20) not null, 					
	c_hong_content varchar2(500) not null, 					
	c_hong_num number(5) not null,
	c_hong_date date not null 							
);

drop table bbs_hong;
select * from bbs_hong;

drop table comment_hong;
select * from comment_hong;

select * from cus;

commit;