package edu.acase.hvz.hvz_app.api.models;

import com.google.android.gms.maps.model.LatLng;
import java.util.Date;


public class HumanReportModel extends BaseReportModel {
    private int numHumans, typicalMagSize;

    public final static class SERIALIZATION extends BaseReportModel.SERIALIZATION {
        public final static String
                ARRAY_KEY = "human_reports",
                NUM_HUMANS = "num_humans",
                TYPICAL_MAG_SIZE = "typical_mag_size";
    }

    public HumanReportModel(int DATABASE_ID) {
        super(DATABASE_ID);
    }

    public int getNumHumans() { return numHumans; }
    public int getTypicalMagSize() { return typicalMagSize; }

    public void setNumHumans(int numHumans) { this.numHumans = numHumans; }
    public void setTypicalMagSize(int typicalMagSize) { this.typicalMagSize = typicalMagSize; }

    @Override
    public String snippet() {
        return "Num Zombies = " + numHumans + "\n" +
                "Time Sighted = " + timeSighted + "\n" +
                "Typical Mag size = " + typicalMagSize + "\n";
    }

    @Override
    public String toString() {
        return "HumanReportModel{" +
                "DATABASE_ID=" + DATABASE_ID +
                ", location=" + location +
                ", numHumans=" + numHumans +
                ", typicalMagSize=" + typicalMagSize +
                ", timeSighted=" + timeSighted +
                '}';
    }
}
