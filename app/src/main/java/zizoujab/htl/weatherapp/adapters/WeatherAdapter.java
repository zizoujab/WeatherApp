package zizoujab.htl.weatherapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zizoujab.htl.weatherapp.R;
import zizoujab.htl.weatherapp.sqlite.Weather;

/**
 * Created by CiS Info on 26/12/2015.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {
    public final String LOG_MESSAGE = "WeatherAdapter" ;

    private Context context = null ;
    private List<Weather> listWeathers = null ;
    private LayoutInflater inflater=null ;

    private  static class ViewHolder {
        public TextView weatherDescription;
        public TextView weatherDay;
        public ImageView image ;

    }

    public WeatherAdapter(Context context, int resource, List<Weather> listWeathers) {
        super(context, resource, listWeathers);
        try {
            this.context = context;
            this.listWeathers = listWeathers;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
            Log.e(LOG_MESSAGE, "" + e.getMessage());
        }
    }

    @Override
    public int getCount() {
        return listWeathers.size();
    }

    @Override
    public Weather getItem(int position) {
        return listWeathers.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        Weather weather = listWeathers.get(position);
         ViewHolder holder;
        try {
            if (convertView == null) {
                view = inflater.inflate(R.layout.list_item_forecast, null);
                holder = new ViewHolder();
                holder.weatherDescription = (TextView) view.findViewById(R.id.description_temp);
                holder.weatherDay = (TextView) view.findViewById(R.id.day);
                holder.image = (ImageView) view.findViewById(R.id.imageView);
                if (weather.getDescription().toUpperCase().contains("SUNNY"))
                    holder.image.setImageResource(R.drawable.sunny);
                else
                    holder.image.setImageResource(R.drawable.thunder);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }



            holder.weatherDay.setText( weather.getDay());
            holder.weatherDescription.setText(weather.getDescription()+" - "+ weather.getHightLow());


        } catch (Exception e) {
            Log.e(LOG_MESSAGE,""+e.getMessage());

        }
        return view;
    }
}