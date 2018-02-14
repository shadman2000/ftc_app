package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="MecTime", group="Pushbot")
//@Disabled
public class mecAutoTimeFront extends LinearOpMode{

    mechard robot = new mechard();
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        forward(5);
        backward(5);
        right(5);
        left(5);

        robot.leftWheelFront.setPower(0);
        robot.leftWheelBack.setPower(0);
        robot.rightWheelFront.setPower(0);
        robot.rightWheelBack.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
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
