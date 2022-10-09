# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.4/gradle-plugin/reference/html/#build-image)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#howto.batch)

### Guides

The following guides illustrate how to use some features concretely:

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

문제
- MAC M1 docker 환경에서 oracle 실행 불가

원인
- oracle 자체에서 MAC M1 arm 아키텍쳐를 지원하지 않음
- M1에 설치된 docker도 arm 아키텍쳐 기반 이미지 사용으로 오라클 지원 x

해결
- arm 아키텍쳐를 지원하는 docker runtime 설치 후 도커 이미지 실행

1. colima 설치 
brew install colima

2. colima 실행
colima start --arch x86_64 --memory 4

2-1. docker runtime 확인
docker context ls

3. docker run
docker run -p 8080:8080 -p 1521:1521 jaspeen/oracle-xe-11g

4. SQLDeploper 실행
ID : system
PW : oracle

