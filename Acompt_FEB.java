package org.firstinspires.ftc.teamcode.feb2026_lynn;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "ACompt Jan 2026")

public class Acompt_FEB extends LinearOpMode {
    
    private DcMotor backleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor frontleftmotor;
    private DcMotor backrightmotor;
    private DcMotor fRubberWheel; // Front Rubber Band Intake Wheel
    private DcMotor bRubberWheel; // Back Rubber Band Intake Wheel
    private DcMotor launch; // Launch Rubber Wheel set with Gearbox

    @Override
    public void runOpMode() {
        backrightmotor = hardwareMap.get(DcMotor.class, "back right motor");
        backleftmotor = hardwareMap.get(DcMotor.class, "back left motor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "front right motor");
        frontleftmotor = hardwareMap.get(DcMotor.class, "front left motor");
        fRubberWheel = hardwareMap.get(DcMotor.class, "front wheel");
        bRubberWheel = hardwareMap.get(DcMotor.class, "back wheel");
        launch = hardwareMap.get(DcMotor.class, "launch");
        
        backrightmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        frontrightmotor.setDirection(DcMotor.Direction.FORWARD);
        frontleftmotor.setDirection(DcMotor.Direction.FORWARD);

        int mode = 0; // Mode is 0 for hold button down; Mode is 1 for toggle
        boolean inverted = 0; // 0 and 1 correspond to false and true

        boolean toggle_intake = false;
        boolean toggle_launch = false;

        boolean toggleLock_mode = false;
        boolean toggleLock_invert = false;
        
        boolean toggleLock_intake = false;
        boolean toggleLock_launch = false;
        
        waitForStart();
        
        while (opModeIsActive()) {
            
            /* The gamepad has a confusing setting where up/down outputs as "x" and left/right outputs as "y"
            We have our variable x set as left/right and our variable y set as forward/back. 
            Thus, we have a confusing mismatch as shown below as, for some reason, top left is set as (0,0), so the y values range from (-1,0) instead */
            
            double x = -gamepad1.left_stick_y;
            double y = -gamepad1.left_stick_x;
            /* If gamepad 1 is not controlling drivetrain, we give gamepad 2 the capability to use the drivetrain. 
            This allows gamepad 1 to have primary override capability */
            if (x == 0.0 && y == 0.0) {
                x = gamepad2.left_stick_y;
                y = -gamepad2.left_stick_x;
            }
            
            double rx = getRot();
            
            
            if (mode == 0) {
                fRubberWheel.setPower(getIntake())
                bRubberWheel.setPower(getIntake());
                launch.setPower(getLaunch());
            }
            else {
                fRubberWheel.setPower(toggleIntake())
                bRubberWheel.setPower(toggleIntake());
                launch.setPower(toggleLaunch());
            }

            if (gamepad1.left_trigger || gamepad2.left_trigger) {
                if (!toggleLock_mode) {
                    mode++;
                    mode = mode%2;
                    toggleLock_mode = true;
                }
            }
            else {
                toggleLock_mode = false;
            }
            
             if (gamepad1.right_trigger || gamepad2.right_trigger) {
                if (!toggleLock_invert) {
                    invert++;
                    invert = invert%2;
                    toggleLock_invert = true;
                }
            }
            else {
                toggleLock_invert = false;
            }

            if (gamepad1.dpad_up) {
                frontleftmotor.setPower(0.5);
                frontrightmotor.setPower(0.5);
                backrightmotor.setPower(0.5);
                backleftmotor.setPower(0.5);
            }
            else if (gamepad1.dpad_down) {
                frontleftmotor.setPower(-0.5);
                frontrightmotor.setPower(-0.5);
                backrightmotor.setPower(-0.5);
                backleftmotor.setPower(-0.5);
            }
            else if (gamepad1.dpad_left) {
                frontleftmotor.setPower(0.5);
                frontrightmotor.setPower(-0.5);
                backrightmotor.setPower(0.5);
                backleftmotor.setPower(-0.5);
            }
            else if (gamepad1.dpad_right) {
                frontleftmotor.setPower(-0.5);
                frontrightmotor.setPower(0.5);
                backrightmotor.setPower(-0.5);
                backleftmotor.setPower(0.5);
            }
            else if (gamepad2.dpad_up) {
                frontleftmotor.setPower(0.5);
                frontrightmotor.setPower(0.5);
                backrightmotor.setPower(0.5);
                backleftmotor.setPower(0.5);
            }
            else if (gamepad2.dpad_down) {
                frontleftmotor.setPower(-0.5);
                frontrightmotor.setPower(-0.5);
                backrightmotor.setPower(-0.5);
                backleftmotor.setPower(-0.5);
            }
            else if (gamepad2.dpad_left) {
                frontleftmotor.setPower(0.5);
                frontrightmotor.setPower(-0.5);
                backrightmotor.setPower(0.5);
                backleftmotor.setPower(-0.5);
            }
            else if (gamepad2.dpad_right) {
                frontleftmotor.setPower(-0.5);
                frontrightmotor.setPower(0.5);
                backrightmotor.setPower(-0.5);
                backleftmotor.setPower(0.5);
            }
            else {
                frontleftmotor.setPower((y-x+rx)/2);
                frontrightmotor.setPower((y+x-rx)/2);
                backleftmotor.setPower((y+x+rx)/2);
                backrightmotor.setPower((y-x-rx)/2);
            }

            if (invert == 1) {
                fRubberWheel.setDirection(DcMotor.Direction.REVERSE);
                bRubberWheel.setDirection(DcMotor.Direction.REVERSE);
                launch.setDirection(DcMotor.Direction.REVERSE);
            }
            else {
                fRubberWheel.setDirection(DcMotor.Direction.FORWARD);
                bRubberWheel.setDirection(DcMotor.Direction.FORWARD);
                launch.setDirection(DcMotor.Direction.FORWARD);
            }

            if (mode == 0) {
                telemetry.addData("Mode", "Hold Down");
            }
            else {
                telemetry.addData("Mode", "Toggle");
            }
            if (inverted) {
                telemetry.addData("Inverted", "True");
            }
            else {
                telemetry.addData("Inverted", "False");
            }
            telemetry.update();

        }
    }

    private double getRot()
    {
        double out = 0;
        if (gamepad1.left_trigger > 0.1)
        {
            out = -gamepad1.left_trigger; // value from [-1, 0)

        }
        else if (gamepad1.right_trigger > 0.1)
        {
            out = gamepad1.right_trigger; // value from (0, 1]
        }
        return out;
        
    }

    /*
        <x>: intake and launch
        <b>: launch
        <a>: intake
    */
    private double getIntake() {
        if (gamepad1.a || gamepad1.x) {
            return 0.5;
        }
        else if (gamepad2.a || gamepad2.x) {
            return 0.5;
        }
        else {
            return 0;
        }
    }

    private double getLaunch() {
        if (gamepad1.b || gamepad1.x) {
            return 1;
        }
        else if (gamepad2.b || gamepad2.x) {
            return 1;
        }
        else {
            return 0;
        }
    }

    private double toggleIntake() {
        if (!toggleLock_intake) {
           if (gamepad1.x || gamepad1.a || gamepad2.x || gamepad2.a) {
               if (toggle_intake) {
                   toggle_intake = false;
               }
               else {
                   toggle_intake = true;
               }
               toggleLock_intake = true;
           }
        }
        else {
            toggleLock_intake = false;
        }

        if (toggle_intake) {
            return 0.5;
        }
        else {
            return 0;
        }
    }

     private double toggleLaunch() {
        if (!toggleLock_launch) {
           if (gamepad1.x || gamepad1.a || gamepad2.x || gamepad2.a) {
               if (toggle_launch) {
                   toggle_launch = false;
               }
               else {
                   toggle_launch = true;
               }
               toggleLock_launch = true;
           }
        }
        else {
            toggleLock_launch = false;
        }

        if (toggle_launch) {
            return 1;
        }
        else {
            return 0;
        }
    }
        
    /*
    private double getFront()
    {
        double out = 0;
        double mode = 0;
        if (gamepad1.b)
        {
            mode = mode+1;
            mode = mode %2;
        }
        if (mode == 1)
        {
            out = 0.5;
        }    
        else
        {
            out = 0;
        }
        return out;
    }
    private double getBack()
    {
        double out = 1;
        double mode = 0;
        if (gamepad1.a)
        {
            mode = mode+1;
            mode = mode %2;
        }
        if (mode == 1)
        {
            out = 0;
        }    
        else
        {
            out = 1;
        }
        return out;
    }
    
    private double getLaunch()
    {
        double out = 0;
        double mode = 0;
        if (gamepad1.x)
        {
            mode = mode+1;
            mode = mode %2;
        }
        if (mode == 1)
        {
            out = 1;
        }    
        else
        {
            out = 0;
        }
        return out;
    }
    */
}








