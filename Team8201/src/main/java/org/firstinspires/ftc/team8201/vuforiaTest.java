package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="Concept: VuMark Id", group ="Concept")

public class vuforiaTest extends LinearOpMode {

    //Crateing the Object data type
    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {

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

        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            //Create an instance vuMark
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if(vuMark != RelicRecoveryVuMark.UNKNOWN){
                telemetry.addData("VuMark", "%s visible", vuMark);
            }else {
                telemetry.addData("VuMark", "not visible");
            }
            if(vuMark == RelicRecoveryVuMark.LEFT){
                telemetry.addData("testLeft","works.equals");
                telemetry.update();
            }
            telemetry.addData("test",vuMark);
            telemetry.update();
        }
    }
}
