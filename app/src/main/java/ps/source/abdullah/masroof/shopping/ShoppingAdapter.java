package ps.source.abdullah.masroof.shopping;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ps.source.abdullah.masroof.R;

/**
 * Created by abdullah on 2/4/2017.
 */

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private ArrayList items;

    public ShoppingAdapter(ArrayList items) {
        this.items = items;
    }

    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shopping_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(items.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(String item) {
        items.add(item);
        notifyItemInserted(items.size());
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
        }
    }
}
