package robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	private Joystick gamePad = DriverStation.gamePad;
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
		if (!shoot && gamePad.getRawButton(1) && !waitToShoot) {
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

			double y = gamePad.getY();
			launcher.riseActuator(y); // try -y
			if (gamePad.getRawButton(2)) {
				// positive power is the correct direction to rewind the cable
				launcher.pullWinch(0.3);
			} else {
				launcher.pullWinch(0);
			}
		}

		if (gamePad.getRawButton(3)) {
			// launcher.riseActuator(0.3);
			RobotMap.gearboxSol.set(DoubleSolenoid.Value.kForward);
		} else if (gamePad.getRawButton(4)) {
			// launcher.riseActuator(-.3);
			RobotMap.gearboxSol.set(DoubleSolenoid.Value.kReverse);
		} else if (gamePad.getRawButton(6)) {
			RobotMap.cannonSol.set(DoubleSolenoid.Value.kForward);
		}else if (gamePad.getRawButton(5)) {
			RobotMap.cannonSol.set(DoubleSolenoid.Value.kReverse);
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
