package org.firstinspires.ftc.teamcode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import  com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

@TeleOp(name = "TeleOpRound",group = "TeleOp")
public class TeleOpRound extends LinearOpMode{
    public DcMotor LeftFront_Motor;
    public DcMotor RightFront_Motor;
    public DcMotor LeftBack_Motor;
    public DcMotor RightBack_Motor;
    public DcMotor motor0;
    public DcMotor motor1;
    public Servo servo0;
    public Servo servo1;

//    public String LiftStatus = "Default";
//    public double SafetyLiftValue = 0;
public void MacanumWheels(){
    double y = gamepad1.left_stick_y; // Remember, this is reversed!
    double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
    double rx = -gamepad1.right_stick_x;
    if (x == 0 || y == 0) {
        if (gamepad1.dpad_up) {
            x = 0;
            y = -1;
        } else if (gamepad1.dpad_down) {
            x = 0;
            y = 1;
        } else if (gamepad1.dpad_right) {
            x = -1;
            y = 0;
        } else if (gamepad1.dpad_left) {
            x = 1;
            y = 0;
        }}


    // Denominator is the largest motor power (absolute value) or 1
    // This ensures all the powers maintain the same ratio, but only when
    // at least one is out of the range [-1, 1]


    LeftFront_Motor.setPower(frontLeftPower);    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    double frontLeftPower = (y + x + rx) / denominator;
    double backLeftPower = (y - x + rx) / denominator;
    double frontRightPower = (y - x - rx) / denominator;
    double backRightPower = (y + x - rx) / denominator;
    LeftBack_Motor.setPower(backLeftPower);
    RightFront_Motor.setPower(frontRightPower);
    RightBack_Motor.setPower(backRightPower);
}

//    public void encoderDrive(double speed,double MotorInches) {
//        int LiftTarget;
//        if (opModeIsActive()) {
//            LiftTarget = motor0.getCurrentPosition() + (int)(MotorInches * COUNTS_PER_INCH);
//            motor0.setTargetPosition(LiftTarget);
//            motor1.setTargetPosition(LiftTarget);
//            motor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            runtime.reset();
//            motor0.setPower((speed));
//            motor1.setPower((speed));
//            while (opModeIsActive()  && (motor0.isBusy() && motor1.isBusy())) {
//                MacanumWheels();
//                telemetry.addData("Running to", (LiftStatus));
////                telemetry.addData("Processing",  " at %d %", ((LiftTarget/(motor0.getCurrentPosition()))*100));
//
//                telemetry.update();
//            }
//            motor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            motor0.setPower(0);
//            motor1.setPower(0);
//            motor0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            sleep(250);
//        }
//    }
    public void Liftemergency(){

        motor0.setDirection(DcMotorSimple.Direction.FORWARD);
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        motor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor0.setPower(gamepad2.left_stick_y);
        motor1.setPower(gamepad2.left_stick_y);
//        telemetry.addData("Position",motor0.getCurrentPosition());
    }
//
//    public void Up_Down_lift() {
//        if (motor0.isBusy() && motor1.isBusy()){
//            //wait
//        }
//        else {
//
//
//
//        if(gamepad2.dpad_left){
//            SafetyLiftValue = 20 - SafetyLiftValue;
//            motor0.setDirection(DcMotorSimple.Direction.REVERSE);
//            motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//            LiftStatus = "Low-Junction";
//            encoderDrive(DRIVE_SPEED, SafetyLiftValue);
//            SafetyLiftValue = 20;
//
//
//            telemetry.addData("Lift-Status", LiftStatus);
//
//        }
//        else if(gamepad2.dpad_up){
//            SafetyLiftValue = 28 - SafetyLiftValue;
//            motor0.setDirection(DcMotorSimple.Direction.REVERSE);
//            motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//            LiftStatus = "Medium-Junciton";
//            encoderDrive(DRIVE_SPEED, SafetyLiftValue);
//            SafetyLiftValue = 28;
//
//            telemetry.addData("Lift-Status", LiftStatus);
//
//        }
//        else if(gamepad2.dpad_right){
//            SafetyLiftValue = 41 - SafetyLiftValue;
//            motor0.setDirection(DcMotorSimple.Direction.REVERSE);
//            motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//            LiftStatus = "High-Junction";
//            encoderDrive(DRIVE_SPEED, SafetyLiftValue);
//            SafetyLiftValue = 41;
//
//            telemetry.addData("Lift-Status", LiftStatus);
//
//        }
//        else if(gamepad2.dpad_down){
//            if (LiftStatus == "Low-Junction"){
//                motor0.setDirection(DcMotorSimple.Direction.REVERSE);
//                motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//                LiftStatus = "Default";
//                encoderDrive(DRIVE_SPEED, -20 );
//
//
//            }
//            else if (LiftStatus == "Medium-Junciton"){
//                motor0.setDirection(DcMotorSimple.Direction.REVERSE);
//                motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//                LiftStatus = "Default";
//                encoderDrive(DRIVE_SPEED, -28);
//
//
//            }
//            else if (LiftStatus == "High-Junction"){
//                motor0.setDirection(DcMotorSimple.Direction.REVERSE);
//                motor1.setDirection(DcMotorSimple.Direction.FORWARD);
//                LiftStatus = "Default";
//                encoderDrive(DRIVE_SPEED, -41);
//
//
//
//            }
//            SafetyLiftValue = 0;
//        }
//
//        }
//    }
public void Grip(){
        if(gamepad2.a){
            //180deg
            telemetry.addData("key","A");
            servo0.setDirection(Servo.Direction.FORWARD);
            servo1.setDirection(Servo.Direction.REVERSE);
            servo0.setPosition(0.7);
            servo1.setPosition(0.7);
        }
        if (gamepad2.b){
            //90deg
            telemetry.addData("key","B");
            servo0.setDirection(Servo.Direction.REVERSE);
            servo1.setDirection(Servo.Direction.FORWARD);
            servo0.setPosition(0.8);
            servo1.setPosition(0.8);
        }
    }
//    public void Base(){
//        motor0.setPower(-gamepad2.right_stick_x);
//        telemetry.addData("Speed",-gamepad2.right_stick_x);
//    }
//    public void MacanumWheels(){
//
//}
//    public void Lift(){
//
//
//        float StickPower = -gamepad1.left_stick_y;
//        if (SafetyValueForLift+StickPower > 0 && SafetyValueForLift+StickPower < 400){
//            Liftemergency();
//            SafetyValueForLift+=StickPower;
//        }
//    }


