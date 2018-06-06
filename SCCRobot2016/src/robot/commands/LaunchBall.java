package robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.BallLauncher;

public class LaunchBall extends Command {

	private static final double PERIOD = 1;
	private BallLauncher launcher = Robot.launcher;
	private Joystick joystick = DriverStation.joystick; //using joystick right now
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
		// SmartDashboard.putNumber("servo angle", RobotMap.servo.getAngle());
		// SmartDashboard.putNumber("servo position", RobotMap.servo.getPosition());
		if (!shoot && joystick.getRawButton(5) && !waitToShoot) {
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

			//for gamepad controller
//			int pov = joystick.getPOV();
//			if (pov == 0) {
//				launcher.riseActuator(10);
//			} else if (pov == 180) {
//				launcher.riseActuator(-10);
//			} else if (pov == -1) {
//				launcher.riseActuator(0);
//			}
			
//			launcher.riseActuator(-y); // try -y
			if (joystick.getRawButton(5)) {
				// positive power is the correct direction to rewind the cable
				launcher.rawShoot(1);
			} else {
				launcher.rawShoot(0);
			}
		}
		
		//for joystick
		while (joystick.getRawButton(5)) {
			launcher.riseActuator(20);
		}
		
		while (joystick.getRawButton(6)) {
			launcher.riseActuator(-20);
		}

		if (joystick.getRawButton(3)) {
			// launcher.riseActuator(0.3);
			RobotMap.gearboxSol.set(DoubleSolenoid.Value.kForward);
		} else if (joystick.getRawButton(4)) {
			// launcher.riseActuator(-.3);
			RobotMap.gearboxSol.set(DoubleSolenoid.Value.kReverse);
		} else if (joystick.getRawButton(6)) {
			RobotMap.cannonSol.set(DoubleSolenoid.Value.kForward);
		}else if (joystick.getRawButton(5)) {
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
