--회원생성
CREATE TABLE CUSTOMER (
	CUSTOMER_ID VARCHAR2(30) PRIMARY KEY,
	PWD VARCHAR2(30) NOT NULL,
	CUSTOMER_NAME VARCHAR2(12) NOT NULL,
	BIRTH DATE NOT NULL,
	EMAIL VARCHAR2(30),
	ADDR VARCHAR2(50),
	CONTACT VARCHAR2(15),
	SIGN_DATE DATE DEFAULT SYSDATE,
	SELLER VARCHAR2(6) DEFAULT NULL
);



--회원 정보 입력 
INSERT INTO CUSTOMER VALUES('CHOI1','1111','최예린', '1996-08-06', 'AAA111@naver.com', '경기도 성남시 수정구', '010-1111-1111', SYSDATE, DEFAULT);

INSERT INTO CUSTOMER VALUES('YOO', '2222', '유이아', '1999-01-12', 'BBB222@naver.com', '부산 금정구 남산동', '010-2222-2222', SYSDATE, DEFAULT);

INSERT INTO CUSTOMER VALUES('CHOI2', '3333', '최유정','08-11-23', 'CCC333@naver.com', '전남 해남군 화원면', '010-3333-3333', SYSDATE, DEFAULT);

INSERT INTO CUSTOMER VALUES('KIM', '4444', '김미현','05-12-25',  'DDD444@naver.com', '인천 미추홀구 주안동', '010-4444-4444', SYSDATE, DEFAULT);

INSERT INTO CUSTOMER VALUES('HYEON', '5555', '현우정','02-03-28', 'EEE555@naver.com', '전남 목포시 옥암동', '010-5555-5555', SYSDATE, DEFAULT);

--아이디 중복 체크 

select customer_id from customer where customer_id =? ;

--판매자 입력
INSERT INTO CUSTOMER VALUES('ADMIN', '1234', '판매자','1991-05-15', 'seller@naver.com', '아틀란티스 전북익산', '010-1111-1111', SYSDATE, 'SELLER');


--레코드 삭제
DELETE FROM CUSTOMER WHERE CUSTOMER_ID = 'Q';

--확인용
SELECT * FROM CUSTOMER;


--커밋
COMMIT

--로그인

--회원가입 
--INSERT INTO CUSTOMER VALUES(?, ?, ? ,? ,? ?, ?, SYSDATE);


--2)성인인증  → 자바에서 성인인증 따로 만드는 걸로 !!  
--		   성인이 아니다 !! -> DB에 저장 안 됨 (돌아가)
--		   성인이다 -> DB저장 



--3) 아이디/비밀번호 찾기 
--아이디

--비밀번호



--4)사용자정보 수정(비밀번호, 주소, 연락처, 이메일)
--비밀번호만 수정하고 싶을때? 2


--주소만 수정하고 싶을 때?

--이메일만 수정하고 싶을때? 

--5)사용자정보 삭제 (탈퇴)

