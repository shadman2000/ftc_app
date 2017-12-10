package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareK9bot
{
    //Motors
    public DcMotor rightWheelFront;
    public DcMotor rightWheelBack;
    public DcMotor leftWheelFront;
    public DcMotor leftWheelBack;
    public DcMotor suckInWheelright;
    public DcMotor suckInWheelleft;
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
        leftWheelFront  = hwMap.get(DcMotor.class, "leftWheelFront");
        leftWheelBack  = hwMap.get(DcMotor.class, "leftWheelBack");
        rightWheelFront = hwMap.get(DcMotor.class, "rightWheelFront");
        rightWheelBack = hwMap.get(DcMotor.class, "rightWheelBack");
        leftWheelFront.setDirection(DcMotor.Direction.REVERSE);
        leftWheelBack.setDirection(DcMotor.Direction.REVERSE);
        
        //Suck-in Wheels
        suckInWheelleft  = hwMap.get(DcMotor.class, "suckInWheelLeft");
        suckInWheelright = hwMap.get(DcMotor.class, "suckInWheelRight");
        suckInWheelleft.setDirection(DcMotor.Direction.REVERSE);

        //Elevator
        elevator  = hwMap.get(DcMotor.class, "elevator");

        //Servo(s)
//        gemArm = hwMap.get(Servo.class, "gemArm");
        cubeHolderLeft = hwMap.get(Servo.class, "leftCubeHolder");
        cubeHolderRight = hwMap.get(Servo.class, "rightCubeHolder");

        // Set all motors to zero power
        leftWheelFront.setPower(0);
        leftWheelBack.setPower(0);
        rightWheelFront.setPower(0);
        rightWheelBack.setPower(0);
        suckInWheelleft.setPower(0);
        suckInWheelright.setPower(0);
//        elevator.setPower(0);
        
        //Servo(s)
//        gemArm.setPosition(0);              //Needs to be tested
        cubeHolderLeft.setPosition(0.165);   //Needs to be tested
        cubeHolderRight.setPosition(0.75);   //Needs to be tested


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        suckInWheelleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        suckInWheelright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
