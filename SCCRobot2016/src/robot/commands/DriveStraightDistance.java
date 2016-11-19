package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.ContinuousGyro;

public class DriveStraightDistance extends Command {
	private double distance;
	private int startEncoder;
	private int endEncoder;
	private boolean forward;
	private double heading;
	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double power;

	public DriveStraightDistance(double distance, double power) {
		this.forward = (power >= 0);
		this.distance = distance;
		this.power = power;
		requires(drive);
	}
	
	public DriveStraightDistance(){
		this.distance = RobotMap.prefs.getDouble("Straight distance", 1);
		this.distance = 30;
		this.power = RobotMap.prefs.getDouble("Straight power", .3);
		this.forward = (power >= 0);
		requires(drive);
	}

	@Override
	protected void initialize() {
		startEncoder = RobotMap.wheelEncoder.get();
		endEncoder = (int) (startEncoder + (forward ? 1 : -1) * distance * RobotMap.TICKS_PER_METER);
		heading = gyro.getAngle();
		drive.driveOnHeadingInit(heading);
	}

	@Override
	protected void execute() {
		drive.driveOnHeading(power);
		System.out.println("Power = " + power);
		System.out.println("Forward = " + forward);
		System.out.println("Distance = " + distance);
	}

	@Override
	protected boolean isFinished() {
		return forward ? RobotMap.wheelEncoder.get() > endEncoder : RobotMap.wheelEncoder.get() < endEncoder;
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
