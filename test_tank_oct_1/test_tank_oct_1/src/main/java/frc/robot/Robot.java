/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  //private Joystick m_leftStick;
  //private Joystick m_rightStick;
  //anything you want;
  //pineappe;
  //hi guys;
  //whattup gamers;
  
>>>>>>> adab505a36f1a7fd906b961abff193a28df62c4b
  private XboxController control;
  private Talon motor1L, motor2L, motor3L, motor1R, motor2R, motor3R;
  private SpeedControllerGroup Left, Right;

  @Override
  public void robotInit() {
    
    control = new XboxController(1);
    motor1L = new Talon(0);
    motor2L = new Talon(1);
    motor3L = new Talon(2);
    motor1R = new Talon(7);
    motor2R = new Talon(8);
    motor3R = new Talon(9);

    Left = new SpeedControllerGroup(motor1L, motor2L, motor3L);
    Right = new SpeedControllerGroup(motor1R, motor2R, motor3R);

    m_myRobot = new DifferentialDrive(Left, Right);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(control.getY(-Hand.kLeft), control.getY(-Hand.kRight));
  }
}