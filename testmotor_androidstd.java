package org.firstinspires.ftc.teamcode;
import  com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "testmotor_androidstd")
public class testmotor_androidstd extends LinearOpMode{
        private DcMotor leftfrontmotor;
        private DcMotor rightfrontmotor;
        private DcMotor leftbackmotor;
        private DcMotor rightbackmotor;
        private  double percent = 0;
        @Override
        public void runOpMode(){
            leftfrontmotor = hardwareMap.get(DcMotor.class,"left front motor");
            rightfrontmotor = hardwareMap.get(DcMotor.class,"right front motor");
            leftbackmotor = hardwareMap.get(DcMotor.class,"left back motor");
            rightbackmotor = hardwareMap.get(DcMotor.class,"right back motor");
            waitForStart();
            if (opModeIsActive()){
                while (opModeIsActive()){

                    telemetry.update();
                }
            }
        }

}
