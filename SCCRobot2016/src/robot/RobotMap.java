package robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Victor;
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

	// Check the ports of the encoders connected to the roborio
//	public static Encoder motorFrontLeftEncoder = new Encoder(6, 7, false,
//			EncodingType.k1X);
//	public static Encoder motorFrontRightEncoder = new Encoder(4, 5, true,
//			EncodingType.k1X);
//	public static Encoder motorBackRightEncoder = new Encoder(4, 5, true,
//			EncodingType.k1X);
//	public static Encoder motorBackLeftEncoder = new Encoder(4, 5, false,
//			EncodingType.k1X);

	// NAVX
	//	public static AHRS ahrs = new AHRS(SPI.Port.kMXP);

	public static void init() {
		LiveWindow.addActuator("Drive Subsystem",
				"Speed Controller Front Left Victor", motorFrontLeft);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Front Right Victor", motorFrontRight);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Back Left Victor", motorBackLeft);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Back Right Victor", motorBackRight);
		//
		// LiveWindow.addSensor("Drive Subsystem", "Front Right Drive Encoder",
		// motorFrontRightEncoder);
		// LiveWindow.addSensor("Drive Subsystem", "Front Left Drive Encoder",
		// motorFrontLeftEncoder);
		// LiveWindow.addSensor("Drive Subsystem", "Back Right Drive Encoder",
		// motorBackRightEncoder);
		// LiveWindow.addSensor("Drive Subsystem", "Back Left Drive Encoder",
		// motorBackLeftEncoder);
	}
}