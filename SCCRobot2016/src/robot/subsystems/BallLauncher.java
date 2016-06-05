package robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;
import robot.commands.LaunchBall;
import robot.utilities.MathHelper;

public class BallLauncher extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LaunchBall());
	}

	public void riseActuator(double power) {
		power = MathHelper.clamp(power, -1, 1);
		RobotMap.motorActuator.set(power);
	}

	public void rotateServo(double angle) {
		RobotMap.servo.setAngle(angle);
	}

	public void pullWinch(double power) {
		power = MathHelper.clamp(power, -1, 1);
		RobotMap.motorWinch.set(power);
	}

	public void stop() {
		RobotMap.motorActuator.set(0);
		RobotMap.motorWinch.set(0);
	}

}
