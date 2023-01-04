* run all tests:
```
mvn test
```

* run the rest tests:
```
 mvn test -Dcucumber.filter.tags="@restTest"
```

* run the appium tests:
```
 run: ./script.sh 
 or
 run:  mvn test -Dcucumber.filter.tags="@appiumTest" 
```

* test report outputs:
```
xml report  : target/junitreport.xml
json report : target/jsonreport.json
html report : target/cucumber-reports
```

* paths of test cases:
```
- src/test/resources/features/appium/appiumTestCases.feature
- src/test/resources/features/rest/restCases.feature
```