
create table mvc_board(
     bId Number(4) Primary key,
     bName varchar2(20) not null,
     bTitle varchar2(100) not null,
     bContent varchar2(2000) not null,
     bDate date default sysdate,
     bHit number(4)default 0,
     bGroup number(4),
     bStep number(4),
     bIndent number(4)
 );   
 create sequence mvc_board_seq;
 
 select*from mvc_board;
 
 insert into mvc_board( bid, bName, bTitle, bContent,  bHit, bGroup, bStep, bIndent)
 values (mvc_board_seq.nextval, 'damn','태회네 카페 분위기 좋네요','어제 태회네 카페에 가서 공부 했는데 분위기가 좋네요',0,mvc_board_seq.currval,0,0);
 insert into mvc_board( bid, bName, bTitle, bContent,  bHit, bGroup, bStep, bIndent)
 values (mvc_board_seq.nextval, 'ragi','태회네 카페 커피 맛있어요','태회네 카페 에티오피아 원두 쓰는데 풍미가 너무 좋네요',0,mvc_board_seq.currval,0,0);
  insert into mvc_board( bid, bName, bTitle, bContent,  bHit, bGroup, bStep, bIndent)
 values (mvc_board_seq.nextval, 'junmandu','판교역 카페 추천좀해주세요!','판교역 주변에 일하기 좋은 분위기 좋은 카페 좀 알려주세요!',0,mvc_board_seq.currval,0,0);
 commit;
 
 select bId, bName, bTitle, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc;
 
 select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board where bId = 1;    

delete  from mvc_board where bId=48;
 DROP SEQUENCE mvc_board_seq;
drop table mvc_board;
    
 
 ----------------------------
 select *from board;
 desc board;
 ----------------------------
 --------------------------------------------
create table c_Board(
cNum Number(4) Primary key,     --댓글 번호
cContent varchar2(1000) not null,            --댓글 내용
cName varchar2(20),                     --댓글 작성자
cDate date default sysdate,         --댓글 작성시간
cStep Number(4),                        --댓글 의 댓글 순서
cLevel Number(4),                       --댓글 의 갯글 깊이
cMom Number(4)                          --댓글 의 부모글
);

 create sequence c_Board_seq;

insert into c_Board (cNum, cContent, cName, cStep, cLevel, cMom) 
                    values(c_Board_seq.nextval, 'is cContent1', 'is cName1', 0,0,1);
insert into c_Board (cNum, cContent, cName, cStep, cLevel, cMom) 
                    values(c_Board_seq.nextval, 'is cContent2', 'is cName2', 0,0,1);
insert into c_Board (cNum, cContent, cName, cStep, cLevel, cMom) 
                    values(c_Board_seq.nextval, 'is cContent3', 'is cName3', 0,0,1);          
 insert into c_Board (cNum, cContent, cName, cStep, cLevel, cMom) 
                    values(c_Board_seq.nextval, 'is cContent1', 'is cName1', 0,0,2);
insert into c_Board (cNum, cContent, cName, cStep, cLevel, cMom) 
                    values(c_Board_seq.nextval, 'is cContent2', 'is cName2', 0,0,2);
insert into c_Board (cNum, cContent, cName, cStep, cLevel, cMom) 
                    values(c_Board_seq.nextval, 'is cContent3', 'is cName3', 0,0,2);         


    drop table c_Board;
 DROP SEQUENCE c_Board_seq; 
 
select *from c_board;

  select *from c_board where cMom = 48;
  commit;
insert into c_board(cNum, cContent, cName, cStep, cLevel, cMom) values (mvc_board_seq.nextval, 'conetent','name',0,0,61);

select cNum, cContent, cName, TO_CHAR(cDate, 'YYYY-MM-DD HH24:MI:SS') AS cDateFmt ,cStep, cLevel, cMom from c_board where cMom=1 ;