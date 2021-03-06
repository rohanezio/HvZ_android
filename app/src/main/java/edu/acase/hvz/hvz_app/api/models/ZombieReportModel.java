package edu.acase.hvz.hvz_app.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import java.util.Date;

/** The model representing zombie reports. This is essentially a java clone of the human report
 * object stored in the server
 * @see BaseReportModel the abstract base model */

public class ZombieReportModel extends BaseReportModel {
    private int numZombies;

    /** The serialization details for this report. These are the "magic strings"
     * in the json returned from the server, and are used to serialize and deserialize
     * reports when communicating with the server.
     * @see BaseReportModel.SERIALIZATION*/
    public final static class SERIALIZATION extends BaseReportModel.SERIALIZATION {
        public final static String
                ARRAY_KEY = "zombie_reports",
                SINGLE_KEY = "zombie_report",
                NUM_ZOMBIES = "num_zombies";
    }

    public ZombieReportModel(int DATABASE_ID, int GAME_ID) {
        super(DATABASE_ID, GAME_ID);
    }
    public ZombieReportModel(int GAME_ID) {
        super(GAME_ID);
    }

    public int getNumZombies() { return numZombies; }

    public void setNumZombies(int numZombies) { this.numZombies = numZombies; }
    public LatLng getLoc(){return location;}

    /** Get the contents of this report
     * @return the contents to display in marker popup dialogs
     */
    @Override
    public String getReportContents() {
        return "Num Zombies = " + numZombies + "\n" +
                "Time Sighted = " + getTimeSinceSighted() + "\n";
    }

    //base overrides

    @Override
    public String toString() {
        return "ZombieReportModel{" +
                "database_id=" + database_id +
                ", GAME_ID=" + GAME_ID +
                ", location=" + location +
                ", numZombies=" + numZombies +
                ", timeSighted=" + timeSighted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ZombieReportModel that = (ZombieReportModel) o;

        return numZombies == that.numZombies;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numZombies;
        return result;
    }

    //parcelling methods

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(GAME_ID);
        dest.writeInt(database_id);
        dest.writeParcelable(location, flags);
        dest.writeLong(timeSighted != null ? timeSighted.getTime() : -1L);
        dest.writeInt(numZombies);
    }

    public static final Parcelable.Creator<ZombieReportModel> CREATOR = new Parcelable.Creator<ZombieReportModel>() {
        @Override
        public ZombieReportModel createFromParcel(Parcel in) {
            ZombieReportModel report = new ZombieReportModel(in.readInt());
            report.database_id = in.readInt();
            report.location = in.readParcelable(LatLng.class.getClassLoader());
            long tmpTimeSighted = in.readLong();
            report.timeSighted = tmpTimeSighted != -1 ? new Date(tmpTimeSighted) : null;
            report.numZombies = in.readInt();
            return report;
        }

        @Override
        public ZombieReportModel[] newArray(int size) {
            return new ZombieReportModel[size];
        }
    };
}
