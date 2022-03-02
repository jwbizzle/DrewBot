// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.Constants.ArmConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetArmPosition extends CommandBase {
  private final ArmSubsystem m_arm;
  private boolean m_inputArmUp;
  private boolean m_armUp = true;
  private double m_lastBurstTime;

  /** Creates a new SetReverseIntakeSpeed. */
  public SetArmPosition(ArmSubsystem subsystem, boolean inputArmUp) {
    m_arm = subsystem;
    m_inputArmUp = inputArmUp;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_armUp){
      if(Timer.getFPGATimestamp() - m_lastBurstTime < ArmConstants.kArmTimeUp){
        m_arm.setSpeed(ArmConstants.kArmTravel);
      }
      else{
        m_arm.setSpeed(ArmConstants.kArmHoldUp);
      }
    }
    else{
      if(Timer.getFPGATimestamp() - m_lastBurstTime < ArmConstants.kArmTimeDown){
        m_arm.setSpeed(-ArmConstants.kArmTravel);
      }
      else{
        m_arm.setSpeed(-ArmConstants.kArmHoldDown);
      }
    }


    if(m_inputArmUp && !m_armUp){
      m_lastBurstTime = Timer.getFPGATimestamp();
      //System.out.println(lastBurstTime);
      m_armUp = true;

    }
    else if (!m_inputArmUp && m_armUp){
      m_lastBurstTime = Timer.getFPGATimestamp();
      //System.out.println(lastBurstTime);
      m_armUp = false;
    }


    System.out.print(m_lastBurstTime);
    System.out.print(" ");
    System.out.println(m_armUp);
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
