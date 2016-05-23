package robot.utilities;

public class MathHelper {
	public static double clamp(double value, double min, double max) {
		return Math.max(Math.min(value, max), min);
	}
}
