# Log Task
I've spend 3h on this project, HibernateUtil class, hibernate.properties where reused from my other project and parseJson method is based on this pace of code https://www.baeldung.com/java-read-lines-large-file for reading large files in java

improved version of the project: https://github.com/adrianrogalski/iss-tracker-ext.git
## How to install
Please download the whole project and compile it while remembering to update the maven dependencies. Please note that you must first set argument in the run/debug configurations as an absolute path of your file or use "logfile.txt" which is included into the project and replace its sample data If you wish.
## Functionalities 
In this project I've tried to implement this functionalities:
* Reading file with data based on the first argument name
* Parsing text file into objects using ObjectMapper
* Saving data into the database using Hibernate
* Flaging objects with duration longer than 4ms
* 1 unit test for database connection
## With more time
* I would use indirectional one to one relationship to minimalize null fields
* I would make unit tests for service methods
* I would try to replace implementation of parseJson and eventDuration methods with more functional oriented style and not so impreative and hard to understand
* More user friendly exceptions handling
## Build With
* Java
* Maven
* Jackson
* Lombok
* Hibernate
* HSQLDB
