# 오롯! Oh Lot!

## 설명
Ohlot 프로젝트는 템플릿에 기능을 확장 후 새로운 템플릿으로 제공하는 **템플릿 연계 프로젝트**입니다. 

**오롯**
> 오롯은 한국의 순 우리말로써, "부족함 없이 온전하다"라는 뜻을 갖고 있습니다.      
> Ohlot 프로젝트는 예시를 제공함에 있어 부족함 없도록하고 온전히 견해를 주고 받을 수 있는 것을 목표로합니다.

**Ohlot이 지키고자 하는 규칙은 다음과 같습니다.**
- 하나의 템플릿에 한 가지 도메인 구현을 목표합니다.
- 이해할 수 있는 네이밍 컨벤션을 지키고자 합니다.


## 📄 Spec

| Name | Desc                                                                                                                                                                                                             |
|:---|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 상위 템플릿 | -                                                                                                                                                                                                                |
| Framework | <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">                                                                                                 |
| Language | <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">                                                                                                               |
| Architecture | <img src="https://img.shields.io/badge/Monolihic-3178C6?style=for-the-badge&logo=java&logoColor=white"> |

## Content Guide
Ohlot! 확장에 가장 기본이 되는 Repository 입니다.
즉 특정 요구사항을 해결하기 위한 비즈니스 로직 구현을 최대한 피했으며, 간소화된 내용으로 구성되어 있습니다.
- 예시) 사용자의 패스워드 암호화 없음., 인증 인가 방식 간소화, 허술한 예외처리 등

### 해당 패키지에서 보이고자 하는 것
해당 아키텍쳐에 DDD, TDD, SOLID 고려해서 개발하는 방법을 보이고자 했습니다.
허술한 부분, 알맞지 않다 생각하는 의견이 있다면 Issue로 이야기 해볼 기회가 있으면 합니다.

해당 프로젝트에서 처리하지 않은 특정 요구사항들은 **ohlot-architecture-template**을 사용한 새로운 tempalte에서 설명할 것입니다.

해당 내용의 기능 제공은 다음과 같습니다.

### Account

**회원가입**
**로그인**

### User
**회원 목록 조회**
**회원 조회**
**회원 정보 업데이트**

## Build Guide

### Unit Test

```shell
./gradlew test
```

### HTTP Client Test (Intellij)
<img width="383" src="https://github.com/ranadas/sboot-jwt/assets/22608825/20e9ade8-3dcd-43a7-bb7d-d50556c5b6c0">
