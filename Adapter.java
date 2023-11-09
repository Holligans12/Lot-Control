package Image.To.Text;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userlist;
    private ItemClickListener listener;

    public Adapter(List<ModelClass> userlist, ItemClickListener listener) {
        this.userlist = userlist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass modelClass = userlist.get(position);
        int resource = modelClass.getImageviewgrommet();
        String name = modelClass.getTextviewgrommet();
        String pn = modelClass.getTextviewpngrommet();
        String line = modelClass.getDivider();

        holder.setData(resource, name, pn, line);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(modelClass);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public void filterList(ArrayList<ModelClass> filteredList) {
        userlist = filteredList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClick(ModelClass modelClass);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageviewgrommet;
        private TextView textviewgrommet;
        private TextView textviewpngrommet;
        private TextView divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageviewgrommet = itemView.findViewById(R.id.imageviewgrommet);
            textviewgrommet = itemView.findViewById(R.id.textviewgrommet);
            textviewpngrommet = itemView.findViewById(R.id.textviewpngrommet);
            divider = itemView.findViewById(R.id.divider);
        }

        public void setData(int resource, String name, String pn, String line) {
            imageviewgrommet.setImageResource(resource);
            textviewgrommet.setText(name);
            textviewpngrommet.setText(pn);
            divider.setText(line);
        }

    }
}
