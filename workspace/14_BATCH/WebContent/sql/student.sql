DROP TABLE STUDENT;
CREATE TABLE STUDENT
(
	SNO VARCHAR2(5) PRIMARY KEY,
	NAME VARCHAR2(32),
	KOR NUMBER(3) CHECK(KOR BETWEEN 0 AND 100),
	ENG NUMBER(3) CHECK(ENG BETWEEN 0 AND 100),
	MAT NUMBER(3) CHECK(MAT BETWEEN 0 AND 100),
	AVE NUMBER(5,2),
	GRADE CHAR(1)
);
