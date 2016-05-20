package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;

public class ConstantHeadingDrive extends Command {

	private Drive drive = Robot.drive;
	private double heading;

	public ConstantHeadingDrive() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		heading = RobotMap.ahrs.getAngle();
		drive.driveOnHeadingInit(heading, 1);
	}

	@Override
	protected void execute() {
		double power = -DriverStation.joystick.getY(); // forward is negative y
														// on the joystick
		drive.driveOnHeading(power);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drive.driveOnHeadingEnd();
	}

	@Override
	protected void interrupted() {
		SmartDashboard.putString("Interrupted", "true");
		end();
	}

}