    @Override
    public void runOpMode() throws InterruptedException {

        long sec2;

        ElapsedTime sec;
        sec = new ElapsedTime(System.nanoTime());

        motor0 = hardwareMap.get(DcMotor.class, "motor0");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        servo0 = hardwareMap.get(Servo.class, "servo0");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        LeftFront_Motor = hardwareMap.get(DcMotor.class, "LeftFront_Motor");
        RightFront_Motor = hardwareMap.get(DcMotor.class, "RightFront_Motor");
        LeftBack_Motor = hardwareMap.get(DcMotor.class, "LeftBack_Motor");
        RightBack_Motor = hardwareMap.get(DcMotor.class, "RightBack_Motor");
//        motor0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()){
        while (opModeIsActive()) {
            sec2 = Math.round(sec.seconds());
            // Use left stick to drive and right stick to turn
            if (sec2==150){
                gamepad1.rumble(500);
                gamepad2.rumble(500);
            }
            if ((sec2 % 3) == 0 && (sec2 >=150)) {
                gamepad1.rumble(0.4, 0.4, 25);
                gamepad2.rumble(0.4, 0.4, 25);
                telemetry.addData("BeaconTime", "ไปเก็บบีคอน");
            }

                Liftemergency();
                MacanumWheels();
                Grip();



        }

    }}}
