package my.i906.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import my.i906.todolist.R;
import my.i906.todolist.model.Todo;

public class TodoAdapter extends ArrayAdapter<Todo> {

    public TodoAdapter(Context context, List<Todo> list) {
        super(context, R.layout.row_todo_list, list);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_todo_list, parent, false);

        Todo item = this.getItem(position);
        ViewHolder holder = new ViewHolder(view);

        holder.title.setText(item.title);
        holder.description.setText(item.description);

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.row_title)
        TextView title;

        @InjectView(R.id.row_description)
        TextView description;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
