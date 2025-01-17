package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;


public class TurnToAngleCommand extends CommandBase {

    private final Swerve m_robotDrive;
    private boolean complete = false;
    private double angle;
    private Timer timer = new Timer();
    private double timeout;
    public TurnToAngleCommand(Swerve subsystem, double degrees, double timeoutS){
        m_robotDrive = subsystem;
        angle = degrees;
        timeout = timeoutS;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
        complete = false;
    }
    
    @Override
    public void execute(){
        double gyroAngle = m_robotDrive.getYaw().getDegrees();

        final double kP = 0.2;
        SmartDashboard.putNumber("gyroAngle", gyroAngle);
    
        if (angle > 180) {
            angle = -(360 - angle);
        } else if (angle < -180) {
            angle = 360 + angle;
        }
    
        double err = angle - gyroAngle;
        double speed = MathUtil.clamp(err * kP, -Constants.Swerve.maxAngularVelocity*0.5, Constants.Swerve.maxAngularVelocity*0.5);
    
        if (Math.abs(err) > 2 && timer.get() < timeout) {
            m_robotDrive.drive(new Translation2d(0,0), speed, true, true);
        } else {
            complete = true;
        }
    }

    @Override
    public void end(boolean inturrupted){
        m_robotDrive.drive(new Translation2d(0,0), 0, true, true);
        timer.stop();
    }

    @Override
    public boolean isFinished(){
        return complete;
    }
}