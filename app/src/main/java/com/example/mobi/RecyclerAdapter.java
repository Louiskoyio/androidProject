package com.example.mobi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobi.models.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder > {

    private ArrayList<Item> shoppingCart;

    public RecyclerAdapter( ArrayList<Item> shoppingCart){

        this.shoppingCart = shoppingCart;


    }




    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item,parent,false);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position)
    {

        Item item = shoppingCart.get(position);
        holder.txtItemName.setText(item.getBrand()+" "+item.getName());
        holder.txtPrice.setText(item.getPrice().toString());


    }

    @Override
    public int getItemCount() {
        return shoppingCart.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder
    {
        TextView txtItemName;
        TextView txtPrice;
        ImageButton btnRemove;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnRemove = itemView.findViewById(R.id.btnRemove);

        }
    }
}
