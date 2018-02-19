package org.firstinspires.ftc.team8201;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="red_front", group="Pushbot")
//@Disabled
public class mecRedFront extends LinearOpMode{

    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    VuforiaLocalizer vuforia;
    mechard robot = new mechard();
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED    = 0.5;

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
//        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
//                (int) (sensorColor.green() * SCALE_FACTOR),
//                (int) (sensorColor.blue() * SCALE_FACTOR),
//                hsvValues);


        //Drop gemArm
        sleep(3000);

        if(sensorColor.red() > sensorColor.blue()){
            backward(1);
            sleep(1000);
            forward(1);
            sleep(1000);
        }
        if(sensorColor.blue() > sensorColor.red()){
            forward(1);
            sleep(1000);
            backward(1);
            sleep(1000);
        }
        forward(5);
        backward(5);
        right(5);
        left(5);

        //Activate after knocking the jewel
        relicTrackables.activate();

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
            // convert the RGB values to HSV values.
            // multiply by the SCALE_FACTOR.
            // then cast it back to int (SCALE_FACTOR is a double)
//            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
//                    (int) (sensorColor.green() * SCALE_FACTOR),
//                    (int) (sensorColor.blue() * SCALE_FACTOR),
//                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Alpha", sensorColor.alpha());
            telemetry.addData("Red  ", sensorColor.red());
            telemetry.addData("Green", sensorColor.green());
            telemetry.addData("Blue ", sensorColor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();
        }
    }

    public void forward(int time){
        runtime.reset();
        robot.leftWheelFront.setPower(FORWARD_SPEED);
        robot.leftWheelBack.setPower(FORWARD_SPEED);
        robot.rightWheelFront.setPower(FORWARD_SPEED);
        robot.rightWheelBack.setPower(FORWARD_SPEED);
        while (opModeIsActive() && (runtime.seconds() < time)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }

    public void backward(int time){
        runtime.reset();
        robot.leftWheelFront.setPower(-FORWARD_SPEED);
        robot.leftWheelBack.setPower(-FORWARD_SPEED);
        robot.rightWheelFront.setPower(-FORWARD_SPEED);
        robot.rightWheelBack.setPower(-FORWARD_SPEED);
        while (opModeIsActive() && (runtime.seconds() < time)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }

    public void left(int time){
        runtime.reset();
        robot.leftWheelFront.setPower(-FORWARD_SPEED);
        robot.leftWheelBack.setPower(FORWARD_SPEED);
        robot.rightWheelFront.setPower(FORWARD_SPEED);
        robot.rightWheelBack.setPower(-FORWARD_SPEED);
        while (opModeIsActive() && (runtime.seconds() < time)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }

    public void right(int time){
        runtime.reset();
        robot.leftWheelFront.setPower(FORWARD_SPEED);
        robot.leftWheelBack.setPower(-FORWARD_SPEED);
        robot.rightWheelFront.setPower(-FORWARD_SPEED);
        robot.rightWheelBack.setPower(FORWARD_SPEED);
        while (opModeIsActive() && (runtime.seconds() < time)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }
}
