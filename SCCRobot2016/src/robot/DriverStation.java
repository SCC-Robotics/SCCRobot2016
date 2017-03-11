package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import robot.commands.DriveStraight;
import robot.commands.DriveStraightDistance;
import robot.commands.ManualDrive;
import robot.commands.ToggleCameraLight;
import robot.commands.TurnAngle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class DriverStation {
	public static Joystick gamePad = new Joystick(0);
	public static Joystick joystick = new Joystick(1);
	public static Button joystickB1 = new JoystickButton(joystick, 1);
	public static Button joystickB2 = new JoystickButton(joystick, 2);
	public static Button joystickB3 = new JoystickButton(joystick, 3);
	public static Button joystickB4 = new JoystickButton(joystick, 4);
	public static Button gamePadRB = new JoystickButton(gamePad, 6);

	public static void buttonInit() {
		// associate a command to the button
		joystickB1.whenPressed(new DriveStraight());
		joystickB1.whenReleased(new ManualDrive());
		
		// turn by 90 degrees to the left within .5 degrees
		joystickB3.whenPressed(new TurnAngle(-90, .5));
		joystickB3.whenReleased(new ManualDrive());
		
		// turn by 90 degrees to the right within .5 degrees
		joystickB4.whenPressed(new TurnAngle(90, 0.5));
		joystickB4.whenReleased(new ManualDrive());
		
		// Toggle the camera light.
		joystickB2.whenPressed(new ToggleCameraLight());
		
		// Drive straight one meter
		gamePadRB.whenPressed(new DriveStraightDistance());
	}

}
