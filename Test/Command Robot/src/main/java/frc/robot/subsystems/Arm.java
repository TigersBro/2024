// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;


public class Arm extends PIDSubsystem {

    private PWMSparkMax m_motor;
    private AnalogPotentiometer m_pot;

    public Arm() {
        super(new PIDController(Constants.armConstants.PVal, Constants.armConstants.IVal, 0));
        ///////////////////// HOW TO TUNE  //////////////////////
        // copied from gears->elevator 
        // https://www.youtube.com/watch?v=IB1Ir4oCP5k
        


        m_motor = new PWMSparkMax(6);
        addChild("Arm Motor", m_motor);

        // 3/4 turn potentiometer = 270 degrees
        m_pot = new AnalogPotentiometer(0, 5);
        addChild("Arm Potentiometer", m_pot);

        getController().setTolerance(Constants.armConstants.armTolerance);
    }

    public void log() {
        
        SmartDashboard.putData("Arm Pot", m_pot);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        log();
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    public void Stop (){
        m_motor.set(0);
        m_motor.stopMotor();
    }
// Manual overrides 
    public void goUp(double speed) {

        m_motor.set(speed);

    }

    public void goDown(double speed){
        //PID control may be good here...
       m_motor.set(speed);
       
    }
    public void manualOverride(double speed)
    {
        m_motor.set(speed);    
    }

    public double getPosition( ){
        return m_pot.get();
    }

    @Override
    public double getMeasurement() {
      return m_pot.get();
    }
  
    public void stop()
    {
        m_motor.stopMotor();
    }
    /** Use the motor as the PID output. This method is automatically called by the subsystem. */
    @Override
    public void useOutput(double output, double setpoint) {
      m_motor.set(output);
    }

}
