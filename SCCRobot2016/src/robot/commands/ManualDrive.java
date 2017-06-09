package robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import robot.DriverStation;
import robot.Robot;
import robot.subsystems.Drive;

public class ManualDrive extends Command {

	private Joystick joystick = DriverStation.joystick;
	private Drive drive = Robot.drive;

	public ManualDrive() {
		requires(drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		// read the joystick inputs and compute the power of the motors
		double x = joystick.getX(); // left-right (+ for left, - for right)
		double y = -joystick.getY(); // backward-forward (+ for backward, -
									// for
									// forward)
		double z = joystick.getThrottle(); // rotation of the handle (+ if
											// to
											// the right, - if to the left)
		// SmartDashboard.putString("x, y, z from joystick", x + ", " + y + ", " + z);
		// forward is negative on the y-axis
		double pBL = y - x - z;
		double pBR = y + x + z;
		double pFL = y - x + z;
		double pFR = y + x - z;

		// Throttle the speed using the slider at the front of the joystick.
		double throttle = (1 - joystick.getZ()) / 2.0;
		drive.rawMecanumDrive(pBL * throttle, pBR * throttle, pFL * throttle, pFR * throttle);
	}

	@Override
	protected boolean isFinished() { 
		return false;
	}

	@Override
	protected void end() {
		drive.rawStop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
