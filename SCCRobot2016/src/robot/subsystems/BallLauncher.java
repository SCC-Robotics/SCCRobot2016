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
		boolean top = RobotMap.halltop.get();
		boolean bot = RobotMap.hallbot.get();
		// moving up means power > 0
		// false means magnet on the sensor
		if (!top && power > 0) {
			power = 0;
		} else if (!bot && power < 0) {
			power = 0;
		}
		power = MathHelper.clamp(power, -.5, .5);
		
		// SmartDashboard.putBoolean("Top", RobotMap.halltop.get());
		// SmartDashboard.putBoolean("bottom", RobotMap.hallbot.get());
		// SmartDashboard.putNumber("power", power);
		
		//SET THE POWER FOR WHEEL SHOTER
		RobotMap.wheelShooter.set(power);

		// if (!top.get()) {
		// canMoveUp = (power < 0);
		// }
		// if (!bottom.get()) {
		// canMoveDown = (power > 0);
		// }
	}

	public void rotateServo(double angle) {
		RobotMap.servo.setAngle(angle);
	}

	public void rawShoot(double power) {
		power = MathHelper.clamp(power, -1, 1);
		//RobotMap.motorShooter.set(power);
	}

	public void stop() {
		RobotMap.wheelShooter.set(0);
		//RobotMap.motorShooter.set(0);
	}

}
