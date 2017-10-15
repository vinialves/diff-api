# diff-api

This is a REST API to perform diff operations.

## The assignment

Provide 2 http endpoints that accepts JSON base64 encoded binary data on both
endpoints
	<host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
The provided data needs to be diff-ed and the results shall be available on a third end
point
	<host>/v1/diff/<ID>
The results shall provide the following info in JSON format
	If equal return that
	If not of equal size just return that
	If of same size provide insight in where the diffs are, actual diffs are not needed.
		So mainly offsets + length in the data
		
## Running

1) Use Maven in order to package the application:
```
mvn package
```

2) Run the application using java command:
```
java -jar target\diff-api-1.0.jar
```
## Testing

1) The unit tests will be executed during the packaging. 
Also, these tests can be executed at any time with the Maven command:
```
mvn test
```
2) It was used the tool Postman to perform manual tests and validate the endpoints.

Example 1 - data is not equal, but data size is equal:

=== Add left data === 
POST request: http://localhost:8081/v1/diff/123/left
JSON data in the request body:
{
	"base64Data" : "dmluaWNpdXM="
}

=== Add right data === 
POST request: http://localhost:8081/v1/diff/123/right
JSON data in the request body:
{
	"base64Data" : "aXNhYmVsbGU="
}

=== Get the diff ===
GET request: http://localhost:8081/v1/diff/123
JSON data in the response:
{
    "id": "123",
    "equals": false,
    "equalSize": true,
    "differences": [
        {
            "byteIndex": 0,
            "numberOfDifferentBits": 5
        },
        {
            "byteIndex": 1,
            "numberOfDifferentBits": 3
        },
        {
            "byteIndex": 2,
            "numberOfDifferentBits": 4
        },
        {
            "byteIndex": 3,
            "numberOfDifferentBits": 3
        },
        {
            "byteIndex": 4,
            "numberOfDifferentBits": 2
        },
        {
            "byteIndex": 5,
            "numberOfDifferentBits": 2
        },
        {
            "byteIndex": 6,
            "numberOfDifferentBits": 3
        },
        {
            "byteIndex": 7,
            "numberOfDifferentBits": 3
        }
    ]
}

Example 2 - data is equal:

=== Add left data === 
POST request: http://localhost:8081/v1/diff/123/left
JSON data in the request body:
{
	"base64Data" : "dmluaWNpdXM="
}

=== Add right data === 
POST request: http://localhost:8081/v1/diff/123/right
JSON data in the request body:
{
	"base64Data" : "dmluaWNpdXM="
}

=== Get the diff ===
GET request: http://localhost:8081/v1/diff/123
JSON data in the response:
{
    "id": "123",
    "equals": true,
    "equalSize": true,
    "differences": []
}

Example 3 - data is not equal and data size is not equal:

=== Add left data === 
POST request: http://localhost:8081/v1/diff/123/left
JSON data in the request body:
{
	"base64Data" : "dmluaWNpdXM="
}

=== Add right data === 
POST request: http://localhost:8081/v1/diff/123/right
JSON data in the request body:
{
	"base64Data" : "YW5hbmRh"
}

=== Get the diff ===
GET request: http://localhost:8081/v1/diff/123
JSON data in the response:
{
    "id": "123",
    "equals": false,
    "equalSize": false,
    "differences": []
}

## Some assumptions

1) For this initial version, scalability is not the major requirement,
so data persistence also is not a requirement. The application keeps the diffs in memory. 
If data persistence becomes a requirement, the data can  be easily persisted in any desired database.

Thinking about scalability, if the use of the application grows, 
probably is a good idea to have the data persisted and return the id
of request in order to track the diff operation. 
Design patterns like Factory could be  used in order to create the tasks 
for different operations like addLeft, addRight, getDiff among others. 
See below (in the possible improvements) more ideas related to scalability.

## Possible Improvements

1) Improve the error handling. For example, creating specific 
errors/exceptions for cases like trying to get a non existing 
diff or a diff with incomplete data (missing left or right).

2)Improve the insight in where the diffs are, maybe providing 
more information than just the byte index and the number of different bits.

3) Data persistence (if it is a requirement).

4) If scalability becomes a requirement, this application was made using spring boot
which is powerfull to build microservices. The microservices architecture can be considered, 
for example, using Docker to manage the packaging of the microservice in a container
and Kubernetes in order to have load balancing and scaling.

5) Add more logs.

## Code coverage

The code coverage is 98%. 
The branch coverage is 100%.
These metrics can be verified after running the unit test with Maven:
```
mvn test
```
Verify the report:
```
target\jacoco-ut\index.html
```

## Logs

It was used log4j2 in the application in order to generate logs.
Initially only two log files were configurated in the log4j2.xml:
diff_api.log and springboot.log.
The log level for these respective logs can also be configured in the log4j2.xml.
The default log level for both logs is INFO.
These logs are generated in the logs folder.