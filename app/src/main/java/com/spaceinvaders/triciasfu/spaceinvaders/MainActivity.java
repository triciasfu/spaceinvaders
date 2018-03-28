package com.spaceinvaders.triciasfu.spaceinvaders;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

import processing.android.CompatUtils;
import processing.android.PFragment;

import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;
import static processing.core.PConstants.SHIFT;



public class MainActivity extends AppCompatActivity {
    private SpaceInvaders b;
    private static final String RED_PIN_NAME = "GPIO6_IO14";
    private static final String GREEN_PIN_NAME = "GPIO19_IO14";
    private static final String BLUE_PIN_NAME = "GPIO26_IO14";
    private Gpio mButtonGpioRed;
    private Gpio mButtonGpioGreen;
    private Gpio mButtonGpioBlue;

    Button buttonA;
    Button buttonB;
    Button buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.Theme_AppCompat_NoActionBar);


        FrameLayout frame = new FrameLayout(this);
        frame.setId(CompatUtils.getUniqueViewId());
        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        b = new SpaceInvaders();
        PFragment fragment = new PFragment(b);
        fragment.setView(frame, this);

        try {
            buttonA = RainbowHat.openButtonA();
            buttonB = RainbowHat.openButtonB();
            buttonC = RainbowHat.openButtonC();
            buttonA.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean pressed) {
                    if (pressed) {
                        b.keyPressed = true;
                        b.keyCode = LEFT;
                    } else {
                        b.keyPressed = false;
                    }
                }
            });
            buttonB.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean pressed) {
                    if (pressed) {
                        b.keyPressed = true;
                        b.keyCode = SHIFT;
                    } else {
                        b.keyPressed = false;
                    }
                }
            });
            buttonC.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean pressed) {
                    if (pressed) {
                        b.keyPressed = true;
                        b.keyCode = RIGHT;
                    } else {
                        b.keyPressed = false;
                    }
                }
            });
        } catch (IOException e) {
            Log.d("I AHATE AMDK:", "SUCKS");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (b != null) {
            b.onRequestPermissionsResult(
                    requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (b != null) {
            b.onNewIntent(intent);
        }
    }
}

//import android.app.Activity;
//import android.os.Bundle;
//
///**
// * Skeleton of an Android Things activity.
// * <p>
// * Android Things peripheral APIs are accessible through the class
// * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
// * set it to HIGH:
// * <p>
// * <pre>{@code
// * PeripheralManagerService service = new PeripheralManagerService();
// * mLedGpio = service.openGpio("BCM6");
// * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
// * mLedGpio.setValue(true);
// * }</pre>
// * <p>
// * For more complex peripherals, look for an existing user-space driver, or implement one if none
// * is available.
// *
// * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
// */
//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
