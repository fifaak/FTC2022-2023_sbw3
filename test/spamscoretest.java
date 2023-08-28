package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name="spamscoretest", group="Autonomous")
public class spamscoretest extends LinearOpMode {
    public DcMotor LeftFront_Motor = null;
    public DcMotor RightFront_Motor = null;
    public DcMotor LeftBack_Motor = null;
    public DcMotor RightBack_Motor = null;
    public DcMotor motor1;
    public DcMotor motor2;
    public Servo servo0;
    public Servo servo1;
    public String Signalsleeve = "";
    private ElapsedTime     runtime = new ElapsedTime();
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     WHEEL_DIAMETER_INCHES_LIFT = 2.0;// For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14159265);
    static final double     COUNTS_PER_INCH_LIFT = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES_LIFT * 3.14159265);

    static final double     DRIVE_SPEED             = 1;
    public int ReturnMotorInches = 0;
    public int MotorInches = 0;
    /*
     * Specify the source for the Tensor Flow Model.
     * If the TensorFlowLite object model is included in the Robot Controller App as an "asset",
     * the OpMode must to load it using loadModelFromAsset().  However, if a team generated model
     * has been downloaded to the Robot Controller's SD FLASH memory, it must to be loaded using loadModelFromFile()
     * Here we assume it's an Asset.    Also see method initTfod() below .
     */
