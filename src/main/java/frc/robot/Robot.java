// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib.config.CTREConfigs;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.SwerveModule;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static CTREConfigs ctreConfigs;
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private SwerveModule m_SwerveModule;
  private Swerve m_Swerve;

  private Joystick secondary = new Joystick(1);
  private Joystick driver = new Joystick(0);
  
  private CANSparkMax elevatorMotor;
  private CANSparkMax winchMotor;
  private static final int DeviceID14 = 13;
  private static final int DeviceID13 = 14;

  @Override
  public void robotInit() {
    elevatorMotor = new CANSparkMax(DeviceID14, MotorType.kBrushless);
    winchMotor = new CANSparkMax(DeviceID13, MotorType.kBrushless);

    elevatorMotor.restoreFactoryDefaults();
    winchMotor.restoreFactoryDefaults();

    new Thread(() -> {
      UsbCamera camera = CameraServer.startAutomaticCapture();
      camera.setResolution(320, 240);
  }).start();
    ctreConfigs = new CTREConfigs();
    m_robotContainer = new RobotContainer();

    
  }

  @Override
  public void robotPeriodic() {
    
    CommandScheduler.getInstance().run();
  } 
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}
  /** This function is called periodically during autonomous. */
  
  private double startTime;

  @Override
  public void autonomousInit() {
  startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    Translation2d m_fowrward = new Translation2d(0, 0.25);
    Translation2d m_backward = new Translation2d(0, -0.25);

  if (time - startTime < 1) {
   winchMotor.set(0.11);
   elevatorMotor.set(0.16);
  } 
  if (time - startTime > 4) {
  winchMotor.set(0);
  elevatorMotor.set(0);
  m_robotContainer.intake.setSolenoidTrue();
  } 
  if (time - startTime > 5) {
    winchMotor.set(-0.18);
    elevatorMotor.set(-0.19);
  } if (time - startTime > 8) {
    winchMotor.set(0);
    elevatorMotor.set(0);
   } if (time - startTime > 8.5); {}
   //     m_robotContainer.getSwerve().drive(m_fowrward, 180, true, false);
  // }
  }
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (driver.getRawButton(6)) {
      m_robotContainer.intake.setSolenoidTrue();
   } else if (driver.getRawButton(5)) {
      m_robotContainer.intake.setSolenoidFalse();
   } else if (secondary.getRawButton(9)) {
    elevatorMotor.set(0.4);
  } else if (secondary.getRawButton(10)) {
    elevatorMotor.set(-0.4); 
  } else if (secondary.getRawButton(1)) {
    winchMotor.set(0.3);
  } else if (secondary.getRawButton(2)) {
    winchMotor.set(-0.6);
  } else {
    winchMotor.set(0);
    elevatorMotor.set(0);
  }}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
