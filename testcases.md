#story 1
[X] case 1
    Given a parking lot, and a car
    When park the car
    Then return a parking ticket

[X] case 2
    Given customer has parking ticket
    When fetch the car
    Then return car

[X] case 3
    Given a parking lot with two parked cars, and two parking tickets
    When fetch the car twice
    Then return the right car with each ticket

[X] case 4
    Given customer has a WRONG parking ticket
    When fetch the car
    Then return nothing

[X] case 5
    Given customer has parking ticket but already USED
    When fetch the car
    Then return nothing

[X]case 6
    Given a car, and a parking lot but NO space
    When park the car
    Then do NOT return a parking ticket

#story 2
[X]case 1
    Given parking lot, and an unrecognized ticket
    When fetch the car
    Then display error message "Unrecognized parking ticket"

[X]case 2
    Given parking lot, and a used ticket
    When fetch the car
    Then display error message "Unrecognized parking ticket"

[X]case 3
    Given parking lot, without any position and a car
    When park the car
    Then display error message "No available position"

#story 3

[X]case 1 
    Given a parking lot, a standard parking boy, and a car
    When park the car
    Then return a parking ticket

[X]case 2
    Given a parking lot with a parked card, a standard parking boy, and a parking ticket
    When fetch the car
    Then return the car

[X]case 3
    Given a parking lot with two parked cars, a standard parking boy, and two parking tickets
    When fetch the car
    Then return the right car with each ticket

[X]case 4
    Given a parking lot, a standard parking boy, and a wrong parking ticket
    When fetch the car
    Then return nothing with error message "Unrecognized parking ticket."

[X]case 5
    Given a parking lot, a standard parking boy, and a used parking ticket
    When fetch the car
    Then return nothing with error message "Unrecognized parking ticket."

[X]case 6
    Given a parking lot without any position a standard parking boy, and a car
    When park the car
    Then return nothing with error message "No available position."

#story 4
[X]case 1
    Given a standard parking boy, two parking lots and both available, and a car
    When park the car
    Then return ticket and park car to the first parking lot

[X]case 2
    Given a standard parking boy, two parking lots and first is full and second is available, and a car
    When park the car
    Then return ticket and park car to the second parking lot

[X]case 3
    Given a standard parking boy, two parking lots, both with parked car and parking ticket
    When fetch the car twice
    Then return right car with each ticket

[X]case 4
    Given a standard parking boy, two parking lots, unrecognized ticket
    When fetch the car
    Then return nothing with error message "Unrecognized parking ticket."

[X]case 5
    Given a standard parking boy, two parking lots, used ticket
    When fetch the car
    Then return nothing with error message "Unrecognized parking ticket."

[X]case 6
    Given a standard parking boy, two parking lots, no available parking slot for both
    When park the car
    Then return nothing with error message "No available position."
    
