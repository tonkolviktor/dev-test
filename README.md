Java Developer Test
=============

# Obtain jar file

## 1. Without build

Download jar from: [dist/GoEuroTest.jar](dist/GoEuroTest.jar)

## 2. Build with maven

clone repository: git clone https://github.com/tonkolviktor/dev-test
run maven package: mvn package
jar file location: target/GoEuroTest.jar

# Run the application

java -jar GoEuroTest.jar Berlin

or

java -jar target/GoEuroTest.jar "Bernau bei Berlin"

The result CSV file will be: ./result.csv

# Run unit tests

mvn test

# Run end-to-end test (it will actually call the backend)

E2E test does not run by default because e2e test actually call the backend. It can be run with the following command. See [pom.xml](pom.xml) for details on configuration.

mvn test -Pwith-e2e-tests

# Documentation

## Used frameworks

* Maven: dependency management, building, jar assembly
* Jersey: JAX-RS Client
* Jackson: JSON Provider
* Apache Commons
* TestNG: test runner for unit and e2e tests
* AssertJ: assertion framework, for more readable test cases
* PODAM: POjo DAta Mocker

## Directory layout

pom.xml: maven pom file with dependencies, unit test and e2e test configuration

### Main files

java/devtest/Application: Main class, error handling only here, main controller: 1. create components, 2. call backend, 3. export results
java/devtest/Argument: class to handle command line arguments
java/devtest/BusinessException: exception class used in the application
java/devtest/Configuration: default configuration values (csv column separator, backend base url, ...)
java/devtest/export/CsvExport: class to write cities to csv file. To create CSV file in java is not difficult, thus no 3rd party lib was used
java/devtest/backend/BackendService: class to call backend with Jersey/Jackson, and return cities list
java/devtest/backend/City + GeoPosition: POJO-s for representing JSON response data

### Test files

test/devtest/TestUtil: common method and test variables
test/devtest/ArgumentTest: unit tests for Argument class (assert empty args...)
test/devtest/export/CsvExportTest: unit tests for CsvExport class (assert file creation, header and content)
test/devtest/e2e/backend/BackendServiceTest: e2e tests for BackendService (happy day scenario and exceptions)
test/devtest/e2e/ApplicationTest: e2e test for the whole application: (missing argument, file creation & content, ...)
