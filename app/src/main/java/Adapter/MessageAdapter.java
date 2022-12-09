package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ft_hangout.R;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<String>{

    private Context context;
    private ArrayList<String> list;
    private TextView chatText;

    public MessageAdapter(@NonNull Context context, ArrayList<String> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        String currentRow = list.get(position);

        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (currentRow.contains("SENDBY\r")) {
                row = inflater.inflate(R.layout.item_send, parent, false);
                chatText = (TextView) row.findViewById(R.id.msgr);
                chatText.setText(currentRow.substring(7));
            } else {
                row = inflater.inflate(R.layout.item_receive, parent, false);
                chatText = (TextView) row.findViewById(R.id.msgr);
                if (currentRow.length() > 10) {
                    chatText.setText(currentRow.substring(10));
                }else{
                    chatText.setText(currentRow);
                }
            }
        return row;
    }

}
