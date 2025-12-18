package org.firstinspires.ftc.teamcode.feb2026_lynn;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Auto February 2026")


public class AutoMain_FEB extends LinearOpMode {
    private DcMotor backleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor frontleftmotor;
    private DcMotor backrightmotor;
    
    

    // todo: write your code here
    public void runOpMode() {
        backrightmotor = hardwareMap.get(DcMotor.class, "back right motor");
        backleftmotor = hardwareMap.get(DcMotor.class, "back left motor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "front right motor");
        frontleftmotor = hardwareMap.get(DcMotor.class, "front left motor");
        // linear code goes here
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
    
    
}


