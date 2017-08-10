package com.cmpickle.volumize.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.data.entity.ScheduleEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cmpickle on 4/19/17.
 *
 */

public class ScheduleEventAdapter extends RecyclerView.Adapter<ScheduleEventAdapter.EventHolder> {

    private final ArrayList<ScheduleEvent> events;
    private final OnItemClickListener onItemClickListener;
    private final OnItemCheckListener onItemCheckListener;

    public interface OnItemClickListener {
        void onItemClick(ScheduleEvent scheduleEvent);
    }

    public interface OnItemCheckListener {
        void onItemCheck(ScheduleEvent scheduleEvent);
    }

    public ScheduleEventAdapter(ArrayList<ScheduleEvent> events, OnItemClickListener onItemClickListener, OnItemCheckListener onItemCheckListener) {
        this.events = events;
        this.onItemClickListener = onItemClickListener;
        this.onItemCheckListener = onItemCheckListener;
    }

    public static class EventHolder extends ViewHolder {

        View view;
        @BindView(R.id.checkbox_schedule_item)
        CheckBox checkBoxActive;
        @BindView(R.id.tv_schedule_item_time)
        TextView tvScheduleItemTime;
        @BindView(R.id.tv_sunday_item)
        TextView tvSundayItem;
        @BindView(R.id.tv_monday_item)
        TextView tvMondayItem;
        @BindView(R.id.tv_tuesday_item)
        TextView tvTuesdayItem;
        @BindView(R.id.tv_wednesday_item)
        TextView tvWednesdayItem;
        @BindView(R.id.tv_thursday_item)
        TextView tvThursdayItem;
        @BindView(R.id.tv_friday_item)
        TextView tvFridayItem;
        @BindView(R.id.tv_saturday_item)
        TextView tvSaturdayItem;
        @BindView(R.id.tv_schedule_item_volume)
        TextView tvScheduleItemVolume;
        @BindView(R.id.tv_schedule_item_type)
        TextView tvScheduleItemType;
        @BindView(R.id.iv_schedule_item)
        ImageView ivScheduleItem;
        @BindView(R.id.iv_start_vibrate)
        ImageView ivStartVibrate;
        @BindView(R.id.iv_end_vibrate)
        ImageView ivEndVibrate;
        @BindView(R.id.recyclerview_item_divider)
        View dividerView;

        public EventHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindEvent(ScheduleEvent event, OnItemClickListener onItemClickListener, OnItemCheckListener onItemCheckListener) {
            checkBoxActive.setChecked(event.isActive());
            SimpleDateFormat format = new SimpleDateFormat("h:mm a");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, event.getHour());
            calendar.set(Calendar.MINUTE, event.getMinute());
            String formattedDate = format.format(calendar.getTime());
            tvScheduleItemTime.setText(formattedDate);
            if((event.getDays()&1)==1)
                tvSundayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvSundayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            if(((event.getDays()>>>1)&1)==1)
                tvMondayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvMondayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            if(((event.getDays()>>>2)&1)==1)
                tvTuesdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvTuesdayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            if(((event.getDays()>>>3)&1)==1)
                tvWednesdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvWednesdayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            if(((event.getDays()>>>4)&1)==1)
                tvThursdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvThursdayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            if(((event.getDays()>>>5)&1)==1)
                tvFridayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvFridayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            if(((event.getDays()>>>6)&1)==1)
                tvSaturdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            else
                tvSaturdayItem.setTextColor(view.getResources().getColor(android.R.color.white));
            tvScheduleItemVolume.setText(String.format("Volume %d", event.getAmount()));
            String type;
            switch (event.getOption()) {
                case 0:
                    type = view.getResources().getString(R.string.all);
                    break;
                case 1:
                    type = view.getResources().getString(R.string.common_ring_tone);
                    break;
                case 2:
                    type = view.getResources().getString(R.string.media);
                    break;
                case 3:
                    type = view.getResources().getString(R.string.common_notifications);
                    break;
                case 4:
                    type = view.getResources().getString(R.string.system);
                    break;
                default:
                    type = "error";
                    break;
            }
            tvScheduleItemType.setText(type);
            if(event.getAmount() != 0 || event.isVibrate()) {
                ivStartVibrate.setVisibility(View.VISIBLE);
                ivEndVibrate.setVisibility(View.VISIBLE);
            } else {
                ivStartVibrate.setVisibility(View.INVISIBLE);
                ivEndVibrate.setVisibility(View.INVISIBLE);
            }
            if(event.getAmount()==0)
                ivScheduleItem.setImageResource(R.drawable.ic_mute);
            else
                ivScheduleItem.setImageResource(R.drawable.ic_volume);
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(event));
            checkBoxActive.setOnClickListener(v -> onItemCheckListener.onItemCheck(event));
        }
    }
    @Override
    public ScheduleEventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new EventHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ScheduleEventAdapter.EventHolder holder, int position) {
        ScheduleEvent event = events.get(position);
        if(position == getItemCount()-1) {
            holder.dividerView.setVisibility(View.INVISIBLE);
        } else {
            holder.dividerView.setVisibility(View.VISIBLE);
        }
        holder.bindEvent(event, onItemClickListener, onItemCheckListener);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
