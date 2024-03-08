

package frc.robot.subsystems;


import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel;


public class Intake extends SubsystemBase {


//private PWMSparkMax intakeMotor;
private CANSparkMax intakeMotor ;
private DigitalInput limitSwitch = new DigitalInput(4);

    

 public Intake() 
 {
 //intakeMotor = new PWMSparkMax(9);
 intakeMotor = new CANSparkMax(7,CANSparkLowLevel.MotorType.kBrushless);

 //addChild("Intake Motor",intakeMotor);
 addChild("Limit Switch", limitSwitch );

 intakeMotor.setInverted(false);


 }

    
    public void suck() 
    {
        intakeMotor.set(.5);
     }

    
     public void reversesuck() 
    {
        intakeMotor.set(-.6);
    }

    @Override
    public void periodic() 
    {

    }

    @Override
    public void simulationPeriodic() 
    {

    }

    public boolean atLimit() 
    {
        return limitSwitch.get();

    }

    public void stop()
    {
        intakeMotor.set(0);
        intakeMotor.stopMotor();
    }

    public void feed()
    {
        intakeMotor.set(.8);
    }

}

