package com.example.ehar.accelerometercs450;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity
implements Observer {

    public static final String XKEY = "X";
    public static final String YKEY = "Y";
    public static final String ZKEY = "Z";
    public static final String LONGKEY = "LONGITUDE";
    public static final String LATKEY = "LATITUDE";

    TextView x_accel_view = null;
    TextView y_accel_view = null;
    TextView z_accel_view = null;
    TextView longitude_view = null;
    TextView latitude_view = null;

    float [] xyz;
    String x,y,z,longi,lat;


    private AccelerometerHandler ah = null;
    private LocationHandler lh = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.x_accel_view =
                (TextView) findViewById(R.id.x_accel_view);
        this.y_accel_view =
                (TextView) findViewById(R.id.y_accel_view);
        this.z_accel_view =
                (TextView) findViewById(R.id.z_accel_view);
        this.longitude_view =
                (TextView) findViewById(R.id.longitude_view);
        this.latitude_view=
                (TextView) findViewById(R.id.latitude_view);

        this.ah = new AccelerometerHandler(this);
        this.lh = new LocationHandler(this);
        this.ah.addObserver(this);
        this.lh.addObserver(this);

        x = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        y = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        z = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        longi = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        lat = getPreferences(MODE_PRIVATE).getString(XKEY,null);

        x_accel_view.setText(x);
        y_accel_view.setText(y);
        z_accel_view.setText(z);
        longitude_view.setText(longi);
        latitude_view.setText(lat);


    }

    @Override
    protected void onStart() {
        super.onStart();
        this.ah = new AccelerometerHandler(this);
        this.lh = new LocationHandler(this);
        this.ah.addObserver(this);
        this.lh.addObserver(this);

        x = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        y = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        z = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        longi = getPreferences(MODE_PRIVATE).getString(XKEY,null);
        lat = getPreferences(MODE_PRIVATE).getString(XKEY,null);

        x_accel_view.setText(x);
        y_accel_view.setText(y);
        z_accel_view.setText(z);
        longitude_view.setText(longi);
        latitude_view.setText(lat);
    }

    @Override
    public void update(Observable observable, Object o) {

            if(observable instanceof AccelerometerHandler){
                xyz = (float []) o;
                this.x_accel_view.setText(Float.toString(xyz[0]));
                this.y_accel_view.setText(Float.toString(xyz[1]));
                this.z_accel_view.setText(Float.toString(xyz[2]));
            }else{
                this.latitude_view.setText(Double.toString(lh.getLatitude()));
                this.longitude_view.setText(Double.toString(lh.getLongitude()));
            }






    }
    @Override
    public void onPause(){
        super.onPause();

        getPreferences(MODE_PRIVATE).edit().putString(XKEY,Float.toString(xyz[0])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(YKEY,Float.toString(xyz[1])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(ZKEY,Float.toString(xyz[2])).apply();
        getPreferences(MODE_PRIVATE).edit().putString(LONGKEY,Double.toString(lh.getLongitude())).apply();
        getPreferences(MODE_PRIVATE).edit().putString(LATKEY,Double.toString(lh.getLatitude())).apply();
    }
}
