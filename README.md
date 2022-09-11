# 스프링부트 게시판
간단하게 게시글을 작성가능하고 댓글까지 작성할수있도록 만든 게시판입니다.
대부분의 내용은 [Let's Develop Spring Boot
스프링 부트(Spring Boot) 게시판](https://congsong.tistory.com/) 참고하여 개발하였습니다.

# Description
- 개발 기간:1주
- 핵심 사용 기술:<br/>
  📕spring boot 4.0
  
  📕DB:MySQL,Mybatis
  
  📕connection pool:hikari
  
  📕템플릿 엔진:Thymeleafd
  
  
  # Views

- **게시글 리스트 화면** URL=>localhost:8080/board/list.do
![main](https://user-images.githubusercontent.com/40134318/146670536-1ca1fde0-1eb9-4fae-8523-44cb833dece8.gif)
 
- **상세보기 화면** URL=>localhost:8080/board/view.do?페이지정보쿼리
![view1](https://user-images.githubusercontent.com/40134318/146670610-651ce937-4458-4445-83f8-65827252f0da.gif)
![view2](https://user-images.githubusercontent.com/40134318/146670624-f577754d-d1c5-4748-a3b4-40ba49318fda.gif)

- **게시글 작성 화면** URL=>localhost:8080/board/write.do
![write](https://user-images.githubusercontent.com/40134318/146670628-b3bd41e1-1d3e-41ed-8ed9-f3996f84a4af.gif)
