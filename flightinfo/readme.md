# Virgin Holidays Flight Information Display

### Requirement

* The application should allow the user to select or input any date, of any year, resulting in the display of flights on that day, displayed in chronological order -- a Flight Information Display.
* The interface can be web-based, command-line or otherwise.



### Running the Application(Using Command Line)

* Navigate to the application root.
* Navigate to /build/libs
* Execute the command java -jar .\flightinfo-0.0.1-SNAPSHOT.jar
* In browser, open the link http://localhost:8080
* Select the desired date, and the application should get the flights on given week of the day.

### Running the Application(Using gradle run)
* Navigate to the application root.
* Open the terminal at application root.
* Run the command  ./gradlew bootrun.
* In browser, open the link http://localhost:8080
* Select the desired date, and the application should get the flights on given week of the day.

![image.png](image.png)

### Functioning of the Application

* Application is designed with Java spring boot REST api which exposes endpoint to fetch flights details.
* There is simple index.html java-script file which allows user to enter date and also display list of files.
* A flights.csv file will always be loaded whenever application starts into Singleton bean hashmap.
* Hashmap will store day wise list of Flights after parsing the csv file.
* Open CSV library has been used to parse csv file onto java objects.
* FetchFlightInfoController class sis controller which publish REST GET endpoint/api,
* FlightScheduleImpl service class for fetching list of flights as per date provided. 