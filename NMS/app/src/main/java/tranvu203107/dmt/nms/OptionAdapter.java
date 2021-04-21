package tranvu203107.dmt.nms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OptionAdapter extends ArrayAdapter<Option> {
    private Context context;
    private int layout;
    private List<Option> list;

    public OptionAdapter(Context context, int layout, List<Option> list) {
        super(context, layout, list);
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Option getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView textViewOption;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OptionAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new OptionAdapter.ViewHolder();

            // map
            viewHolder.textViewOption = (TextView) convertView.findViewById(R.id.textViewOption);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (OptionAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.textViewOption.setText(list.get(position).title);

        return convertView;
    }
}
