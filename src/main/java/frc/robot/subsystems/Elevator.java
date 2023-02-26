package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator{

private static final int DeviceID13 = 13;
private static final int DeviceID14 = 14;

private CANSparkMax elevatorMotor;
private CANSparkMax winchMotor;
private SparkMaxPIDController elevatorPIDController;
private SparkMaxPIDController winchPIDController;
private RelativeEncoder elevatorEncoder;
private RelativeEncoder winchEncoder;
public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

public void robotInit() {

elevatorMotor = new CANSparkMax(DeviceID13, MotorType.kBrushless);
winchMotor = new CANSparkMax(DeviceID14, MotorType.kBrushless);

elevatorMotor.restoreFactoryDefaults();
winchMotor.restoreFactoryDefaults();

elevatorEncoder = elevatorMotor.getEncoder();
winchEncoder = winchMotor.getEncoder();

elevatorPIDController = elevatorMotor.getPIDController();
winchPIDController = winchMotor.getPIDController();

kP = 0.1;
kI = 1e-4;
kD = 1;
kIz = 0;
kFF = 0;
kMaxOutput = 0.1;
kMinOutput = -0.2;

winchPIDController.setP(kP);
winchPIDController.setI(kI);
winchPIDController.setD(kD);
winchPIDController.setIZone(kIz);
winchPIDController.setFF(kFF);
winchPIDController.setOutputRange(kMinOutput, kMaxOutput);

elevatorPIDController.setP(kP);
elevatorPIDController.setI(kI);
elevatorPIDController.setD(kD);
elevatorPIDController.setIZone(kIz);
elevatorPIDController.setFF(kFF);
elevatorPIDController.setOutputRange(kMinOutput, kMaxOutput);
}

public void robotPeriodic() {
    elevatorEncoder.getPosition();
elevatorEncoder.getVelocity();
winchEncoder.getPosition();
elevatorEncoder.getVelocity();
}
public void teleopPeriodic() {

SmartDashboard.putNumber("Encoder Position", elevatorEncoder.getPosition());
SmartDashboard.putNumber("Encoder Velocity", elevatorEncoder.getVelocity());
SmartDashboard.putNumber("Encoder Position", winchEncoder.getPosition());
SmartDashboard.putNumber("Encoder Velocity", winchEncoder.getVelocity());

if((0.1 != kP)) { elevatorPIDController.setP(0.1); kP = 0.1; }
if((1e-4 != kI)) { elevatorPIDController.setI(1e-4); kI = 1e-4; }
if((1 != kD)) { elevatorPIDController.setD(1); kD = 1; }
if((0 != kIz)) { elevatorPIDController.setIZone(0); kIz = 0; }
if((0 != kFF)) { elevatorPIDController.setFF(0); kFF = 0; }
if((0.1 != kMaxOutput) || (-0.2 != kMinOutput)) { 
    elevatorPIDController.setOutputRange(-0.2, 0.1); 
  kMinOutput = -0.2; kMaxOutput = 0.1; 
}
}

public void highcube() {
    elevatorPIDController.setReference(0, CANSparkMax.ControlType.kPosition);
    winchPIDController.setReference(20, CANSparkMax.ControlType.kPosition);
}
public void midcube() {

}
public void midcone() {

}
public void startingconfig() {
winchPIDController.setReference(0, CANSparkMax.ControlType.kPosition);
}
public void floor() {

}
}