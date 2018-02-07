# Vehicle Simulation

Vehicle Simulation is a simple real time vehicle simulator editor to enable to user: 
- to add a vehicle
- to manipulate the direction and speed of the vehicle
- to remove a vehicle

# Pre-requests
- download the git
- download the maven

# Pull and run the code 
- git pull https://github.com/burakfahri/vehiclesimulation.git
- mvn clean install
- cd main
- mvn exec:java

# Using Vehicle Simulation
  * Add a vehicle : "add coordinate(point) direction(vector)" - "add (0,0) ((0,0),(1,1))"  command,
  * List vehicles : "list" ,
  * Remove a vehicle : "remove id(int) " - "remove 1" ,
  * Change a vehicle direction "change_direction id(int) vector" - "change_direction 1 ((0,0),(1,0))
