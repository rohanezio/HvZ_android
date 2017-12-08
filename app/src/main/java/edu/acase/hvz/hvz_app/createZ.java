package edu.acase.hvz.hvz_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

import edu.acase.hvz.hvz_app.api.models.ZombieReportModel;
import edu.acase.hvz.hvz_app.api.requests.ZombieReportRequest;

public class createZ extends BaseActivity {
    protected final Logger logger = new Logger("CreateZ");
    private static final ZombieReportRequest zombieReportRequest = new ZombieReportRequest();
    private ZombieReportModel report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_z);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final LatLng marker = getIntent().getParcelableExtra("location");
        final EditText num = (EditText) findViewById(R.id.title);


        final Button Create = (Button) findViewById(R.id.save);
        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                report = new ZombieReportModel(1); //TODO: FIX ME

                int number = tryParse(num.getText().toString());
                if (number >= 0)
                    report.setNumZombies(number);
                else
                    report.setNumZombies(1);
                report.setLocation(marker);
                report.setTimeSighted(new Date());
                report.setDatabase_id(zombieReportRequest.create(report));

                Intent resultIntent = new Intent(getApplicationContext(), HumanActivity.class);
                startActivity(resultIntent);
            }

        });
    }

    private int tryParse(String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}
