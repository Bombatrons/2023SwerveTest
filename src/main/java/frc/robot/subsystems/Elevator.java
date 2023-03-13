package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;

public class Elevator{
    private static Elevator mInstance;
    private Joystick secondary = new Joystick(1);
    public static Elevator getInstance() {
        if (mInstance == null) {
          mInstance = new Elevator();
        }
        return mInstance;
      }

private static final int DeviceID13 = 14;
private static final int DeviceID14 = 13;

private CANSparkMax elevatorMotor;
private CANSparkMax winchMotor;

public void robotInit() {

elevatorMotor = new CANSparkMax(DeviceID13, MotorType.kBrushless);
winchMotor = new CANSparkMax(DeviceID14, MotorType.kBrushless);

elevatorMotor.restoreFactoryDefaults();
winchMotor.restoreFactoryDefaults();
}

public void robotPeriodic() {
}
public void teleopPeriodic() {

}
public void winchout() {
 System.out.println("winchout active");
  winchMotor.get();
}

public void winchin() {
winchMotor.get();;
}
public void elevator(){
  elevatorMotor.set(0.2);
}
public void elevatorout() {
elevatorMotor.set(0.2);
}
public void elevatorin() {
if (secondary.getRawButton(1)) {
  elevatorMotor.set(0.2);
} else if (secondary.getRawButton(2)) {
  elevatorMotor.set(-0.2);
}
}
}