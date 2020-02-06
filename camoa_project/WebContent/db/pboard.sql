create table p_bbs(
cf_num number(10),
cf_id varchar2(20),
cf_name varchar2(20),
cf_address varchar2(20),
cf_phone varchar2(20),
cf_ame number(10),
cf_latte number(10),
cf_caramel number(10),
cf_mocha number(10),
cf_vanila number(10),
cf_workhour varchar2(20),
cf_park varchar2(20),
cf_intro varchar2(2000),
cf_readcount number(10),
cf_recom number(10),
cf_reg_date date,
primary key(cf_num)
)SEGMENT creation IMMEDIATE ;
