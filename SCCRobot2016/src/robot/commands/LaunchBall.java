package robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.BallLauncher;

public class LaunchBall extends Command {

	private static final double PERIOD = 1;
	private BallLauncher launcher = Robot.launcher;
	private Joystick joystick = DriverStation.joystick;
	private boolean shoot, waitToShoot;
	private Timer timer;

	public LaunchBall() {
		requires(launcher);
	}

	@Override
	protected void initialize() {
		timer = new Timer();
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("servo angle", RobotMap.servo.getAngle());
		SmartDashboard.putNumber("servo position", RobotMap.servo.getPosition());
		if (!shoot && joystick.getRawButton(1) && !waitToShoot) {
			shoot = true;
			waitToShoot = true;
			timer.start();
		}
		if (shoot) {
			if (!timer.hasPeriodPassed(PERIOD)) {
				launcher.rotateServo(180);
			} else {
				shoot = false;
			}
		} else if (!shoot) {
			if (!timer.hasPeriodPassed(PERIOD)) {
				launcher.rotateServo(0);
			} else {
				waitToShoot = false;
				timer.stop();
			}
			double y = joystick.getY();
			launcher.riseActuator(y);
			if (joystick.getRawButton(2)) {
				launcher.pullWinch(Math.abs(joystick.getZ()));
			} else {
				launcher.pullWinch(0);
			}

		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		launcher.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
