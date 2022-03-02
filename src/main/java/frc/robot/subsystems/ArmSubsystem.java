// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  private boolean m_isUp = true;
  private double m_lastBurstTime;

  CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.kArmMotorId, MotorType.kBrushed);

  /** Creates a new IntakeSubsystem. */
  public ArmSubsystem() {
  }

  // Set arm motor speed
  public void setSpeed(Double speed) {
    m_armMotor.set(speed);
  }

  // Get current position
  public boolean getPosition() {
    return m_isUp;
  }

  // Set current position
  public void setPosition(boolean isUp) {
    this.m_isUp = isUp;
  }

  // Get recent burst time
  public double getLastBurtTime() {
    return m_lastBurstTime;
  }

  // Get the burst time
  public void setLastBurtTime(double burstTime) {
    this.m_lastBurstTime = burstTime;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
