@echo off
start cmd /k mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=chrome
start cmd /k mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=firefox
start cmd /k mvn clean verify -Dtest=myrunner.SuiteRunnerTest -Dbrowser=edge