package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ft_hangout.R;

import java.util.ArrayList;
import Pojo.RowContactList;

public class ListContactAdapter extends ArrayAdapter<RowContactList>{

    private Context context;
    private ArrayList<RowContactList> list;

    public ListContactAdapter(@NonNull Context context, ArrayList<RowContactList> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if (listItem == null)
                listItem = LayoutInflater.from(context).inflate(R.layout.list_contact,parent,false);

            RowContactList currentRow = list.get(position);

//            ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
//            image.setImageResource(currentMovie.getmImageDrawable());

            TextView name = (TextView) listItem.findViewById(R.id.tv_contact_list);
            name.setText(currentRow.getName());

            TextView phone = (TextView) listItem.findViewById(R.id.tv_phone_list);
            phone.setText(currentRow.getPhone());

            return listItem;
        }

}
