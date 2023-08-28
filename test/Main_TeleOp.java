//package org.firstinspires.ftc.teamcode.TeleOp;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import  com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.teamcode.TeleOp.Baseandlift.*;
//import org.firstinspires.ftc.teamcode.TeleOp.FullDriving.*;
//
//
//@TeleOp(name = "Main_TeleOp",group = "TeleOp")
//public class Main_TeleOp extends LinearOpMode{
//    FullDriving fullDriving = new FullDriving();
//    Baseandlift baseandlift = new Baseandlift();
//
//    @Override
//    public void  runOpMode() throws InterruptedException{
//
//
//        waitForStart();
//        if (opModeIsActive()) {
//
//            while (opModeIsActive()) {
//
//                fullDriving.Forward_Backward();
//                fullDriving.Right_Left();
//                fullDriving.Rotate();
//                baseandlift.Base();
//                baseandlift.Up_Down_lift();
//                baseandlift.Grip();
//            }
//        }
//    }
//
//}