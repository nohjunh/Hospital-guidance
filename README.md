----------------------------------------------
#Java 7Days Short_Term_Project

자바 7일 단기 텀 프로젝트

-----------------------------------------------
Project title : 복통잡아

단기 프로젝트로 인한 통증 부위 한정 ->

복부 통증 부위에 따른 예상 질환을 안내

예상 질환을 진료할 수 있는 지역 병원과 전문 병원 안내

------------------------------------------------
진료 병원 확인 -> Kakao Local API 사용 -> 예상 질환 치료 가능한 지역 병원 추출

추천 병원 확인 -> 병원 평점 취합 thread 생성 -> (Asynchronous multi-threading) thread 별로 data crawling 동시에 진행 -> 예상 질환 치료 가능한 지역 병원 중 상위 인기 병원 추출

Option1 -> 해당 증상 진료 병원 table 구성 (병원명, 전화번호, 위치)

Option2 -> 상위 인기 병원 table 구성(동일)
