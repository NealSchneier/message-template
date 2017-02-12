# message-template
Legal Document Template Based Message Workflow

### Assumptions and Basic Thoughts on Design

1 - The REST service use is for both Configuration fo the Templates (CRUD commands) AND as a service to see the legal messages.

2 - The Template stores only the predefined message and the placeholders. 

3 - As a consumer of this Rest service the predefined fields are provided on the request.
    
    - The possible values are: URLs, emails, phone numbers, or names.
    
4 - This service is used by internal customers only.

5 - In the real worl this would be backed by a database instead of storing just in memory. The database would depend on the non functional requirements and expected usage of the service.

### Requirements

*Java 8

*Scala 2.11

*SBT

### To Run

Launch the Server

*sbt run

To run Tests

*sbt test