--------------------------------------------------------
--  파일이 생성됨 - 월요일-4월-23-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table EXAM
--------------------------------------------------------

  CREATE TABLE "HR"."EXAM" 
   (	"ID" NUMBER, 
	"TITLE" VARCHAR2(100 BYTE), 
	"SID" VARCHAR2(20 BYTE), 
	"KOR" NUMBER, 
	"ENG" NUMBER, 
	"MAT" NUMBER, 
	"REGDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into HR.EXAM
SET DEFINE OFF;
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (1,'semi','aaa',98,77,95,to_date('18/05/03','RR/MM/DD'));
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (3,'semi','ccc',76,86,68,to_date('18/05/03','RR/MM/DD'));
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (4,'final','aaa',98,77,76,to_date('18/05/03','RR/MM/DD'));
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (5,'final','ddd',77,87,67,to_date('18/07/03','RR/MM/DD'));
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (7,'semi','eee',56,67,78,null);
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (8,'semi','fff',56,67,68,to_date('17/05/06','RR/MM/DD'));
Insert into HR.EXAM (ID,TITLE,SID,KOR,ENG,MAT,REGDATE) values (9,'semi','aaa',77,88,99,null);
--------------------------------------------------------
--  Constraints for Table EXAM
--------------------------------------------------------

  ALTER TABLE "HR"."EXAM" ADD CONSTRAINT "EXAM_CH_KOR" CHECK (kor>=0 and kor<=100) ENABLE;
