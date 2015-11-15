package com.example.researchbeast.myspectrum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.events.StartActivityEvent;
import com.example.researchbeast.myspectrum.models.CardModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    CardModel[] mCardModelArray;

    public CardAdapter(CardModel[] cardList) {
        mCardModelArray = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View itemLayout = inflater.inflate(R.layout.recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardModel card = mCardModelArray[position];

        holder.titleTextView.setText(card.title);
        holder.descriptionTextView.setText(card.description);
        holder.drawableResource.setImageResource(card.drawable);
        holder.drawableResource.setTag(card.drawable);
    }

    @Override
    public int getItemCount() {
        return mCardModelArray.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        @Bind(R.id.item_name_text) TextView titleTextView;
        @Bind(R.id.item_description_text) TextView descriptionTextView;
        @Bind(R.id.item_image) ImageView drawableResource;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }
        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            ImageView imgView = (ImageView) view.findViewById(R.id.item_image);
            EventBus.getDefault().post(new StartActivityEvent((int)imgView.getTag()));
        }
    }
}

