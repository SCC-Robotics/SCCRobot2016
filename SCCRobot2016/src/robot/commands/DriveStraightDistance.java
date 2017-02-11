package robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.ContinuousGyro;
import robot.utilities.MathHelper;

public class DriveStraightDistance extends Command implements PIDOutput {
	private double distance = Double.NaN;
	private int startEncoder;
	private int endEncoder;
	private boolean forward;
	private double heading;
	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double power = Double.NaN;

	private PIDController pidEncoder;
	private double pidOutput;

	public DriveStraightDistance(double distance, double power) {
		this.forward = (power >= 0);
		this.distance = distance;
		this.power = power;
		requires(drive);
	}

	public DriveStraightDistance(double distance) {
		this.distance = distance;
		requires(drive);
	}

	public DriveStraightDistance() {
		requires(drive);
	}

	@Override
	protected void initialize() {
		if (Double.isNaN(distance)) {
			distance = RobotMap.prefs.getDouble("Straight distance", 0.5);
		}
		if (Double.isNaN(power)) {
			power = RobotMap.prefs.getDouble("Straight power", 0.3);
		}
		forward = (power >= 0);

		startEncoder = RobotMap.wheelEncoder.get();
		endEncoder = (int) (startEncoder + (forward ? 1 : -1) * distance * RobotMap.TICKS_PER_METER);
		heading = gyro.getAngle();
		drive.driveOnHeadingInit(heading);

		pidEncoder = new PIDController(RobotMap.STRAIGHT_KP, RobotMap.STRAIGHT_KI, RobotMap.STRAIGHT_KD,
				RobotMap.wheelEncoder, this);
		pidEncoder.setSetpoint(endEncoder);
		pidEncoder.setOutputRange(-0.5, 0.5);
		pidEncoder.setAbsoluteTolerance(0.03 * RobotMap.TICKS_PER_METER);
		pidEncoder.setToleranceBuffer(25);
		pidEncoder.enable();

		// SmartDashboard.putString("power, distance, forward", power + ", " + distance + ", " + forward);
	}

	@Override
	protected void execute() {
		drive.driveOnHeading(MathHelper.clamp(pidOutput, -Math.abs(power), Math.abs(power)));

		// SmartDashboard.putString("Encoder Current and Target", RobotMap.wheelEncoder.get() + ", " + endEncoder);
		// SmartDashboard.putNumber("PID Encoder Average Error", pidEncoder.getAvgError());
		// SmartDashboard.putNumber("PID Encoder Error", pidEncoder.getError());
	}

	@Override
	protected boolean isFinished() {
		// return forward ? RobotMap.wheelEncoder.get() > endEncoder :
		// RobotMap.wheelEncoder.get() < endEncoder;
		return pidEncoder.onTarget();
	}

	@Override
	protected void end() {
		drive.driveOnHeadingEnd();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
	}

}
