package org.firstinspires.ftc.teamcode.feb2026_lynn;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;


@Autonomous(name = "Auto January 2026 - Red")

public class AutoMain_FEB extends LinearOpMode {
    private DcMotor backleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor frontleftmotor;
    private DcMotor backrightmotor;
    private DcMotor fRubberWheel; // Front Rubber Band Intake Wheel
    private DcMotor bRubberWheel; // Back Rubber Band Intake Wheel
    private DcMotor launch; // Launch Rubber Wheel set with Gearbox
    private OpenCvWebcam webcam; //Hopely Webcam but we need to check with Aarit 

    private ElapsedTime clock = new ElapsedTime();

    @Override
    public void runOpMode() {
        backrightmotor = hardwareMap.get(DcMotor.class, "back right motor");
        backleftmotor = hardwareMap.get(DcMotor.class, "back left motor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "front right motor");
        frontleftmotor = hardwareMap.get(DcMotor.class, "front left motor");
        fRubberWheel = hardwareMap.get(DcMotor.class, "front wheel");
        bRubberWheel = hardwareMap.get(DcMotor.class, "back wheel");
        launch = hardwareMap.get(DcMotor.class, "launch");
        webcam = hardwareMap.get(DcMotor.class,"Webcam 1"); //Need to change the class it comes from but I don't know which 

        // We set these based on how the motors are physically installed
        backrightmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        frontrightmotor.setDirection(DcMotor.Direction.FORWARD);
        frontleftmotor.setDirection(DcMotor.Direction.FORWARD);
        
        waitForStart();
        if (opModeIsActive()) {
            clock.reset();
                
            //Using Clock ~1-2 Seconds of Forward To Reach the Front Row of Balls  
            moveForward();
            while(clock.seconds()<=1.5){
            }
            stopMotors();
            
            clock.reset();
            turnLeft();
            while(clock.seconds()<=1){
            }
            stopMotors();
            
            clock.reset();
            moveForward();
            while(clock.seconds()<=1.5){
            }
            stopMotors();
            
            clock.reset();
            startIntakes();
            moveForward();
            while(clock.seconds()<=1.5){
            }
            stopMotors();
            stopIntakes();
            clock.reset();
            turnRight();
            while(clock.second()<=1){
            }
            stopMotors();
            clock.reset();
            moveForward();
            while(clock.seconds()<=3.5){ //idk how long to make it so, I just did this for now 
            }
            stopMotors();
            clock.reset();


        }
        
    }
    
    public void stopMotors() {
        backleftmotor.setPower(0);
        backrightmotor.setPower(0);
        frontleftmotor.setPower(0);
        frontrightmotor.setPower(0);
    }
    
    public void moveForward() {
        backleftmotor.setPower(1);
        backrightmotor.setPower(1);
        frontleftmotor.setPower(1);
        frontrightmotor.setPower(1);
    }
    
    public void moveBackward() {
        backleftmotor.setPower(-1);
        backrightmotor.setPower(-1);
        frontleftmotor.setPower(-1);
        frontrightmotor.setPower(-1);
    }
    
    public void strafeLeft() {
        backleftmotor.setPower(-1);
        frontleftmotor.setPower(1);
        backrightmotor.setPower(1);
        frontrightmotor.setPower(-1);
    }
    
    public void strafeRight() {
        backleftmotor.setPower(1);
        frontleftmotor.setPower(-1);
        backrightmotor.setPower(-1);
        frontrightmotor.setPower(1);
    }

    public void turnLeft() {
        backleftmotor.setPower(0);
        frontleftmotor.setPower(0);
        backrightmotor.setPower(0.5);
        frontrightmotor.setPower(0.5);

    }

    public void turnRight() {
        backleftmotor.setPower(0.5);
        frontleftmotor.setPower(0.5);
        backrightmotor.setPower(0);
        frontrightmotor.setPower(0);

    }

     public void startIntakes()
    {
        fRubberWheel.setPower(0.5);
        bRubberWheel.setPower(0.5);
    }

    public void stopIntakes()
    {
        fRubberWheel.setPower(0);
        bRubberWheel.setPower(0);
    }
    
    
}


