/* Copyright (c) 2018 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

import java.util.ArrayList;
import java.util.List;

public class Vuforia {

    private static final String VUFORIA_KEY = "ARH6A3z/////AAAAGZ4wVweTjU73lDoZeCr/rzwpfNAFaWSGUn4qhsRk/g7XznybiUzddzhqWfAWncGfPY8Q8fqY3lozXjMIMdWiZYPQkmYSmb2NkIry1JizLHG3PvtS5yr3fYCT0Tpia25pg03b3lQeoEVYRQUTnAFXQnO4wSwGOmz2wWWMg0rNDBN6gxnUipEKrLaLajvGvwtmkl/EB0P3Rib3zTgQzJXxgi3nHVV4m06LZ3twCd0l4p4EA7W2Js1V+iR7ue94ObAH4FUfJ0qcOsnlnM+DDq5LdJOAP5HbgldfzsncBeqyRA8O4u4TZ6ABu+4u8T1T/tY1dG7doWkIDjFD/z40F4bEQYGrEo1VuEnsURpIZugF9Ahc";

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    
    private static final float mmPerInch        = 25.4f;
    private static final float mmFTCFieldWidth  = (12*6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    private CameraDirection cameraChoice = CameraDirection.FRONT;

    private OpenGLMatrix lastLocation = null;
    
    private boolean targetVisible = false;
    private String nameOfVisibleTarget = "";
    private double relativeRotation = 0.0;
    private String locationOfGoldMineral = "";
    private int goldMineralLeft = -1;
    private double xFromTarget = 0;
    private double yFromTarget = 0;
    
    private boolean activated = false;
    
    public static final String ROVER          = "BLUE-ROVER";
    public static final String FOOTPRINT      = "RED-FOOTPRINT";
    public static final String CRATERS        = "FRONT-CRATERS";
    public static final String SPACE          = "BACK-SPACE";
    public static final String LEFT           = "LEFT";
    public static final String CENTER         = "CENTER";
    public static final String RIGHT          = "RIGHT";
    public static final CameraDirection FRONT = CameraDirection.FRONT;
    public static final CameraDirection BACK  = CameraDirection.BACK;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;
    TFObjectDetector tfod;
    
    
    List<VuforiaTrackable> allTrackables;
    
    VuforiaTrackables targetsRoverRuckus;
    VuforiaTrackable blueRover;
    VuforiaTrackable redFootprint;
    VuforiaTrackable frontCraters;
    VuforiaTrackable backSpace;


    // Change to front / back camera using FRONT and BACK public properties
    public void setCamera(CameraDirection direction) {
        cameraChoice = direction;
    }
    
    // Run before 'waitForStart'
    public boolean init(HardwareMap hardwareMap) {
        // int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(/*cameraMonitorViewId*/);

        parameters.vuforiaLicenseKey = VUFORIA_KEY ;
        parameters.cameraDirection   = cameraChoice;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
        
        blueRover = targetsRoverRuckus.get(0);
        blueRover.setName(ROVER);
        
        redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName(FOOTPRINT);
        
        frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName(CRATERS);
        
        backSpace = targetsRoverRuckus.get(3);
        backSpace.setName(SPACE);

        allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsRoverRuckus);

        OpenGLMatrix blueRoverLocationOnField = OpenGLMatrix
                .translation(0, mmFTCFieldWidth, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0));
        blueRover.setLocation(blueRoverLocationOnField);

        OpenGLMatrix redFootprintLocationOnField = OpenGLMatrix
                .translation(0, -mmFTCFieldWidth, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180));
        redFootprint.setLocation(redFootprintLocationOnField);

        OpenGLMatrix frontCratersLocationOnField = OpenGLMatrix
                .translation(-mmFTCFieldWidth, 0, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90));
        frontCraters.setLocation(frontCratersLocationOnField);

        OpenGLMatrix backSpaceLocationOnField = OpenGLMatrix
                .translation(mmFTCFieldWidth, 0, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90));
        backSpace.setLocation(backSpaceLocationOnField);


        final int CAMERA_FORWARD_DISPLACEMENT  = 110;   // eg: Camera is 110 mm in front of robot center
        final int CAMERA_VERTICAL_DISPLACEMENT = 200;   // eg: Camera is 200 mm above ground
        final int CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES,
                        cameraChoice == FRONT ? 90 : -90, 0, 0));

        for (VuforiaTrackable trackable : allTrackables)
        {
            ((VuforiaTrackableDefaultListener)trackable.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        }
        
        
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        } else {
            return true;
        }
        
        return false;
    }

    // Run to detect new changes. Could be placed in while loop to constantly update
    public void update() {
        if (!activated) {
            targetsRoverRuckus.activate();
            tfod.activate();
            activated = true;
        }

        targetVisible = false;
        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetVisible = true;
                nameOfVisibleTarget = trackable.getName();

                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        if (targetVisible) {
            VectorF translation = lastLocation.getTranslation();

            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            xFromTarget = translation.get(0) / mmPerInch;
            yFromTarget = translation.get(1) / mmPerInch;
            relativeRotation = rotation.thirdAngle;
        }
        
        
        
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {
            for (Recognition r : updatedRecognitions) {
                if (r.getLabel().equals(LABEL_GOLD_MINERAL)) {
                    goldMineralLeft = (int) r.getLeft();
                    break;
                }
            }
            
            if (updatedRecognitions.size() == 3) {
                int goldMineralX = -1;
                int silverMineral1X = -1;
                int silverMineral2X = -1;
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                      goldMineralX = (int) recognition.getLeft();
                    } else if (silverMineral1X == -1) {
                      silverMineral1X = (int) recognition.getLeft();
                    } else {
                      silverMineral2X = (int) recognition.getLeft();
                    }
                }
                if (goldMineralX != 1 && silverMineral1X != -1 && silverMineral2X != -1) {
                    if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                        locationOfGoldMineral = LEFT;
                    } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                        locationOfGoldMineral = CENTER;
                    } else {
                        locationOfGoldMineral = RIGHT;
                    }
                }
            }
        }
    }
    
    // Check if any navigation target is visible
    public boolean isTargetVisible() {
        return targetVisible;
    }
    
    // Check if the given navigation target is visible (using public properties ROVER, FOOTPRINT, CRATERS, and SPACE)
    public boolean isTargetVisible(String pic) {
        return (targetVisible && nameOfVisibleTarget.equals(pic));
    }
    
    // Get the location of the gold mineral (using public properties LEFT, CENTER and RIGHT). May be empty.
    public String getGoldMineralLocation() {
        return locationOfGoldMineral;
    }
    
    public boolean isMineralVisible() {
        return false;
    }
    
    // Get rotation relative to a navigation target. May be 0 by default.
    public double getRelativeRotation() {
        return relativeRotation;
    }
    
    // Get x distance relative
    public double getXFromTarget() {
        return xFromTarget;
    }
    
    // Get y distance relative to a navigation target. May be 0 by default.
    public double getYFromTarget() {
        return yFromTarget;
    }
    
    // Get gold mineral's x location relative to its position on the camera
    public int getGoldMineralLeft() {
        return goldMineralLeft;
    }
}
