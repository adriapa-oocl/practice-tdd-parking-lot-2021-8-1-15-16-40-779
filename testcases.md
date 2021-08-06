#story 1
[X] case 1
    Given a parking lot, and a car
    When customer do park the car
    Then return a parking ticket

[X] case 2
    Given customer has parking ticket
    When customer do fetch the car
    Then return car

[X] case 3
    Given a parking lot with two parked cars, and two parking tickets
    When fetch the car twice
    Then return the right car with each ticket

[] case 4
    Given customer has a WRONG parking ticket
    When fetch the car
    Then return nothing

[] case 5
    Given customer has parking ticket but already USED
    When customer do fetch the car
    Then return nothing

[]case 6
    Given a car, and a parking lot but NO space
    When customer do park the car
    Then do NOT return a parking ticket






