// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

// import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
// import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.GrandTheftDrive;
import frc.robot.commands.HalveDriveSpeed;
import frc.robot.commands.SetForwardIntakeSpeed;
import frc.robot.commands.SetIntakeSpeedStop;
import frc.robot.commands.SetReverseIntakeSpeed;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final IntakeSubsystem m_robotIntake = new IntakeSubsystem();

  // The autonomous routines

  // A simple auto routine that drives forward a specified distance, and then stops.
  // private final Command m_simpleAuto =
  //     new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  // The opreators's controller
  XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
       
    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    
    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right.
    // m_robotDrive.setDefaultCommand(new ArcadeDrive(m_robotDrive, m_driverController::getLeftY, m_driverController::getRightX));

    // A split-stick arcade command, with forward/backward controlled by the left/right triggers 
    // and turning controlled by the left stick.   
    m_robotDrive.setDefaultCommand(
      new GrandTheftDrive(m_robotDrive, m_driverController::getRightTriggerAxis, m_driverController::getLeftTriggerAxis, m_driverController::getLeftX));

    // Add commands to the autonomous command chooser
    // m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // While the driver is holding the shoulder button, drive at half speed
    new JoystickButton(m_driverController, Button.kRightBumper.value)
        .whenHeld(new HalveDriveSpeed(m_robotDrive));

    // Control the intake motor speed while the operator is holder the triggers
    Trigger leftTriggerButton = new Trigger(() -> m_operatorController.getLeftTriggerAxis() > OIConstants.kDeadbandThreshold);
    Trigger rightTriggerButton = new Trigger(() -> m_operatorController.getRightTriggerAxis() > OIConstants.kDeadbandThreshold);

    rightTriggerButton.whileActiveOnce(new SetForwardIntakeSpeed(m_robotIntake));
    leftTriggerButton.whileActiveOnce(new SetReverseIntakeSpeed(m_robotIntake));

    rightTriggerButton.or(leftTriggerButton).whenInactive(new SetIntakeSpeedStop(m_robotIntake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
