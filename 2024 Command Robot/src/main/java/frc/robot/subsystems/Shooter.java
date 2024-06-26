// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
import frc.robot.Constants;

import java.util.Map;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

///import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Shooter extends SubsystemBase {

    //private PWMSparkMax shooterMotor;
    private CANSparkMax shooterMotor1;
    private CANSparkMax shooterMotor2;
    

    
    private GenericEntry motor1Speed ;
    private GenericEntry motor2Speed ;
    private GenericEntry motor3Speed ;
    private GenericEntry motorSpeedPercent ;
    private double  motor1SpeedDefault = .68; 
    private double  motor2SpeedDefault = .68;
    private double  motor3SpeedDefault = 6.6;
    private double  percentSpeedDefault = 80;

    public Shooter() {
        shooterMotor1 = new CANSparkMax(Constants.motors.shootermotor1,CANSparkLowLevel.MotorType.kBrushed);
        shooterMotor2 = new CANSparkMax(Constants.motors.shootermotor2, CANSparkLowLevel.MotorType.kBrushed);
        shooterMotor1.setInverted(false);
        shooterMotor2.setInverted(false);
    
    
        // Both motors for shooting speaker
        motor3Speed = Shuffleboard.getTab("Motors")
                .add("Shoot Speaker Speed", motor3SpeedDefault)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 12))
                .getEntry();
                
        //top motor for shooting amp        
        motor2Speed = Shuffleboard.getTab("Motors")
        .add("Shooter2 Speed", motor2SpeedDefault)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
        
        //bottom motor for shooting amp
        motor1Speed = Shuffleboard.getTab("Motors")
        .add("Shooter1 Speed", motor1SpeedDefault)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();


        //bottom motor for shooting amp
        motorSpeedPercent = Shuffleboard.getTab("Motors")
        .add("Shooter Percent", percentSpeedDefault)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 100))
        .getEntry();


    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void Place() 
    {
        shooterMotor1.set(.1);
    }

    public void Launch() {

        // double speed = motor3Speed.getDouble(motor3SpeedDefault);
        // shooterMotor1.set(speed);
        // shooterMotor2.set(speed);
        
        double voltage = motor3Speed.getDouble(motor3SpeedDefault);
        double voltageLower = voltage * motorSpeedPercent.getDouble(percentSpeedDefault)/100;
        shooterMotor1.setVoltage(voltage);
        shooterMotor2.setVoltage(voltageLower);




        }

    public void Stop() 
    {
        shooterMotor1.set(0);
        shooterMotor1.stopMotor();
        shooterMotor2.set(0);
        shooterMotor2.stopMotor();
    }

    public void LaunchLow(){
        // /bottom motor should spin faster to make the note go vertical
        
        
        double speed1 = motor2Speed.getDouble(motor2SpeedDefault);
        double speed2 = motor1Speed.getDouble(motor1SpeedDefault);
        shooterMotor1.set(speed1);
        shooterMotor2.set(speed2);

    }
    public void BackupShoot() 
    {

        shooterMotor1.set(-.25);
        shooterMotor2.set(-.25);

    }


}
