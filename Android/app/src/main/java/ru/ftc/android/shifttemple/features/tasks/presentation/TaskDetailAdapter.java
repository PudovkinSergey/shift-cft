package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;

final class TaskDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Task task;
    private final List<Bid> bids = new ArrayList<>();
    private final LayoutInflater inflater;
    private boolean checkBidSelected;
    private final SelectBidListener selectBidListener;

    TaskDetailAdapter(Context context, SelectBidListener selectBidListener) {
        inflater = LayoutInflater.from(context);
        this.selectBidListener = selectBidListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View itemView = inflater.inflate(R.layout.task_item, parent, false);
            return new TaskHolder(itemView);
        } else {
            View itemView = inflater.inflate(R.layout.bid_item, parent, false);
            return new BidHolder(itemView, selectBidListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == 0) {
            TaskHolder taskHolder = (TaskHolder) holder;
            taskHolder.bind(task);
        } else {
            BidHolder bidHolder = (BidHolder) holder;
            bidHolder.bind(bids.get(position - 1), task);
        }
    }

    @Override
    public int getItemCount() {
        if (task == null) {
            return bids.size();
        } else {
            return 1 + bids.size();
        }
    }

    public void setBids(List<Bid> bidsList) {
        bids.clear();
        bids.addAll(bidsList);



        notifyDataSetChanged();
    }

    public void setTask(Task task) {
        this.task = task;
        notifyDataSetChanged();
    }

    class BidHolder extends RecyclerView.ViewHolder {
        private final TextView bidUserNameView;
        private final TextView bidTextView;
        private final TextView bidDateView;
        private TextView bidPhone;
        private TextView bidTgLink;
        private TextView bidVkLink;
        private TextView bidEmail;
        private final SelectBidListener selectBidListener;

        BidHolder(View view, SelectBidListener selectBidListener) {
            super(view);
            this.selectBidListener = selectBidListener;
            bidUserNameView = view.findViewById(R.id.bid_item_username);
            bidTextView = view.findViewById(R.id.bid_item_text);
            bidDateView = view.findViewById(R.id.bid_item_date);
            bidPhone = view.findViewById(R.id.bid_phone);
            bidTgLink = view.findViewById(R.id.bid_tg_link);
            bidVkLink = view.findViewById(R.id.bid_vk_link);
            bidEmail = view.findViewById(R.id.bid_email);
        }

        void bind(final Bid bid, final Task task) {
            bidUserNameView.setText(bid.getUserName());
            bidTextView.setText(bid.getText());
            bidDateView.setText(bid.getDate());
            if (bid.getId() != null && task.getChosedBid() != null &&
                    task.getChosedBid().equals(bid.getId())) {
                bidPhone.setText(bid.getPhone());
                bidTgLink.setText(bid.getTgLink());
                bidVkLink.setText(bid.getVkLink());
                bidEmail.setText(bid.getEmail());
                bidPhone.setVisibility(View.VISIBLE);
                bidTgLink.setVisibility(View.VISIBLE);
                bidVkLink.setVisibility(View.VISIBLE);
                bidEmail.setVisibility(View.VISIBLE);

                itemView.setBackgroundResource(R.color.colorBidIsSelected);
            } else {
                bidPhone.setVisibility(View.GONE);
                bidTgLink.setVisibility(View.GONE);
                bidVkLink.setVisibility(View.GONE);
                bidEmail.setVisibility(View.GONE);
                itemView.setBackgroundResource(R.color.colorBidIsntSelected);
            }
            if (!checkBidSelected) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectBidListener.onBidSelect(bid);
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        selectBidListener.onBidLongClick(bid);
                        return true;
                    }
                });
            } else {
                itemView.setOnClickListener(null);
                itemView.setOnLongClickListener(null);
            }
        }
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private final TextView taskTitleView;
        private final TextView taskDescriptionView;
        private final TextView taskDateView;

        TaskHolder(View view) {
            super(view);

            taskTitleView = view.findViewById(R.id.task_item_title);
            taskDescriptionView = view.findViewById(R.id.task_item_description);
            taskDateView = view.findViewById(R.id.task_item_date);
        }

        void bind(final Task task) {
            taskTitleView.setText(task.getTitle());
            taskDescriptionView.setText(task.getDescription());
            taskDateView.setText(task.getDate());
        }
    }

    interface SelectBidListener {

        void onBidSelect(Bid bid);

        void onBidLongClick(Bid bid);

        void onBidFinishTaskClicked(Bid bid);

    }
}

