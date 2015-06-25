Example Spring Boot Security
============================

스프링Boot 환경에서 Spring Security를 사용하는 예제를 작성하면서 공부하는 것이 목적.

* Customized login form
* DAO-based authentication
* Basic "remember me" authentication
* URL-based security
* Method-level security

jdk 1.8로 개발된 것아래의 프로젝트를 JDK 7으로 변형하고 수정하는 것이 목적.

See the [Spring Boot Security Application](http://kielczewski.eu/2014/12/spring-boot-security-application/) article for
commentary.

Menu 와 권한 그리고 사용자를 연결한 권한 체크 로직을 넣으려고 시도 - 실패
문제	
Spring Framework restful-api 에 대한 체크를 하기 힘듬.
=> Spring Security 의 Config 설정부분에 권한과 url 패턴을 맵핑 메뉴 추가시 설정을 추가하는 방식으로 해결 ~! 
   Database에서 메뉴와 권한은 관계가 나타나지 않도록 분리할 계획.    

Requirements
------------
* [Java Platform (JDK) 8 를 7에 맞게 변경](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

Quick start
-----------
1. `mvn spring-boot:run`
2. Point your browser to [http://localhost:8080/](http://localhost:8080/)

Issue List
-----------
- [x] 관리자 계정이 없는 경우 자동생성기능 [#2](/../../issues/2) (2015-05-27)
