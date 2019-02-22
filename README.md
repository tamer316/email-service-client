Email Service Client
====================
Application built with the following (main) technologies:

* Scala
* SBT
* Specs2
* Play WS

Description
-----------
A Scala Client for the Email Service to send emails

Prerequisites
-------------
The following applications are installed and running:

* [Scala 2.11.8](http://www.scala-lang.org/)
* [SBT](http://www.scala-sbt.org/)
    - For Mac:
      ```
      brew install sbt
      ```

Publishing
-------
- Publish to your local repository
  ```
  sbt publish-local
  ```
  
Testing
---------
- Run Unit tests
  ```
  sbt test
  ```
  
- Run one test
  ```
  sbt test-only *EmailClientSpec
  ```

Code Coverage
-------------
SBT-scoverage a SBT auto plugin: https://github.com/scoverage/sbt-scoverage
- Run tests with coverage enabled by entering:
  ```
  sbt clean coverage test
  ```

After the tests have finished, find the coverage reports inside target/scala-2.11/scoverage-report