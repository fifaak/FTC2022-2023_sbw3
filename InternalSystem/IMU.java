package org.firstinspires.ftc.teamcode.InternalSystem;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "IMU",group = "InternalSystem")
public class IMU extends LinearOpMode {

    private BNO055IMU imu;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        BNO055IMU.Parameters imuParameters;
        Orientation angles;
        Acceleration gravity;

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        // Create new IMU Parameters object.
        imuParameters = new BNO055IMU.Parameters();
        // Use degrees as angle unit.
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        // Express acceleration as m/s^2.
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        // Disable logging.
        imuParameters.loggingEnabled = false;
        // Initialize IMU.
        imu.initialize(imuParameters);
        // Prompt user to press start buton.

        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Get absolute orientation
                // Get acceleration due to force of gravity.
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
                gravity = imu.getGravity();
                // Display orientation info.

                telemetry.addData("X ANGLE", angles.firstAngle);
                telemetry.addData("Y ANGLE", angles.secondAngle);
                telemetry.addData("Z ANGLE", angles.thirdAngle);
                // Display gravitational acceleration.
                telemetry.addData("gravity (X)", gravity.xAccel);
                telemetry.addData("gravity (Y)", gravity.yAccel);
                telemetry.addData("gravity (Z)", gravity.zAccel);


                telemetry.update();
            }
        }
    }
}