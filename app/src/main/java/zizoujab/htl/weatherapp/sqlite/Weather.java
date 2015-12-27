package zizoujab.htl.weatherapp.sqlite;

/**
 * Created by CiS Info on 26/12/2015.
 */
public class Weather {

    int _ID;
    String day;
    String description;
    String hightLow ;
    public Weather(){

    }
    
    public Weather(int _ID, String day, String hightLow, String description) {
        this._ID = _ID;
        this.day = day;
        this.hightLow = hightLow;
        this.description = description;
    }

    public Weather( String day, String hightLow, String description) {
        this.day = day;
        this.hightLow = hightLow;
        this.description = description;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHightLow() {
        return hightLow;
    }

    public void setHightLow(String hightLow) {
        this.hightLow = hightLow;
    }
}
