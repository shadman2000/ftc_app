package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DepotX", group="RhinoAuto")
public class AutoDepotX extends RhinoAuto {
    
    @Override
    public void runOpMode() {
        initAutonomous();
        waitForStart();
        
        
        rotate(90); // predicted: right
        
        // descentOntoGround();
        // driveTowardsMinerals();
        // locateGoldMineral();
        // pushGoldMineral();
        
        // goToDepot();
        // placeTeamMarker();
        // parkInCrater();
        
        
        finish();
    }
    
    
    public void goToDepot() {
        if (positionOfRobot.equals("left"))
            strafe(-5);
        else if (positionOfRobot.equals("center"))
            strafe(-10);
        else if (positionOfRobot.equals("right"))
            strafe(-35);
            
        rotate(-40);
        drive(32);
        
        rotate(90);
        drive(40);
    }
    
    public void parkInCrater() {
        drive(-120);
    }
    
}
