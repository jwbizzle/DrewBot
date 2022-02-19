// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          new CANSparkMax(DriveConstants.kLeftFrontMotorId, MotorType.kBrushed),
          new CANSparkMax(DriveConstants.kLeftBackMotorId, MotorType.kBrushed));

  // The motors on the right side of the drive.
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
          new CANSparkMax(DriveConstants.kRightFrontMotorId, MotorType.kBrushed),
          new CANSparkMax(DriveConstants.kRightBackMotorId, MotorType.kBrushed));

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    
    // In order to get the forward and backward movement to work with
    // the rotation you need to invert one side of the drive train and 
    // negate the forward or rotational value passed into arcadeDrive().
    // This was determined through trail and error.  In our code we'll invert
    // the "left" side and also negate the forward value in arcadeDrive().
    m_leftMotors.setInverted(true);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    // Negate the forward/backward value to get the stick behaving correctly
    // since we inverted the left motors.
    m_drive.arcadeDrive(-fwd, rot);

    // Display values for debugging.
    // if (Math.abs(fwd) > .1 || Math.abs(rot) > .1) {
    //   System.out.println("The forward value is " + fwd + " and the rotational value is " + rot + ".");
    // }
  }

  public void setLeftMotor(double motorSetting){
    this.m_leftMotors.set(motorSetting);
  }

  public void setRightMotor(double motorSetting){
    this.m_rightMotors.set(motorSetting);
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
