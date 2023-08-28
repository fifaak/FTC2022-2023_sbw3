package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Driveonly",group = "TeleOp")
public class Driveonly extends LinearOpMode {
    public DcMotor LeftFront_Motor;
    public DcMotor RightFront_Motor;
    public DcMotor LeftBack_Motor;
    public DcMotor RightBack_Motor;
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration

        LeftFront_Motor = hardwareMap.get(DcMotor.class, "LeftFront_Motor");
        RightFront_Motor = hardwareMap.get(DcMotor.class, "RightFront_Motor");
        LeftBack_Motor = hardwareMap.get(DcMotor.class, "LeftBack_Motor");
        RightBack_Motor = hardwareMap.get(DcMotor.class, "RightBack_Motor");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        RightFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            LeftFront_Motor.setPower(frontLeftPower);
            LeftBack_Motor.setPower(backLeftPower);
            RightFront_Motor.setPower(frontRightPower);
            RightBack_Motor.setPower(backRightPower);
        }
    }
}