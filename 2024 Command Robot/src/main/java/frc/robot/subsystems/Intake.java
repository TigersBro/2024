

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;




public class Intake extends SubsystemBase {


private PWMSparkMax intakeMotor;
private DigitalInput limitSwitch = new DigitalInput(4);

    

 public Intake() 
 {

 intakeMotor = new PWMSparkMax(9);
 addChild("Intake Motor",intakeMotor);
 addChild("Limit Switch", limitSwitch );

 intakeMotor.setInverted(false);


 }

    
    public void suck() 
    {
        intakeMotor.set(.8);
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
        intakeMotor.set(.4);
    }

}

