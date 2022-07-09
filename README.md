# Groove Backend (Java Spring)
* using gradle multi module with kotlin DSL

## Modules
### Storage
* JPA 기능만을 투영하며 실제 DB를 읽고 쓰는 주체
* 구체적인 로직은 상위 레이어에 작성

### Common
* 다른 모듈에서 공통적으로 사용될 수 있는 것들

### Domain (+ endpoint)
* domain과 endpoint는 나누는 것이 좋을 지 고민

## Java Linter
* using checkstyle plugin
* using google style configuration
  ```
    {projectRoot}/config/checkstyle.xml
  ```

### How to fix violations easily
* install Checkstyle IDEA plugin
* Preferences &rarr; editor &rarr; code style &rarr; Java &rarr; import the checkstyle configuration
* then Press <kbd>⌘ + ⌥ + L</kbd> or <kbd>Ctrl + Alt + L</kbd>