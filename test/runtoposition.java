//package org.firstinspires.ftc.teamcode.test;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//
//@Autonomous(name = "Tensorflowcustom (Blocks to Java)")
//public class runtoposition extends LinearOpMode {
//
//
//    /**
//     * This function is executed when this Op Mode is selected from the Driver Station.
//     */
//    @Override
//    public void runOpMode() {
//
//        // Wait for start command from Driver Station.
//        waitForStart();
//        if (opModeIsActive()) {
//            // Put run blocks here.
//            while (opModeIsActive()) {
//
//                recognitions = tfod.getRecognitions();
//                // If list is empty, inform the user. Otherwise, go
//                // through list and display info for each recognition.
//                if (JavaUtil.listLength(recognitions) == 0) {
//                    telemetry.addData("Error", "ตรวจไม่เจอ");
//                } else {
//                    index = 0;
//                    // Iterate through list and call a function to
//                    // display info for each recognized object.
//                    for (Recognition recognition_item : recognitions) {
//                        recognition = recognition_item;
//                        // Display info.
//                        displayInfo(index);
//                        // Increment index.
//                        index = index + 1;
//                    }
//                }
//                telemetry.update();
//            }
//        }
//        // Deactivate TFOD.
//        tfod.deactivate();
//
//        vuforiaPOWERPLAY.close();
//        tfod.close();
//    }
//
//    /**
//     * Describe this function...
//     */
//    private void displayInfo(int i) {
//        telemetry.addData("Label: " + recognition.getLabel() + ", Confidence: " + recognition.getConfidence(), "X: " + Math.round(JavaUtil.averageOfList(JavaUtil.createListWith(Double.parseDouble(JavaUtil.formatNumber(recognition.getLeft(), 0)), Double.parseDouble(JavaUtil.formatNumber(recognition.getRight(), 0))))) + ", Y: " + Math.round(JavaUtil.averageOfList(JavaUtil.createListWith(Double.parseDouble(JavaUtil.formatNumber(recognition.getTop(), 0)), Double.parseDouble(JavaUtil.formatNumber(recognition.getBottom(), 0))))));
//    }
//}
