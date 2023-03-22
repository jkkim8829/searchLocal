# searchLocal

사용방법(cURL)
메인 searchLocal.jar 실행
java -jar searchLocal.jar
1. 장소검색
cURL http://localhost:8080/search?query=검색어
2. 검색키워드 목록
cURL http://localhost:8080/popularSearch?date=3

개발사항
  - 멀티모듈 사용
  - 저장소 h2 InmemoryDB사용
  - Java11 / SpringBoot / Gradle사용
  - 설계를 통한 동시성 이슈 처리(키워드 검색 날짜 추가)
  - 대용량 트래픽을 위한 WebClient사용(비동기 처리)
  - 장소 비교방식 장소명칭, 도로명 주소 비교 처리

명세서
1. 장소 검색 서비스(API사용)
  - URI : /search
  - 방식 : get
  - request : query="String" 필수값
  - respones : Json형식
               places : 장소그룹
               place_name : 장소이름
               road_address_name : 도로명 주소
               address_name : 지번주소
               phone : 전화번호

2. 검색 키워드 목록
  - URI : popularSearch
  - 방식 : get
  - request : date=integer (지난 시간검색으로 정해진 숫자 전까지 수집된 데이터만 숫자로 가지고온다)
  - respones : Json형식
               searchWord : 검색키워드
               searchCount : 검색 횟수

