package com.spaceinvaders.triciasfu.spaceinvaders;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import processing.android.CompatUtils;
import processing.android.PFragment;
import processing.core.PApplet;


public class MainActivity extends AppCompatActivity {
    private PApplet sketch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frame = new FrameLayout(this);
        frame.setId(CompatUtils.getUniqueViewId());
        setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        sketch = new Sketch();
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (sketch != null) {
            sketch.onRequestPermissionsResult(
                    requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (sketch != null) {
            sketch.onNewIntent(intent);
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
