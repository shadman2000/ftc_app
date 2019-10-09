package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="CraterX", group="RhinoAuto")
public class AutoCraterX extends RhinoAuto {
    
    @Override
    public void runOpMode() {
        initAutonomous();
        waitForStart();


        descentOntoGround();

        driveTowardsMinerals();
        locateGoldMineral();
        pushGoldMineral();
        
        // middleMan();
        
        // goToDepot();
        // placeTeamMarker();
        // parkInCrater();
        
        extendSlider();
        putCollectorDown();
        
        
        finish();
    }
    
    // Middle man goooo!!!
    public void middleMan() {
        drive(15);
        
        if (positionOfRobot.equals("left"))
            strafe(-14);
        else if (positionOfRobot.equals("center"))
            strafe(-30);
        else if (positionOfRobot.equals("right"))
            strafe(-46);
            
            
        rotate(-40);
        drive(-10);
        
        rotate(-90);
        drive(-60);
        
        
        putCollectorDown();
        sleep(500);
        putCollectorUp();
        
        
        // rotate(-180);
        // drive(120);
        
        
    }
    
    public void goToDepot() {
        if (positionOfRobot.equals("left"))
            strafe(10);
        else if (positionOfRobot.equals("center"))
            strafe(25);
        else if (positionOfRobot.equals("right"))
            strafe(40);
            
        rotate(-40);
        drive(28);
        
        rotate(90);
    }
    
    public void parkInCrater() {
        drive(-120);
        
    }
    
}
