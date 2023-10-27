// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package cowlib;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.DriveConstants.SwervePID;

/** Add your docs here. */
public class SwerveModule {
	private CANSparkMax angleMotor;
	private CANSparkMax speedMotor;
	private PIDController pidController;
	private CANCoder encoder;

	public SwerveModule(int angleMotor, int speedMotor, int encoder) {
		this.angleMotor = new CANSparkMax(angleMotor, MotorType.kBrushless);
		this.speedMotor = new CANSparkMax(speedMotor, MotorType.kBrushless);
		this.pidController = new PIDController(SwervePID.p, SwervePID.i, SwervePID.d);
		this.encoder = new CANCoder(encoder);

		this.pidController.enableContinuousInput(0, 360);
	}

	public SwerveModule(SwerveModuleConfig config) {
		this(config.angleMotorId, config.driveMotorId, config.encoderId);
	}

	public void drive(double speed, double angle) {
		speedMotor.set(speed);
		angleMotor.set(MathUtil.clamp(pidController.calculate(encoder.getAbsolutePosition(), angle), -1, 1));
	}

	public void drive(SwerveModuleState state) {
		this.drive(state.speedMetersPerSecond, state.angle.getDegrees());
	}
}
