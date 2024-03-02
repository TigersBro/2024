

package frc.robot.subsystems;


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;




public class Intake extends SubsystemBase {


private PWMSparkMax intakeMotor;

    

 public Intake() {
 intakeMotor = new PWMSparkMax(1);
 addChild("Intake Motor",intakeMotor);
 intakeMotor.setInverted(false);


    }

    
    public void suck() {
        intakeMotor.set(.8);
     }

    
     public void reversesuck() {
        intakeMotor.set(-.6);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }


}

