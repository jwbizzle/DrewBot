// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class GrandTheftDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private double m_acceleration;
  private double m_steering;

  /** Creates a new DefaultDrive. */
  public GrandTheftDrive(DriveSubsystem subsystem, double acceleration, double steering) {
    m_drive = subsystem;
    m_acceleration = acceleration;
    m_steering = steering;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.setLeftMotor(m_acceleration + m_steering);
    m_drive.setRightMotor(m_acceleration - m_steering);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
