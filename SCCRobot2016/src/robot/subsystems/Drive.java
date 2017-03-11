package robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.RobotMap;
import robot.commands.ManualDrive;
import robot.utilities.MathHelper;

public class Drive extends Subsystem implements PIDOutput {

	private PIDController drivePID;
	private double pidOutput;

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
	}

	public void driveOnHeadingInit(double heading) {
		double kp = RobotMap.prefs.getDouble("Heading_P", RobotMap.STRAIGHT_KP);
		double ki = RobotMap.prefs.getDouble("Heading_I", RobotMap.STRAIGHT_KI);
		double kd = RobotMap.prefs.getDouble("Heading_D", RobotMap.STRAIGHT_KD);
		// SmartDashboard.putString("kp, ki, kd", kp + ", " + ki + ", " + kd);
		drivePID = new PIDController(kp, ki, kd, RobotMap.gyro, this);
		drivePID.setSetpoint(heading);
		drivePID.setOutputRange(-0.5, 0.5);
		drivePID.enable();
		// SmartDashboard.putNumber("set point ", drivePID.getSetpoint());
	}

	public void driveOnHeading(double power) {
		// go straight but correct with the pidOutput
		// given the pid output, rotate accordingly
		double pFL = power - pidOutput;
		double pFR = power + pidOutput;
		double pBL = power + pidOutput;
		double pBR = power - pidOutput;
		double error = RobotMap.gyro.getAngle() - drivePID.getSetpoint();
		// SmartDashboard.putNumber("heading error", error);
		// SmartDashboard.putNumber("Pid output", pidOutput);
		// SmartDashboard.putNumber("power straight drive", power);
		pFL = MathHelper.clamp(pFL, -1, 1);
		pFR = MathHelper.clamp(pFR, -1, 1);
		pBL = MathHelper.clamp(pBL, -1, 1);
		pBR = MathHelper.clamp(pBR, -1, 1);
		rawMecanumDrive(pFL, pFR, pBL, pBR);
	}

	public void driveOnHeadingEnd() {
		drivePID.reset();
		drivePID.free();
		pidOutput = 0;
		rawStop();
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ManualDrive());
	}

	public void rawMecanumDrive(double pFL, double pFR, double pBL, double pBR) {
		double max = Math.min(RobotMap.sonicAverage.getAverage() / 1000, 1);
		double min = -max;
		// Bypass the sonic sensor
		max = 1;
		min = -1;
		pFL = MathHelper.clamp(pFL, min, max);
		pFR = MathHelper.clamp(pFR, min, max);
		pBL = MathHelper.clamp(pBL, min, max);
		pBR = MathHelper.clamp(pBR, min, max);
		// motors on the left and right are back to back
		// and should rotate in opposite directions -> minus signs
		RobotMap.motorBL.set(pBL);
		RobotMap.motorBR.set(-pBR);
		RobotMap.motorFL.set(-pFL);
		RobotMap.motorFR.set(pFR);
	}

	public void rawStop() {
		RobotMap.motorBL.set(0);
		RobotMap.motorBR.set(0);
		RobotMap.motorFL.set(0);
		RobotMap.motorFR.set(0);
		// RobotMap.motorBL.disable();
		// RobotMap.motorBR.disable();
		// RobotMap.motorFL.disable();
		// RobotMap.motorFR.disable();
	}

}
