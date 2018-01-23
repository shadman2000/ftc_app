package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class EHardwarek9bot
{
    //Motors
    public DcMotor rightWheelFront;
    public DcMotor leftWheelFront;
    public DcMotor suckInRight;
    public DcMotor suckInLeft;

    //Servo(s)
    public Servo gemArm;
    public Servo lift;
    public Servo leftroller;
    public Servo rightRoller;


    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public EHardwarek9bot() {}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors

        //Wheels
        rightWheelFront  = hwMap.get(DcMotor.class, "rightFront");
        leftWheelFront  = hwMap.get(DcMotor.class, "leftFront");
        leftWheelFront.setDirection(DcMotor.Direction.REVERSE);
        rightWheelFront.setDirection(DcMotor.Direction.REVERSE);

        //Suck-in Wheels
        suckInRight  = hwMap.get(DcMotor.class, "rightTop");
        suckInLeft = hwMap.get(DcMotor.class, "leftTop");
        suckInLeft.setDirection(DcMotor.Direction.REVERSE);

        //Servo(s)
        gemArm = hwMap.get(Servo.class, "jewel");
        lift = hwMap.get(Servo.class, "liftArm");
        leftroller = hwMap.get(Servo.class, "leftroller");
        rightRoller = hwMap.get(Servo.class, "rightRoller");

        // Set all motors to zero power
        rightWheelFront.setPower(0);
        leftWheelFront.setPower(0);
        suckInRight.setPower(0);
        suckInLeft.setPower(0);

        //Servo(s)
        gemArm.setPosition(0.0);              //Needs to be tested
        lift.setPosition(0.0);               //Needs to be tested
        leftroller.setPosition(0.0);          //Needs to be tested
        rightRoller.setPosition(0.0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        suckInRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        suckInLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
