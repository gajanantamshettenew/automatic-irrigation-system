## Automatic irrigation system

As a irrigation system which helps the automatic irrigation of agricultural lands without human intervention, system has to
be designed to fulfil the requirement of maintaining and configuring the plots of land by the irrigation time slots and the
amount of water required for each irrigation period.
The irrigation system should have integration interface with a sensor device to direct letting the sensor to irrigate based on
the configured time slots/amount of water.

### Technology Used

- java11
- spring-boot2.7.3
- postgres13.1-alpine
- jacoco-plugin0.8.5
- docker
- testcontainers1.16.2

### Go to the project root directory and follow the instructions below to build & run the application

- Run the below command to build the application & generate jacoco report (which includes code-coverage details). The Path for the jacoco report is: `reports/jacoco/index.html`
  > mvn clean install -U
- Install or Setup docker on your machine & verify the same using below command
  > docker --version
- Make sure docker-deamon is running-up prior
  - ###### Window:
    > wsl -l -v
  - ###### Linux/Mac:
    > sudo service docker status
- Run the below commands in the terminal to build, start & stop all the services
    - ###### Build:
      > docker build -t automatic-irrigation-system .
    - ###### Run:
      > docker-compose up
    - ###### Stop:
      > docker-compose down

### Please use the below APIs details to test the application once services are up & running
- Add new plot of land
  - URI: http://localhost:8080/api/plot
  - HTTP-Method: POST
  - Request:
    - {
      "soilType": "CLAY",
      "length": 101,
      "width": 246,
      "latitude": 1000.11,
      "longitude": 1000.12,
      "waterRequired": 61.3
      }
- Edit a plot of land
  - URI: http://localhost:8080/api/plot
  - HTTP-Method: PUT
  - Request:
    - {
      "plotId": "a90c426f-2a4a-4099-8646-7a0e6c8ea02a",
      "soilType": "CLAY",
      "length": 101.0,
      "width": 246.0,
      "latitude": 1000.11,
      "longitude": 1000.12,
      "waterRequired": 61.3
      }
- Configure a plot of land
  - URI: http://localhost:8080/api/plot/{plotId}
  - HTTP-Method: PATCH
  - Request:
    - {
      "soilType": "SILT"
      }
- List all plots and it's details
  - URI: http://localhost:8080/api/plots
  - HTTP-Method: GET
  - Response:
    - [
      {
      "plotId": "c3e409e9-1cd4-4a2e-8c12-9df1e2765d68",
      "soilType": "SILT",
      "length": 100.0,
      "width": 245.0,
      "latitude": 1011.11,
      "longitude": 1011.12,
      "waterRequired": 60.3,
      "unit": "ltr"
      },
      {
      "plotId": "a90c426f-2a4a-4099-8646-7a0e6c8ea02a",
      "soilType": "LOAMY",
      "length": 102.0,
      "width": 247.0,
      "latitude": 1001.11,
      "longitude": 1001.12,
      "waterRequired": 62.3,
      "unit": "ltr"
      }
      ]