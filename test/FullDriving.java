//package org.firstinspires.ftc.teamcode.TeleOp;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import  com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//
//
//
//@TeleOp(name = "FullDriving")
//public class FullDriving extends LinearOpMode{
//
//    public DcMotor LeftFront_Motor;
//    public DcMotor RightFront_Motor;
//    public DcMotor LeftBack_Motor;
//    public DcMotor RightBack_Motor;
//
//    public void moveDriveTrain(){
//        double vertical;
//        double horizontal;
//        double pivot;
//        vertical = -gamepad1.left_stick_y;
//        horizontal = gamepad1.left_stick_x;
//        pivot = gamepad1.right_stick_x;
//        RightFront_Motor.setPower(pivot + (-vertical + horizontal));
//        LeftFront_Motor.setPower(pivot+(-vertical +  horizontal));
//        RightBack_Motor.setPower(pivot + (-vertical - horizontal));
//        LeftBack_Motor.setPower(pivot +(-vertical + horizontal));
//    }
//
//
//
//    public void Forward_Backward() {
//        LeftFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
//        LeftBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//        RightFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
//        RightBack_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
//        LeftFront_Motor.setPower(gamepad1.left_stick_y);
//        RightFront_Motor.setPower(gamepad1.left_stick_y);
//        LeftBack_Motor.setPower(gamepad1.left_stick_y);
//        RightBack_Motor.setPower(gamepad1.left_stick_y);
//
//    }
//
//    public void Right_Left(){
//        LeftFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
//        LeftBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//        RightFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
//        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//        LeftFront_Motor.setPower(gamepad1.right_stick_x);
//        RightFront_Motor.setPower(gamepad1.right_stick_x);
//        LeftBack_Motor.setPower(gamepad1.right_stick_x);
//        RightBack_Motor.setPower(gamepad1.right_stick_x);
//
//    }
//
//    public void Rotate(){
//        LeftFront_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//        LeftBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//        RightFront_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
//
//            LeftFront_Motor.setPower(-gamepad1.right_trigger);
//            RightFront_Motor.setPower(-gamepad1.right_trigger);
//            LeftBack_Motor.setPower(-gamepad1.right_trigger);
//            RightBack_Motor.setPower(-gamepad1.right_trigger);
//            LeftFront_Motor.setPower(gamepad1.left_trigger);
//            RightFront_Motor.setPower(gamepad1.left_trigger);
//            LeftBack_Motor.setPower(gamepad1.left_trigger);
//            RightBack_Motor.setPower(gamepad1.left_trigger);
//
//    }
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        LeftFront_Motor = hardwareMap.get(DcMotor.class, "LeftFront_Motor");
//        RightFront_Motor = hardwareMap.get(DcMotor.class, "RightFront_Motor");
//        LeftBack_Motor = hardwareMap.get(DcMotor.class, "LeftBack_Motor");
//        RightBack_Motor = hardwareMap.get(DcMotor.class, "RightBack_Motor");
//        waitForStart();
//        while (opModeIsActive()) {
//            Forward_Backward();
//            Right_Left();
//        }
//
//    }
//}