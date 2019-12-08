package com.example.finalproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConfessionAdapter extends RecyclerView.Adapter<ConfessionAdapter.ConfessionViewHolder> {
    public List<Confession> confession;
    private OnItemClicked onClickAtItem;



    public interface OnItemClicked {
        void onClickItemDelete(int position);
        void onClickItemUpdate(int position);
        void onClickItem(int position);

    }

    public void setOnClickAtItem (OnItemClicked onClick) {
        this.onClickAtItem = onClick;
    }

    public ConfessionAdapter(Runnable mainActivity, List<Confession> confessions) {
        confession = confessions;
    }

    @NonNull
    @Override
    public ConfessionAdapter.ConfessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_confession, parent, false);
        return new ConfessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConfessionAdapter.ConfessionViewHolder holder, final int position) {

        Log.d("tag", confession.get(position).getContent());

        holder.tv_confessionTitle.setText(confession.get(position).getTitle());
        holder.tv_confessionContent.setText(confession.get(position).getContent());
        holder.btn_deleteConfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAtItem.onClickItemDelete(position);
            }
        });

        holder.btn_updateContentConfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAtItem.onClickItemUpdate(position);
            }
        });
        holder.tv_confessionContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAtItem.onClickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (confession == null) {
            return 0;
        }
        return confession.size();
    }

    class ConfessionViewHolder extends RecyclerView.ViewHolder {
        TextView tv_confessionTitle;
        TextView tv_confessionContent;
        Button btn_updateContentConfession, btn_deleteConfession;

        public ConfessionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_confessionTitle = itemView.findViewById(R.id.tv_titleConfession);
            tv_confessionContent = itemView.findViewById(R.id.tv_contentConfession);
            btn_updateContentConfession = itemView.findViewById(R.id.btn_updateConfession);
            btn_deleteConfession = itemView.findViewById(R.id.btn_deleteConfession);
        }
    }
}
