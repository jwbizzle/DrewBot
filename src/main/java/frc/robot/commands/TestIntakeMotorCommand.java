// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class TestIntakeMotorCommand extends CommandBase {
  private final IntakeSubsystem m_intake;
  private DoubleSupplier m_rightTrigger;
  private DoubleSupplier m_leftTrigger;


  /** Creates a new SetReverseIntakeSpeed. */
  public TestIntakeMotorCommand(IntakeSubsystem subsystem, DoubleSupplier rightTrigger, DoubleSupplier leftTrigger) {
    m_intake = subsystem;
    m_rightTrigger = rightTrigger;
    m_leftTrigger = leftTrigger;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.setSpeed(m_rightTrigger.getAsDouble() - m_leftTrigger.getAsDouble());
   
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
