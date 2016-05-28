package robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import robot.commands.ManualDrive;
import robot.commands.TurnLeft;
import robot.commands.TurnRight;
import robot.commands.DriveStraight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class DriverStation {
	public static Joystick joystick = new Joystick(0);
	public static Button joystickB1 = new JoystickButton(joystick, 1);
	public static Button joystickB3 = new JoystickButton(joystick, 3);
	public static Button joystickB4 = new JoystickButton(joystick, 4);

	public static void buttonInit() {
		// associate a command to the button
		joystickB1.whenPressed(new DriveStraight());
		joystickB1.whenReleased(new ManualDrive());
		joystickB3.whenPressed(new TurnLeft());
		joystickB3.whenReleased(new ManualDrive());
		joystickB4.whenPressed(new TurnRight());
		joystickB4.whenReleased(new ManualDrive());
	}

}
