# Robot code
The code is divided into several packages

robot package: contains three classes:

_ RobotMap: maps program variables to the sensors and actuators of the robot

_ DriverStation: binds the controls on the physical operator to the commands and command groups that allow control of the robot

_ Robot: initializes and runs all of the subsystems of the robot



subsytems package:

_ Drive: represents the driving train of the robot (code that commands the motors connected to the wheels)



commands package: contains all of the commands that we write for our robot (typically commands are bound to a button on the joystick)

_ ManualStraightDrive: moves the robot forward 

