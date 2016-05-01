package robot;

import robot.subsystems.Drive;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	public static Drive drive = new Drive();

	/**
	 * This method is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		DriverStation.buttonInit();
	}

}
