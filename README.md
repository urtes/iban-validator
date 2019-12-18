# IBAN Validator

IBAN Validator is an application that validates International Bank Account Numbers (IBAN).
Application is available in two versions. Version v1.0 is a command line application. Version v2.0 is a web application.

Application is developed in Java, using Maven and Spring Boot (for web version).

## Installation

To run the command line application download cli-app-1.0-SNAPSHOT.jar file from Releases (v1.0).
To run the web application download web-app-1.0-SNAPSHOT.jar file from Releases (v2.0).

## Usage

#### Command line version
Command line version takes two command line arguments: first one - functioning mode, second one - data to validate.
Valid values for first argument are `1` or `2`.
If `1` is entered, then second argument is expected to be IBAN to be validated. Validation result will be printed to
console.
If `2` is entered, then second argument is expected to be a file with a full path, containing a list of IBANs to
validate. Validation result will be outputed to a new file with same path and name as input file, it's extension will be
`.out`.
To start the application go to the directory where cli-app-1.0-SNAPSHOT.jar file is located and enter `java -jar cli
-app-1.0-SNAPSHOT.jar` followed by command line arguments.

#### Web version
To start the application go to the directory where cli-app-1.0-SNAPSHOT.jar file is located and enter `java -jar web
-app-1.0-SNAPSHOT.jar`. When starting, Tomcat will start on port 8080 by default. Post a list of IBANs to validate to
 http://localhost:8080/validate. Response will be IBANs with validation info.