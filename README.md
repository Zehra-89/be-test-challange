## FreeNow - Backend Test Challenge

BaseURI:  https://jsonplaceholder.typicode.com/

✏️ **Task**

The task is to perform the validations for the comments for the post made by a specific
user named “Samantha” using the tools specified

* Search for the user.
* Use the details fetched to make a search for the posts written by the user.
* For each post, fetch the comments and validate if the emails in the comment
section are in the proper format.
* Think of various scenarios for the test workflow, all the things that can go wrong.
* Upload your test to Github
* Execute the tests successfully on Circle CI.


## Tech Stack
* Java 11
* Maven
* RestAssure
* TestNG
* [CircleCI](https://app.circleci.com/pipelines/github/Zehra-89/be-test-challenge)
* [Allure Report](https://output.circle-artifacts.com/output/job/a5d4f7b1-0316-42a2-a26c-9d359b8e9855/artifacts/0/allure-report/index.html)


## Setup Environment
```bash
brew install allure
```

## Test Execution 
_Type the following commands in the terminal_

```bash
mvn clean test -Denvironment=test 
```
#### Generate Allure Report Locally 
```bash
allure serve allure-results 
```
## Allure Report
Allure Report is a flexible multi-language test report tool to show you a detailed representation of what has been tested.

### Known Issues 
CircleCi Parallelism 



