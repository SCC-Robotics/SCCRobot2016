package robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.commandgroups.DriveAroundFloor2;
import robot.commands.DrivePath;
import robot.subsystems.BallLauncher;
import robot.subsystems.CameraLight;
import robot.subsystems.Drive;
import robot.utilities.VisionProcessor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation.
 */

public class Robot extends IterativeRobot {

	// the subsystems of the robot
	// just the drive subsystem for now
	// we will have more (e.g. launcher subsystem)
	public static Drive drive = new Drive();
	public static BallLauncher launcher = new BallLauncher();
	public static CameraLight cameraLight = new CameraLight();
	public static VisionProcessor visionProcessor;
	public Command autonomousCommand;

	public void robotInit() {
		RobotMap.init();
		DriverStation.buttonInit();

		// Keep the compressor from starting.
		RobotMap.compressor.stop();

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 360);
		visionProcessor = new VisionProcessor();
		visionProcessor.start();
	}

	@Override
	public void autonomousInit() {
		new DriveAroundFloor2().start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		periodicAll();
	}

	@Override
	public void teleopInit() {
		String path = RobotMap.prefs.getString("Drive Path", "d 0.5, t 90, d 0.5");
		SmartDashboard.putData("Drive Path", new DrivePath(path));
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		periodicAll();

	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		periodicAll();
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
		periodicAll();
	}

	public void periodicAll() {
		RobotMap.sonicAverage.addValue();
		//SmartDashboard.putNumber("Distance in Front", RobotMap.sonicAverage.getAverage());
		SmartDashboard.putNumber("Distance in Front", RobotMap.ultrasonic.getValue());
		
		// SmartDashboard.putBoolean("bottom", RobotMap.bottom.get());
		// SmartDashboard.putBoolean("top", RobotMap.top.get());
		// SmartDashboard.putNumber("Back Left motor", RobotMap.motorBL.get());
		// SmartDashboard.putNumber("Back Right motor", RobotMap.motorBL.get());
		// SmartDashboard.putNumber("Front Left motor", RobotMap.motorFL.get());
		// SmartDashboard.putNumber("Front Right motor", RobotMap.motorFR.get());
		// SmartDashboard.putNumber("Joystick x", DriverStation.joystick.getX());
		// SmartDashboard.putNumber("Joystick y", DriverStation.joystick.getY());
		// SmartDashboard.putNumber("Joystick z", DriverStation.joystick.getZ());
		// SmartDashboard.putNumber("Joystick twist", DriverStation.joystick.getTwist());
		// SmartDashboard.putNumber("Joystick directions degrees", DriverStation.joystick.getDirectionDegrees());
		// SmartDashboard.putNumber("Joystick throttle", DriverStation.joystick.getThrottle());
		// SmartDashboard.putNumber("Yaw angle", RobotMap.gyro.getAngle());
		// SmartDashboard.putNumber("Sonic sensor", RobotMap.sonicSensor.getValue());
		// SmartDashboard.putNumber("Sonic sensor average value", RobotMap.sonicSensor.getAverageValue());
		// SmartDashboard.putNumber("Sonic sensor computed count", RobotMap.sonicAverage.getCount());
		// SmartDashboard.putNumber("Sonic sensor computed sum", RobotMap.sonicAverage.getSum());
		// SmartDashboard.putNumber("Digital sonic sensor", RobotMap.digiSonicSensor.getRangeInches());
		// SmartDashboard.putNumber("WheelEncoder value", RobotMap.wheelEncoder.get());
		// SmartDashboard.putData("reset encoder", new ResetEncoder());
		// SmartDashboard.putString("solenoid",
		// RobotMap.pistonSol.get().toString());
	}
}
