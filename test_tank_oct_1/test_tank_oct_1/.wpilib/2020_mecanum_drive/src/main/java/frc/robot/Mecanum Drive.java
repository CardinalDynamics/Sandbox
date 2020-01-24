package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="Mecanum Drive", group="Tele - OP")
public class MecanumWheel extends OpMode
{
// Declare OpMode members.
private ElapsedTime runtime = new ElapsedTime();
private DcMotor frontLeftDrive = null;
private DcMotor frontRightDrive = null;
private DcMotor backLeftDrive = null;
private DcMotor backRightDrive = null;
/*
* Code to run ONCE when the driver hits INIT
*/
@Override
public void init() {
telemetry.addData("Status: ", "Initialized");
// Initialize the hardware variables. Note that the strings used here as parameters
// to 'get' must correspond to the names assigned during the robot configuration
// step (using the FTC Robot Controller app on the phone).
frontLeftDrive = hardwareMap.get(DcMotor.class, "Front_left" );
frontRightDrive = hardwareMap.get(DcMotor.class, "Front_right");
backLeftDrive = hardwareMap.get(DcMotor.class, "Back_left" );
backRightDrive = hardwareMap.get(DcMotor.class, "Back_right" );
frontLeftDrive = Range.clip(frontLeftDrive,0.5,-0.5);
frontRightDrive = Range.clip(frontRightDrive,1,-1);
frontLeftDrive.setPower(0.0);
frontRightDrive.setPower(0.0);
backLeftDrive.setPower(0.0);
backRightDrive.setPower(0.0);
// Tell the driver that initialization is complete.
telemetry.addData("Status", "Initialized");
}

/*
* Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
*/
@Override
public void init_loop() {
}

/*
* Code to run ONCE when the driver hits PLAY
*/
@Override
public void start()
{
runtime.reset();
}

/*
* Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
*/
@Override
public void loop()
{
double magnitude = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
double rightX = gamepad1.right_stick_x;
final double fld = magnitude * Math.cos(robotAngle) + rightX;
final double frd = magnitude * Math.sin(robotAngle) - rightX;
final double bld = magnitude * Math.sin(robotAngle) + rightX;
final double brd = magnitude * Math.cos(robotAngle) - rightX;
frontLeftDrive.setPower(fld);
frontRightDrive.setPower(frd);
backLeftDrive.setPower(bld);
backRightDrive.setPower(brd);
}

/*
* Code to run ONCE after the driver hits STOP
*/
@Override
public void stop() {
}
// hi

}