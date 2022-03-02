// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.IntakeConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorId, MotorType.kBrushed);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  public void setForwardSpeed(double forward) {
    m_intakeMotor.set(forward);
  }

  public void setReverseSpeed(double reverse) {
    m_intakeMotor.set(reverse);
  }

  public void setStopSpeed(double stop) {
    m_intakeMotor.set(stop);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
