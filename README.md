# 🍺 콘솔 기반 온라인 양주 쇼핑몰 (Team)
-------------
팀 프로젝트 (서은지 외 4명) <br/>
(2021.08.11 - 2021.09.04) <br/>
------------
### ✨프로젝트 목표
-------
- JDBC와 MVC 구조에 대한 이해
- 객체지향 설계 및 구현

### ✨구현화면
-----------
![1](https://user-images.githubusercontent.com/88135219/147864552-d96ee0ae-fced-4330-88df-56e4621b2e31.png) <br>
![2](https://user-images.githubusercontent.com/88135219/147864555-ebad4ff5-49fd-4628-a7dc-a0a54f0ba70c.png)

--------	
### ✨ 사용언어 및 개발도구
--------
- `java` `Oracle 11g` `JDBC` `Eclipse` `SqlDeveloper`
----
### 📊 개발일정
----
![plan](https://user-images.githubusercontent.com/88135219/147864629-eae8221d-db61-4613-b720-92970f2671e1.jpg)

----
### 📊 DB Table
----
![db](https://user-images.githubusercontent.com/88135219/147864648-cf0b37ea-20f5-41c2-9d68-6d5fe5808dae.png)
----
- 회원테이블의 id를 `PK`로 설정하여 `양주`, `장바구니`, `주문`, `리뷰`, `쿠폰함` 테이블에 `FK`로 설정해주었다.
- `리뷰`, `양주`, `장바구니`, `주문`, `쿠폰` 테이블의 각 no에 `시퀀스`를 설정해주었다.

----
### ✨ 내가 맡은 부분
----
- `장바구니` → `상품 담기` `장바구니 조회` `장바구니 비우기`
- `리뷰`→  `등록` `수정` `삭제` `회원별 리뷰 조회` `양주별 리뷰 조회`
----
### 📃 장바구니 구현 화면
----
![cart](https://user-images.githubusercontent.com/88135219/147865090-38bef048-dec2-4b0f-ab44-c03ccc5db4f8.png)
----
### ✍ 리뷰 구현 화면
----
#### 1. `회원별` `양주별` 리뷰 조회
![review1](https://user-images.githubusercontent.com/88135219/147865127-5325396b-5d80-44c8-b4b3-af0171cdd40d.png) <br>
#### 1. `관리자` 리뷰 관리
![review2](https://user-images.githubusercontent.com/88135219/147865154-d9f19f3f-7a47-49c7-bdc4-e1af9a9ee355.png)

----
### ✔ 프로젝트 리뷰 및 개선방향
----
- MVC구조를 이해하고 구현하기 위해서는 기초가 탄탄하게 잡혀있어야할 필요가 있다.
- 리뷰에 댓글기능 추가하기
