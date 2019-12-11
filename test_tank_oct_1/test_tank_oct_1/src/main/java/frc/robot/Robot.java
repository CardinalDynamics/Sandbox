/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  //private Joystick m_leftStick;
  //private Joystick m_rightStick;
  //pineapple
  
  private XboxController control;
  private Spark motor1LS, motor2LS, motor3LD,motor4LD, motor1RS, motor2RS, motor3RD, motor4RD;
  private SpeedControllerGroup Left, Right;
  private Solenoid exampleSolenoid;  

  private Compresor c;
  @Override
  public void robotInit() {
    Solenoid.exampleSolenoid = Solenoid(1);
    control = new XboxController(1);
    motor1L = new Spark(0);
    motor2L = new Spark(1);
    motor3L = new Spark(2);
    motor1R = new Spark(3);
    motor2R = new Spark(4);
    motor3R = new Spark(5);
    c = new Compresor(0);
    Left = new SpeedControllerGroup(motor1L, motor2L, motor3L);
    Right = new SpeedControllerGroup(motor1R, motor2R, motor3R);

    m_myRobot = new DifferentialDrive(Left, Right);

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(control.getY(Hand.kLeft), control.getY(Hand.kRight));
 
     if (getAButtonPressed(Hand.kRight)) {
      //yuckhchkgchgkcghk
      System.out.println(A)
    }

  }
}
