# IBAN Validator

IBAN Validator is a project for validating International Bank Account Numbers (IBAN). It is is available as a command
 line application and as a web application.

The project is developed in Java, using Maven and Spring Boot (for web version).

## Build

From parent run `mvn clean install`, then from cli-app run `mvn clean compile assembly:single`. There are no additional steps for building web-app.

## Installation

To run the command line application download cli-app-1.0-RELEASE.jar file from Releases.

To run the web application download web-app-1.0-RELEASE.jar file from Releases.

## Usage

#### Command line application
Command line application takes two command line arguments: first one - mode, second one - data to validate.
Valid values for first argument are `1` or `2`.

If `1` is entered, then second argument is expected to be IBAN to be validated. Validation result will be printed to
console.

If `2` is entered, then second argument is expected to be a file with a full path, containing a list of IBANs to
validate. Input file has to have extension. Validation result will be written into to a new file with same path and name as input file, it's extension
 will be `.out`.
 
To start the application go to the directory where cli-app-1.0-RELEASE.jar file is located and execute `java -jar cli
-app-1.0-RELEASE.jar` followed by command line arguments.

#### Web application
To start the application go to the directory where web-app-1.0-RELEASE.jar file is located and execute `java -jar web
-app-1.0-RELEASE.jar`. When starting, Tomcat will start on port 8080 by default. Post a JSON array, containing IBANs
to validate. Response will be JSON array, containing IBANs with validation info.

Sample JSON request:
```
[
 	"thisIsInvalidIBAN!",
 	"AL35202111090000000001234567"
]
```
Sample JSON response:
```
[
    {
        "iban": "thisIsInvalidIBAN!",
        "valid": false
    },
    {
        "iban": "AL35202111090000000001234567",
        "valid": true
    }
]
```

