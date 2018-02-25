package org.firstinspires.ftc.team8201;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="red_back", group="Pushbot")
//@Disabled
public class mecRedBack extends LinearOpMode{

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    VuforiaLocalizer vuforia;
    mechard robot = new mechard();
    private ElapsedTime runtime = new ElapsedTime();

    //Start declaring the variables
    static final double COUNTS_PER_MOTOR_REV = 280;     //The Motor we have Encoder
    static final double COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV/Math.PI;
    static final double DRIVE_SPEED = 0.7;
    static final double TURN_SPEED = 0.5;
    static final double FORWARD_SPEED = 1;

    public String vColumn = "";

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        //****************************Color Sensor*****************************

        // get a reference to the color sensor.
        sensorColor = hardwareMap.get(ColorSensor.class, "cs");

        // get a reference to the distance sensor that shares the same name.
//        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        //****************************vuForia**********************************

        //Local RC camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()); //If u wanna see the camera on the RC
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //License key
        parameters.vuforiaLicenseKey = "AZ+6D5b/////AAAAmRQUk+S030qIoZLhZWPOrbZbzraKFy6NtOgGzvfQKxoBjqGiTiEJDTlDv8gK/EKuq7CP2U0tGXDtroZoWs7PgAZii969frlDaJBpox50Kizzy7CxuUwz1mOA+yf3VfwRRkc+srynTpahWnDGYAuZSTZfCYy2wcpAVAb20J1/kE4z6BL/eAXHHu94ER2jASSGBgeSR01+73BW6G3JEEw9SpluEMNRdJgnMlLwcGLyaO/zuhkfTZOqdffWYsnb2dcbVETTJtVI7EmvLWcoreFhpIwHjEJX0yZiCvw5+MxzRlDVoqIKJoy8hJPDV2Hh6/A1j5mA7IiqssiKjnt3kPfgTGCWH3Eu0vnhE8jWTolJ68JR";

        //Use the back camera
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        //Load the relica recovery vuforia asset
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);


        //**********************autonomous***********************************

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        stopAndResetEncoders();
        relicTrackables.activate();
        //dropGemArm
        sleep(3000);
        if(sensorColor.red() > sensorColor.blue()){
            sleep(2000);
            drive(-2);
            sleep(1000);
            drive(2);
            sleep(1000);
            //pick up gemArm
        }
        if(sensorColor.blue() > sensorColor.red()){
            sleep(2000);
            drive(2);
            sleep(1000);
            drive(-2);
            sleep(1000);
            //pick up gemArm
        }

        if(vColumn == "LEFT"){
            drive(28);
            sleep(1000);
            encoderTurn(90);
            glyphUp();
        }

        if(vColumn == "RIGHT"){

        }

        if(vColumn == "CENTER"){

        }

        if(vColumn == "CD"){
            drive(28);
            sleep(1000);
            encoderTurn(90);
            glyphUp();
        }


        //END POWERS
        robot.leftWheelFront.setPower(0);
        robot.leftWheelBack.setPower(0);
        robot.rightWheelFront.setPower(0);
        robot.rightWheelBack.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

        //color values
        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            // convert the RGB values to HSV values.
            // multiply by the SCALE_FACTOR.
            // then cast it back to int (SCALE_FACTOR is a double)
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();

            if(vuMark == RelicRecoveryVuMark.LEFT){
                vColumn = "LEFT";
            }
            if(vuMark == RelicRecoveryVuMark.RIGHT){
                vColumn = "RIGHT";
            }
            if(vuMark == RelicRecoveryVuMark.CENTER){
                vColumn = "CENTER";
            }

            if(vuMark == RelicRecoveryVuMark.UNKNOWN){
                vColumn = "CD";
            }
        }
    }

    public void encoderTurn(double degrees) {
        double circumference = 92;
        double arc = circumference * (degrees / 360);
        encoderDrive(TURN_SPEED, arc, -arc, arc, -arc, 10.0);
    }

    public void encoderMoveLeft(double inches){                 //Test it out
        encoderDrive(DRIVE_SPEED, -inches, inches, inches, -inches, 5.0);
    }

    public void encoderMoveRight(double inches){                 //Test it out
        encoderDrive(DRIVE_SPEED, inches, -inches, -inches, inches, 5.0);
    }

    public void drive(double inches){
        encoderDrive(DRIVE_SPEED, inches,inches,inches,inches,10.0);
    }

    public void collectorWheel(int time, double speed){
        runtime.reset();
        robot.collectorLeft.setPower(speed);
        robot.collectorRight.setPower(speed);
        while (opModeIsActive() && (runtime.seconds() < time)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }

    public void glyphUp(){
        robot.liftLeft.setPosition(0);
        robot.liftRight.setPosition(1);
    }

    public void glyphDown(){
        robot.liftLeft.setPosition(1);
        robot.liftRight.setPosition(0);
    }

    public void stopAndResetEncoders() {
        robot.leftWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(50); // Wait 50ms to make sure it fully processes

        robot.leftWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void encoderDrive(double speed, double leftFrontInches, double rightFrontInches, double leftBackInches, double rightBackInches , double timeoutS){

        int newLeftFront;
        int newRightFront;
        int newLeftBack;
        int newRightBack;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFront = robot.leftWheelFront.getCurrentPosition() + (int) (leftFrontInches * COUNTS_PER_INCH);
            newRightFront = robot.rightWheelFront.getCurrentPosition() + (int) (rightFrontInches * COUNTS_PER_INCH);
            newLeftBack = robot.leftWheelBack.getCurrentPosition() + (int) (leftBackInches * COUNTS_PER_INCH);
            newRightBack = robot.rightWheelBack.getCurrentPosition() + (int) (rightBackInches * COUNTS_PER_INCH);
            robot.leftWheelFront.setTargetPosition(newLeftFront);
            robot.rightWheelFront.setTargetPosition(newRightFront);
            robot.leftWheelBack.setTargetPosition(newLeftBack);
            robot.rightWheelBack.setTargetPosition(newRightBack);


            // Turn On RUN_TO_POSITION
            robot.leftWheelFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightWheelFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftWheelBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightWheelBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftWheelFront.setPower(Math.abs(speed));
            robot.rightWheelFront.setPower(Math.abs(speed));
            robot.leftWheelBack.setPower(Math.abs(speed));
            robot.rightWheelBack.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftWheelFront.isBusy() && robot.rightWheelFront.isBusy())) {

                // Display it for the driver.
                telemetry.addData("lf" , newLeftFront);
                telemetry.addData("rb" , newRightBack);
                telemetry.update();
            }

        }
    }
}
