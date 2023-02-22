package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake{
private static Intake mInstance;

DoubleSolenoid mIntakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

public static Intake getInstance() {
  if (mInstance == null) {
    mInstance = new Intake();
  }
    return mInstance;
  }

public void setSolenoidTrue(){
mIntakeSolenoid.set(Value.kForward);
}

public void setSolenoidFalse(){
  mIntakeSolenoid.set(Value.kReverse);
}

public void stop() {
  mIntakeSolenoid.set(Value.kForward);}
}