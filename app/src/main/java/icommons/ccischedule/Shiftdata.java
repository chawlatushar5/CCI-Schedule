package icommons.ccischedule;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tushar on 7/21/16.
 */
public class Shiftdata {
    private String Start_time;
    private String End_time;
    private int user_id;
    private int shift_id;
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStart_time() {
        return Start_time;
    }

    public void setStart_time(String start_time) {
        Start_time = start_time;
    }

    public String getEnd_time() {
        return End_time;
    }

    public void setEnd_time(String end_time) {
        End_time = end_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int account_id) {
        this.user_id = account_id;
    }

    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }


}
