

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
private CANSparkMax intakeMotor1 ;
private CANSparkMax intakeMotor2 ;

    

 public Intake() 
 {
 //intakeMotor = new PWMSparkMax(9);
 intakeMotor1 = new CANSparkMax(7,CANSparkLowLevel.MotorType.kBrushless);
 intakeMotor2 = new CANSparkMax(7,CANSparkLowLevel.MotorType.kBrushless);


 //addChild("Intake Motor",intakeMotor);
 addChild("Limit Switch", limitSwitch );

 intakeMotor1.setInverted(false);
 intakeMotor2.setInverted(false);

 intakeMotor2.follow(intakeMotor1);

 }

    
    public void suck() 
    {
        intakeMotor1.set(.5);
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

    public boolean atLimit() 
    {
        return limitSwitch.get();

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

