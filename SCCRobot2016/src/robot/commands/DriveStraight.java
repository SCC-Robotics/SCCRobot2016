package robot.commands;

import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.ContinuousGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that drives the robot along a straight line given by a heading angle
 *
 */
public class DriveStraight extends Command {

	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double heading;
	private double yMotion = 1;

	public DriveStraight() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		if (RobotMap.prefs.getBoolean("Test heading", false)) {
			heading = RobotMap.prefs.getDouble("Heading", 0);
			// make the robot turn on the spot for testing (no forward or
			// backward motion)
			yMotion = 0;
		} else {
			// getAngle returns a continuous angle as does pidGet in ContinousGyro
			heading = gyro.getAngle();
		}
		drive.driveOnHeadingInit(heading);
	}

	@Override
	protected void execute() {
		// the negative sign is because forward is -y on the joystick
		// yMotion is 0 for testing and 0 otherwise
		double power = -yMotion * DriverStation.joystick.getY();
		drive.driveOnHeading(power);
	}

	@Override
	protected boolean isFinished() {
		// Goes on until interrupted
		return false;
	}

	@Override
	protected void end() {
		drive.driveOnHeadingEnd();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
