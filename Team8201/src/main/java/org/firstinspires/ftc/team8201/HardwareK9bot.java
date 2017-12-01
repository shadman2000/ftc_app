package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareK9bot
{
    //Motors
    public DcMotor rightWheel;
    public DcMotor leftWheel;
    public DcMotor suckInWheelright;
    public DcMotor suckInWheeleft;
    public DcMotor elevator;
    
    //Servo(s)
    public Servo gemArm;
    public Servo cubeHolderLeft;
    public Servo cubeHolderRight;


    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareK9bot() {}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors
        
        //Wheels
        leftWheel  = hwMap.get(DcMotor.class, "leftWheel");
        rightWheel = hwMap.get(DcMotor.class, "rightWheel");
        leftWheel.setDirection(DcMotor.Direction.REVERSE);
        
        //Suck-in Wheels
        suckInWheeleft  = hwMap.get(DcMotor.class, "suckInLeftWheel");
        suckInWheelright = hwMap.get(DcMotor.class, "suckInWheelright");
        suckInWheeleft.setDirection(DcMotor.Direction.REVERSE);
        
        //Elevator
        elevator  = hwMap.get(DcMotor.class, "elevator");
        
        //Servo(s)
        gemArm = hwMap.get(Servo.class, "gemArm");
        cubeHolderLeft = hwMap.get(Servo.class, "leftCubeHolder");
        cubeHolderRight = hwMap.get(Servo.class, "rightCubeHolder");

        // Set all motors to zero power
        leftWheel.setPower(0);
        rightWheel.setPower(0);
        suckInWheeleft.setPower(0);
        suckInWheelright.setPower(0);
        elevator.setPower(0);
        
        //Servo(s)
        gemArm.setPosition(0);              //Needs to be tested
        cubeHolderLeft.setPosition(0.18);   //Needs to be tested
        cubeHolderRight.setPosition(0.2);   //Needs to be tested


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        suckInWheeleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        suckInWheelright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
