# 특정 고객 거래내역 조회 서비스 개발

## 개발 프레임워크
  - Spring Boot 2.2.6
  - Java 11
  - Spring Data JPA 2.2.6
  - Querydsl 4.2.1
  - H2 1.4.2
  - Gradle 6.3
  - GitHub
  
## 문제 해결 방법
### (공통): 
    - Querydsl을 활용해 H2에서 데이터 추출
    - 과도한 서브 쿼리 지양 -> 어플리케이션에서 처리하여 성능 향상
    - 1차적으로 추출한 데이터를 Service에서 처리(Stream 사용하여 처리)

### 1. 2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API 개발.(단, 취소여부가 ‘Y’ 거래는 취소된 거래임, 합계 금액은 거래금액에서 수수료를 차감한 금액임)
    - 1차적으로 H2에서 추출: join을 활용하여 필요한 데이터 추출
    - Stream을 활용(filter, collect, forEach 등) -> 2018년, 2019년 각각 거래금액 합이 가장 큰 개인의 정보를 maxMap에 저장
    - 계좌명은 계좌번호로 조회하여 최종 리턴 객체에 저장 및 리스트에 추가
    - api 호출: http://localhost/transaction/sum/amount/max

### 2. 2018년 또는 2019년에 거래가 없는 고객을 추출하는 API 개발.(취소여부가 ‘Y’ 거래는 취소된 거래임)
    - 거래가 없는 고객을 추출해야 하므로, 거래내역 데이터에서 거래가 발생한 계좌 리스트를 추출
    - 거래가 발생한 계좌번호 리스트를 파라미터로 하여, 계좌정보 데이터에서 'NotIn' 메서드를 활용해 거래 내역이 없는 계좌 정보 추출
    - 2018년, 2019년의 거래내역이 없는 고객 정보를 최종 리턴할 객체에 저장 및 리스트에 추가
    - api 호출: http://localhost/transaction/empty/customer

### 3. 연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력하는 API 개발.( 취소여부가 ‘Y’ 거래는 취소된 거래임)
    - 리턴할 객체의 형식을 보고 vo를 구성
    - 세개의 테이블을 join, 관리점 코드로 group 등 querydsl 활용하여 필요한 데이터 추출
    - 각 연도별로 관리점 리스트를 최종 리턴할 객체에 추가
    - api 호출: http://localhost/transaction/sum/branch/year

### 4. 분당점과 판교점을 통폐합하여 판교점으로 관리점 이관을 하였습니다. 지점명을 입력하면 해당지점의 거래금액 합계를 출력하는 API 개발( 취소여부가 ‘Y’ 거래는 취소된 거래임)
    - POST방식으로 요청 받아 처리하는 api
    - @RequestBody를 활용해 파라미터 처리
    - @ResponseEntity 활용해 리턴
    - 관리점명이 분당점일 경우, 에러코드와 메세지를 리턴하도록 분기 처리 로직 구현
    - 다른 관리점일 경우 필요한 관리점별 거래금액 합계를 추출하여 리턴
    - postman을 활용하여 테스트
    - api 호출: http://localhost/transaction/sum/branch/name 
    - POST방식으로 요청하면 결과 확인 가능
    - JSON 형식의 파라미터 필요: {"brName":"관리점명"}

## 빌드 및 실행방법
  - https://github.com/swswbrbr/transactionapi 에서 Repository를 Clone
  - (이클립스 사용)import - Projects from Git에서 복사한 URI를 입력
  - Gradle 빌드 실행
  - Application.java 파일 실행
  
