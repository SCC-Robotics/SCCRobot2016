package robot.commands;

import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.ContinuousGyro;
import edu.wpi.first.wpilibj.command.Command;

public class TurnAngle extends Command {
	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double tolerance, angle;
	private double finalHeading;

	public TurnAngle(double angle, double tolerance) {
		requires(drive);
		this.tolerance = tolerance;
		this.angle = angle;
	}

	@Override
	protected void initialize() {
		finalHeading = gyro.getAngle() + angle;
		drive.driveOnHeadingInit(finalHeading);
	}

	@Override
	protected void execute() {
		double power = -DriverStation.joystick.getY();
		drive.driveOnHeading(power);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(gyro.getAngle() - finalHeading) <= tolerance;
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
