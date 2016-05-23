
package robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// the actuators and sensors of the robot
	// the four motor controllers (B=back, F=front, L=left, R=right)
	public static VictorSP motorFR = new VictorSP(1);
	public static VictorSP motorFL = new VictorSP(0);
	public static VictorSP motorBR = new VictorSP(3);
	public static VictorSP motorBL = new VictorSP(2);

	// the accelerometer and gyro
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP);

	// table of values to store on the roborio and possibly modify on the
	// smartdashboard
	public static Preferences prefs = Preferences.getInstance();

	public static void init() {
		// populate the LiveWindow with variables (visible only in test mode)
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Front Left Victor", motorFL);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Front Right Victor", motorFR);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Back Left Victor", motorBL);
		LiveWindow.addActuator("Drive Subsystem", "Speed Controller Back Right Victor", motorBR);

		// Add values to the preferences table: useful for interactively tuning the robot (e.g. pid's)
		prefs.putBoolean("Test heading", false);
		prefs.putDouble("Heading", 0);
		prefs.putDouble("Heading_P", .001);
		prefs.putDouble("Heading_I", 0);
		prefs.putDouble("Heading_D", 0);
	}

}
