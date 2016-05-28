package robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.subsystems.Drive;

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

	public void robotInit() {
		RobotMap.init();
		DriverStation.buttonInit();
		CameraServer server = CameraServer.getInstance();
		server.setQuality(100);
		server.startAutomaticCapture("cam0");
	}

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		super.autonomousInit();
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		super.teleopInit();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Back Left motor", RobotMap.motorBL.get());
		SmartDashboard.putNumber("Back Right motor", RobotMap.motorBL.get());
		SmartDashboard.putNumber("Front Left motor", RobotMap.motorFL.get());
		SmartDashboard.putNumber("Front Right motor", RobotMap.motorFR.get());
		SmartDashboard.putNumber("Joystick x", DriverStation.joystick.getX());
		SmartDashboard.putNumber("Joystick y", DriverStation.joystick.getY());
		SmartDashboard.putNumber("Joystick z", DriverStation.joystick.getZ());
		SmartDashboard.putNumber("Joystick twist", DriverStation.joystick.getTwist());
		SmartDashboard.putNumber("Joystick directions degrees", DriverStation.joystick.getDirectionDegrees());
		SmartDashboard.putNumber("Joystick throttle", DriverStation.joystick.getThrottle());
		SmartDashboard.putNumber("Yaw angle", RobotMap.gyro.getAngle());
	}

	@Override
	public void disabledInit() {
		// TODO Auto-generated method stub
		super.disabledInit();
	}

	@Override
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	@Override
	public void testInit() {
		// TODO Auto-generated method stub
		super.testInit();
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		super.testPeriodic();
	}
}
