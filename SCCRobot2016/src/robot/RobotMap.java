package robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import robot.utilities.AnalogUltrasonic;
import robot.utilities.ContinuousGyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// PID values for driving straight
	// oscillation at kp = 0.1 with a period of 2 s
	// got kp, ki, and kd using Nichols and Ziegler method
	// multiply ki by delta_t, and divide kd by delta_t since
	// not done in the code of PIDController
	public static final double STRAIGHT_KP = 0.06;
	public static final double STRAIGHT_KI = 0.003;
	public static final double STRAIGHT_KD = 0.3;

	// the actuators and sensors of the robot
	// the four motor controllers (B=back, F=front, L=left, R=right)
	public static VictorSP motorFR = new VictorSP(1);
	public static VictorSP motorFL = new VictorSP(0);
	public static VictorSP motorBR = new VictorSP(3);
	public static VictorSP motorBL = new VictorSP(2);

	// Hall effect sensors on the actuator
	public static DigitalInput hallbot = new DigitalInput(0);
	public static DigitalInput halltop = new DigitalInput(1);
	
	// Sonic sensor
	// public static AverageSonicSensor sonicAverage = new AverageSonicSensor(new AnalogInput(0), 5);
	public static AnalogUltrasonic ultrasonic = new AnalogUltrasonic(0);
	
	// motor controller for the ball launcher
	public static VictorSP motorShooter = new VictorSP(4);
	public static VictorSP motorActuator = new VictorSP(5);
	public static Servo servo = new Servo(6);

	// solenoid
	public static DoubleSolenoid gearboxSol = new DoubleSolenoid(0, 1);
	public static DoubleSolenoid cannonSol = new DoubleSolenoid(4, 5);
	public static Solenoid lightSol = new Solenoid(3);
	// Compressor
	public static Compressor compressor = new Compressor(0);

	// the accelerometer and gyro
	// public static AHRS ahrs = new AHRS(SPI.Port.kMXP);
	// a gyro based on the AHRS gyro that uses a continuous angle for pidGet
	public static ContinuousGyro gyro = new ContinuousGyro(SPI.Port.kMXP);

	// encoders for the wheel
	public static Encoder wheelEncoder = new Encoder(9, 8); // a->9, b->8
	// measured 4422 ticks over 26 feet
	// public static final double TICKS_PER_METER = (4422 / (26 * 12 * .0254));
	// Latest measurement was 1528 ticks over 9 2/3 feet
	public static final double TICKS_PER_METER = (1528 / (9.667 * 12 * .0254));

	// table of values to store on the roborio and possibly modify on the
	// smartdashboard
	public static Preferences prefs = Preferences.getInstance();

	public static void init() {
		// populate the LiveWindow with variables (visible only in test mode)
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Front Left Victor", motorFL);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Front Right Victor", motorFR);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Back Left Victor", motorBL);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Back Right Victor", motorBR);

		// Add values to the preferences table: useful for interactively tuning
		// the robot (e.g. pid's)
		setDefaultPref("Test heading", false);
		setDefaultPref("Heading", 0);
		setDefaultPref("Heading_P", STRAIGHT_KP);
		setDefaultPref("Heading_I", STRAIGHT_KI);
		setDefaultPref("Heading_D", STRAIGHT_KD);
		setDefaultPref("Straight distance", 1);
		setDefaultPref("Straight power", .3);
		setDefaultPref("Drive Path", "d 0.5, t 90, d 0.5");
	}

	public static void setDefaultPref(String key, Object value) {
		if (!prefs.containsKey(key)) {
			if (value.getClass() == Boolean.class) {
				prefs.putBoolean(key, (boolean) value);
			} else if (value.getClass() == Double.class) {
				prefs.putDouble(key, (double) value);
			} else if (value.getClass() == Float.class) {
				prefs.putFloat(key, (float) value);
			} else if (value.getClass() == Integer.class) {
				prefs.putInt(key, (int) value);
			} else if (value.getClass() == Long.class) {
				prefs.putLong(key, (long) value);
			} else if (value.getClass() == String.class) {
				prefs.putString(key, (String) value);
			} else {
				throw new IllegalArgumentException("key=" + key + ", value=" + value);
			}
		}
	}

}
