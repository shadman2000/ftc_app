package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class mechard
{
    //Motors
    public DcMotor rightWheelFront;
    public DcMotor rightWheelBack;
    public DcMotor leftWheelFront;
    public DcMotor leftWheelBack;
    public DcMotor collectorLeft;
    public DcMotor collectorRight;

    public Servo liftLeft;
    public Servo liftRight;
    public Servo flip;
    public Servo cop;
    public Servo gem;


    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public mechard() {}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors

        //Wheels
        leftWheelFront  = hwMap.get(DcMotor.class, "lf");
        leftWheelBack  = hwMap.get(DcMotor.class, "lb");
        rightWheelFront = hwMap.get(DcMotor.class, "rf");
        rightWheelBack = hwMap.get(DcMotor.class, "rb");

        collectorLeft = hwMap.get(DcMotor.class, "cleft");
        collectorRight = hwMap.get(DcMotor.class, "cright");

        gem = hwMap.get(Servo.class,"gemArm");
        liftLeft = hwMap.get(Servo.class, "lleft"); // hl 0.47      0.92        0.37
        liftRight = hwMap.get(Servo.class, "lright"); // hr 0.52    0.075       0.62
        flip = hwMap.get(Servo.class, "flipper");
        // cop = hwMap.get(Servo.class, "cp");

        leftWheelFront.setDirection(DcMotor.Direction.REVERSE);
        leftWheelBack.setDirection(DcMotor.Direction.REVERSE);
        collectorRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftWheelFront.setPower(0);
        leftWheelBack.setPower(0);
        rightWheelFront.setPower(0);
        rightWheelBack.setPower(0);
        collectorLeft.setPower(0);
        collectorRight.setPower(0);
        // liftLeft.setPosition(0.37);
        // liftRight.setPosition(0.62);
        // cop.setPosition(0.0);



        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