//    @SuppressLint("SdCardPath")
//    private static final String TFOD_MODEL_ASSET = "/sdcard/FIRST/tflitemodels/ConeDetection.tflite";
////     private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/ConeDetection.tflite";


    private static final String[] LABELS = {
            "Blue",
            "Green",
            "Red"
    };
    private static final String VUFORIA_KEY =
            "AfZjI23/////AAABmZsgvIcMokMol/Tf3SfdS/xPTDQjzGz/I5Z7k8aVXWtGea9KrllBEM3jsOWbAiVEzBfqAZG3Bg06yHfz6+BPegTpfeaD6MBgsrM0aYrPH6dnkXmMViCRD3gDpbJG5qvX0L/oqvtt6mn59IzXmyjs9prwQn7NxcIB8eQJo3rh0tEs+EyuTTekTKMNgvAKEs+u03GCQI5qGkV29EYqe0oSj+KNh3IFHx+uu3eH1X0X4Hxp8gVH5MGCeZPhhzfyd2QV+36auRxr3accLENR4qYpkHvqMyL2H31SnuINPbCIjtlKa3iqrWYpHHWH6hWQ0h2eGqAsETmZPpP/1HTxTzq4LOL0YeenXj5Pu74pGQ3QMii1";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String TFOD_MODEL_ASSET = "/sdcard/FIRST/tflitemodels/ConeDetection.tflite";
    static double Metertoinch(double METERS) {
        return METERS *=   39.3700787402;
    }
    public void RuntoSignalsleevePosition(String Signalsleeve){
        if (Signalsleeve == "Blue"){
            Left(0.6);

        }
        else if (Signalsleeve == "Red"){
            //

        }
        else if (Signalsleeve == "Green"){
            Right(0.6);
        }
    }
    public void encoderDriveForLift(double speed,String junction) {
        //20 28 41 low mid high
        int LiftTarget;
        if (junction == "Default"){
            MotorInches = 0 - MotorInches;
            ReturnMotorInches = 0;
        }
        else if (junction == "Lowforstart"){
            MotorInches = 10;
            ReturnMotorInches = 20;
        }
        else if (junction == "Low"){
            MotorInches = 20 - MotorInches;
            ReturnMotorInches = 20;
        }
        else if (junction == "Medium"){
            MotorInches = 28 - MotorInches;
            ReturnMotorInches = 28;
        }
        else if (junction == "High"){
            MotorInches = 41 - MotorInches;
            ReturnMotorInches = 41;
        }
        if (opModeIsActive()) {
            LiftTarget = motor1.getCurrentPosition() + (int)(MotorInches * COUNTS_PER_INCH_LIFT);
            motor1.setTargetPosition(LiftTarget);
            motor2.setTargetPosition(LiftTarget);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            motor1.setPower((speed));
            motor2.setPower((speed));
            while (opModeIsActive()  && (motor1.isBusy() && motor2.isBusy())) {

                telemetry.update();
            }
            motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor1.setPower(0);
            motor2.setPower(0);
            motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
        MotorInches = ReturnMotorInches;
    }
    public void encoderDrive(double speed, double maters) {
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;
        if (opModeIsActive()) {
            newLeftFrontTarget = LeftFront_Motor.getCurrentPosition() + (int)(maters * COUNTS_PER_INCH);
            newRightFrontTarget = RightFront_Motor.getCurrentPosition() + (int)(maters * COUNTS_PER_INCH);
            newLeftBackTarget = LeftBack_Motor.getCurrentPosition() + (int)(maters * COUNTS_PER_INCH);
            newRightBackTarget = RightBack_Motor.getCurrentPosition() + (int)(maters * COUNTS_PER_INCH);
            LeftFront_Motor.setTargetPosition(newLeftFrontTarget);
            RightFront_Motor.setTargetPosition(newRightFrontTarget);
            LeftBack_Motor.setTargetPosition(newLeftBackTarget);
            RightBack_Motor.setTargetPosition(newRightBackTarget);
            LeftFront_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            LeftFront_Motor.setPower((speed));
            RightFront_Motor.setPower((speed));
            LeftBack_Motor.setPower((speed));
            RightBack_Motor.setPower((speed));
            while (opModeIsActive() && (LeftFront_Motor.isBusy() && RightFront_Motor.isBusy()) && LeftBack_Motor.isBusy() && RightBack_Motor.isBusy()) {
//                telemetry.addData("Running to",  " %7d :%7d", newLeftFrontTarget);
//                telemetry.addData("Currently at",  " at %7d :%7d", LeftFront_Motor.getCurrentPosition());
                telemetry.update();
            }
            LeftFront_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            RightFront_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            LeftBack_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            RightBack_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            LeftFront_Motor.setPower(0);
            RightFront_Motor.setPower(0);
            LeftBack_Motor.setPower(0);
            RightBack_Motor.setPower(0);
            LeftFront_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightFront_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftBack_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightBack_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromFile(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }
    public void Up_Down_lift(String junction) {
        //20 28 41
                motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                motor2.setDirection(DcMotorSimple.Direction.FORWARD);
                encoderDriveForLift(DRIVE_SPEED,junction);
    }
    public void OpenGrip(){
            //180deg

            servo0.setDirection(Servo.Direction.FORWARD);
            servo1.setDirection(Servo.Direction.REVERSE);
            servo0.setPosition(0.7);
            servo1.setPosition(0.7);
        }
    public void CloseGrip(){
            //90deg

            servo0.setDirection(Servo.Direction.REVERSE);
            servo1.setDirection(Servo.Direction.FORWARD);
            servo0.setPosition(0.8);
            servo1.setPosition(0.8);
        }
    public void Forward(double Meters) {
        LeftFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        encoderDrive(DRIVE_SPEED,  Metertoinch(Meters));
    }
    public void Backward(double Meters){
        LeftFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        encoderDrive(DRIVE_SPEED,  Metertoinch(-Meters));
    }
    public void Right(double Meters){
        LeftFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightFront_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        encoderDrive(DRIVE_SPEED,Metertoinch(Meters));
    }
    public void Left(double Meters){
        LeftFront_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftBack_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightFront_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        encoderDrive(DRIVE_SPEED,Metertoinch(-Meters));

    }
    public void Rotate(double Degrees){
        LeftFront_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        LeftBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightFront_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightBack_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        Degrees = -(2*3.14159265*0.27)*(Degrees/360)*2; //พายอาร์กำลังสองคูณกับอัตราส่วนวงกลม
        encoderDrive(DRIVE_SPEED,Metertoinch(Degrees));
    }
    @Override
    public void runOpMode() {
        servo0 = hardwareMap.get(Servo.class, "servo0");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        LeftFront_Motor = hardwareMap.get(DcMotor.class, "LeftFront_Motor");
        RightFront_Motor = hardwareMap.get(DcMotor.class, "RightFront_Motor");
        LeftBack_Motor = hardwareMap.get(DcMotor.class, "LeftBack_Motor");
        RightBack_Motor = hardwareMap.get(DcMotor.class, "RightBack_Motor");
        LeftFront_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        RightFront_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftBack_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightBack_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftFront_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightFront_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftBack_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        OpenGrip();
        waitForStart();
        if (opModeIsActive()){
//        initVuforia();
//        initTfod();
////        if (tfod != null) {
////            tfod.activate();
////
////            // The TensorFlow software will scale the input images from the camera to a lower resolution.
////            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
////            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
////            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
////            // should be set to the value of the images used to create the TensorFlow Object Detection model
////            // (typically 16/9).
////            tfod.setZoom(0.8, 16.0/9.0);
////        }
////
////        OpenGrip();
////        waitForStart();
////        if (opModeIsActive()) {
////            CloseGrip();
////            while (opModeIsActive() && Signalsleeve =="") {
////                if (tfod != null) {
////                    // getUpdatedRecognitions() will return null if no new information is available since
////                    // the last time that call was made.
////                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
////                    if (updatedRecognitions != null) {
////                        telemetry.addData("# Objects Detected", updatedRecognitions.size());
////
////                        // step through the list of recognitions and display image position/size information for each one
////                        // Note: "Image number" refers to the randomized image orientation/number
////                        for (Recognition recognition : updatedRecognitions) {
////                            double col = (recognition.getLeft() + recognition.getRight()) / 2;
////                            double row = (recognition.getTop() + recognition.getBottom()) / 2;
////                            double width = Math.abs(recognition.getRight() - recognition.getLeft());
////                            double height = Math.abs(recognition.getTop() - recognition.getBottom());
////
////                            telemetry.addData("", " ");
////                            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
////                            Signalsleeve = recognition.getLabel();
////                            telemetry.addData("- Position (Row/Col)", "%.0f / %.0f", row, col);
////                            telemetry.addData("- Size (Width/Height)", "%.0f / %.0f", width, height);
////
////                        }
////                        telemetry.update();
////                    }
////                }
////            }
//            encoderDriveForLift(1,"Lowforstart");
//
//            Forward(1.2334);
//            sleep(2000);
//            Backward(1.234);
            Up_Down_lift("Low");
SpamLowJunction(3);
        }}


    public void SpamLowJunction(int round){
        for (int i=0;i<=round;i++){
        Backward(0.15);
        Up_Down_lift("Default");
        Left(0.383);
        Forward(0.92);
        CloseGrip();
        sleep(500);
        Up_Down_lift("Low");
        sleep(500);
        Backward(0.92);
        Right(0.383);
        Forward(0.15);
        OpenGrip();
        }
    }
}
