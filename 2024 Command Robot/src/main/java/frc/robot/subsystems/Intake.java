

package frc.robot.subsystems;


import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

//import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkFlex;


//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel;


public class Intake extends SubsystemBase {


//private PWMSparkMax intakeMotor;
private PWMVictorSPX intakeMotor1 ;
private PWMVictorSPX intakeMotor2 ;

    

 public Intake() 
 {
 //intakeMotor = new PWMSparkMax(9);
 intakeMotor1 = new PWMVictorSPX(Constants.motors.intakemotor1);
 intakeMotor2 = new PWMVictorSPX(Constants.motors.intakemotor2);


 intakeMotor1.setInverted(true);
 intakeMotor2.setInverted(true);
intakeMotor1.addFollower(intakeMotor2);

 }

    
    public void suck() 

    {
        intakeMotor1.set(.25);
     }

    public void suck( boolean doIt)
    {
        if (doIt)
        {
            suck( );
        }
        else 
        {
            stop();
        }
    } 
    
     public void reversesuck() 
    {
        intakeMotor1.set(-.6);
    }

    @Override
    public void periodic() 
    {

    }

    @Override
    public void simulationPeriodic() 
    {

    }

    public void stop()
    {
        intakeMotor1.set(0);
        intakeMotor1.stopMotor();
        intakeMotor2.set(0);
        intakeMotor2.stopMotor();
    }

    public void feed()
    {
        intakeMotor1.set(-.8);
    }

}

