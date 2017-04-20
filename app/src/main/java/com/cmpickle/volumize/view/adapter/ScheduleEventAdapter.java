package com.cmpickle.volumize.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
 */

public class ScheduleEventAdapter extends RecyclerView.Adapter<ScheduleEventAdapter.EventHolder> {

    ArrayList<ScheduleEvent> events = new ArrayList<>();

    public ScheduleEventAdapter(ArrayList<ScheduleEvent> events) {
        this.events = events;
    }

    public static class EventHolder extends ViewHolder implements View.OnClickListener {

        ScheduleEvent event;
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

        public EventHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindEvent(ScheduleEvent event) {
            this.event = event;
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
            if(((event.getDays()>>>1)&1)==1)
                tvMondayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            if(((event.getDays()>>>2)&1)==1)
                tvTuesdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            if(((event.getDays()>>>3)&1)==1)
                tvWednesdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            if(((event.getDays()>>>4)&1)==1)
                tvThursdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            if(((event.getDays()>>>5)&1)==1)
                tvFridayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
            if(((event.getDays()>>>6)&1)==1)
                tvSaturdayItem.setTextColor(view.getResources().getColor(R.color.colorAccent));
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
                case 4:
                    type = view.getResources().getString(R.string.common_notifications);
                    break;
                case 8:
                    type = view.getResources().getString(R.string.system);
                    break;
                default:
                    type = "error";
                    break;
            }
            tvScheduleItemType.setText(type);
        }

        @Override
        public void onClick(View v) {

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
        holder.bindEvent(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
