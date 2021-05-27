# Football Management Application

### Project Structure
1. standings-backend [Backend|SpringBoot2.5.2|Java11|Gradle]

### Steps to compile and test
```sh
$ cd standings-backend
$ ./gradlew clean build
```
- This will compile, build and run the unit test, and controller tests.
- Gradle wrapper is used hence system without gradle will be able to run this apploication
- Code coverage of the service and controller is 100% . Checked it with IntelliJ code coverage functionality

### Steps to run the application
```sh
$ cd standings-backend
$ ./run.sh
```
- This will build the Dockerfile and run the spring boot container on ***port 8080***
  *http://localhost:8080/standing?countryName=England&leagueName=Non%20League%20Premier&teamName=Folkestone%20Invicta*

#### Sample Response
```json
{
    "success": true,
    "data": {
        "countryId": 44,
        "countryName": "England",
        "leagueId": 149,
        "leagueName": "Non League Premier",
        "teamId": 3033,
        "teamName": "Folkestone Invicta",
        "overallLeaguePosition": 10
    }
}
```
### Approaches/Patterns/OtherDetails used
1. Domain Driven Design of backend application
2. Hexagonal Architecture Project Structure [Ports and Adapters Pattern]
3. Spring boot controller tests
4. Mockito for unit tests

Please do reach out to me in case something does not work.Thanks
#### Author
##### [Ajay Singh]
[Ajay Singh]: <https://www.linkedin.com/in/ajaygahlot/>
