package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/** Adapter that stamps item_pizza.xml once for each pizza in the list. */
public class PizzaMenuAdapter extends RecyclerView.Adapter<PizzaMenuAdapter.ViewHolder> {

    /** Called when a pizza row is tapped. */
    public interface OnItemClickListener {
        void onItemClick(PizzaOption option);
    }

    private final List<PizzaOption> pizzas;
    private final OnItemClickListener listener;

    public PizzaMenuAdapter(List<PizzaOption> pizzas, OnItemClickListener listener) {
        this.pizzas = pizzas;
        this.listener = listener;
    }

    /** Creates a new empty row view from item_pizza.xml. */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pizza, parent, false);
        return new ViewHolder(view);
    }

    /** Fills one row with data from the pizza at this position. */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PizzaOption pizza = pizzas.get(position);
        holder.imageView.setImageResource(pizza.getImageResId());
        holder.nameText.setText(pizza.getName());
        holder.toppingsText.setText(pizza.getToppings());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(pizza));
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    /** Holds references to the views inside one row. */
    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameText;
        final TextView toppingsText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView    = itemView.findViewById(R.id.imagePizza);
            nameText     = itemView.findViewById(R.id.textPizzaName);
            toppingsText = itemView.findViewById(R.id.textToppings);
        }
    }
}
