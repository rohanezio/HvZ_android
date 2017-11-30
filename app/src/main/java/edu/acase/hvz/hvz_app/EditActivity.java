package edu.acase.hvz.hvz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Date;

import edu.acase.hvz.hvz_app.api.models.HumanReportModel;

import static android.text.format.DateUtils.SECOND_IN_MILLIS;

class EditActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final LatLng latlng = (LatLng) getIntent().getParcelableExtra("location");

        final EditText title = (EditText) findViewById(R.id.title);
        final EditText time = (EditText) findViewById(R.id.time);
        final EditText mag = (EditText) findViewById(R.id.mag);
        final EditText loc = (EditText) findViewById(R.id.lat);
        final long temp = Calendar.getInstance().getTimeInMillis();
        final Date timespot = Calendar.getInstance().getTime();

        Button boton = (Button) findViewById(R.id.save);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MarkerOptions marker = new MarkerOptions().position(latlng);
                time.setText(timespot.toString());//autofill time
                loc.setText(latlng.toString());//autofill position

                int number, magazine;
                number = Integer.parseInt(title.getText().toString());//number of humans
                magazine = Integer.parseInt(mag.getText().toString());//number of darts  in mag
                //time since last update
                CharSequence s = DateUtils.getRelativeTimeSpanString(temp, (long) (System.currentTimeMillis()), SECOND_IN_MILLIS);


                marker.title("  ").snippet(setT(marker, temp));

                Intent resultIntent = new Intent();
                resultIntent.putExtra("marker", marker);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }

        });
    }
    public String setT (MarkerOptions marker, long temp){
        return DateUtils.getRelativeTimeSpanString(temp, (long) (System.currentTimeMillis()), SECOND_IN_MILLIS).toString();
    }
}