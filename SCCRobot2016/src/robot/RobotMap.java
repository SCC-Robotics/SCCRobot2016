package robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Check the ports of the victors connected to the roborio
	public static Victor motorFrontLeft = new Victor(0);
	public static Victor motorFrontRight = new Victor(1);
	public static Victor motorBackLeft = new Victor(2);
	public static Victor motorBackRight = new Victor(3);

	public static Encoder motorFrontLeftEncoder = new Encoder(6, 7, false,
			EncodingType.k1X);
	public static Encoder leftShooterWheelEncoder = new Encoder(4, 5, true,
			EncodingType.k1X);

	public static Encoder rightMotorEncoder = new Encoder(0, 1);
	public static Encoder leftMotorEncoder = new Encoder(2, 3, true);

	// Contains an accelerometer and a gyro
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP);

	public static void init() {
		// The parameters typed in for the encoder objects are random.
		LiveWindow.addActuator("Drive Subsystem",
				"Speed Controller Front Left Victor", (Victor) motorFrontLeft);
		LiveWindow
				.addActuator("Drive Subsystem",
						"Speed Controller Front Right Victor",
						(Victor) motorFrontRight);
		LiveWindow.addActuator("Drive Subsystem",
				"Speed Controller Back Left Victor", (Victor) motorBackLeft);
		LiveWindow.addActuator("Drive Subsystem",
				"Speed Controller Back Right Victor", (Victor) motorBackRight);

		LiveWindow.addSensor("Drive Subsystem", "Right Drive Encoder",
				rightMotorEncoder);
		LiveWindow.addSensor("Drive Subsystem", "Left Drive Encoder",
				leftMotorEncoder);

		LiveWindow.addSensor("ShooterWheel Subsystem",
				"Right Shooter Wheel Encoder", motorFrontLeftEncoder);
		LiveWindow.addSensor("ShooterWheel Subsystem",
				"Left Shooter Wheel Encoder", leftShooterWheelEncoder);
		// unknown, 6 now taken

	}
}