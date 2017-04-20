package com.cmpickle.volumize.view.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.adapter.ScheduleEventAdapter;
import com.eyeem.recyclerviewtools.adapter.WrapAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ScheduleFragment extends BaseFragment implements ScheduleView {

    @Inject
    SchedulePresenter schedulePresenter;

    @BindView(R.id.fab_schedule)
    FloatingActionButton fabSchedule;
    @BindView(R.id.recyclerview_schedule)
    RecyclerView recyclerViewSchedules;
    @BindView(R.id.tv_schedule_empty)
    TextView tvScheduleEmptyState;

    public ScheduleFragment() {
        Injector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.Adapter adapter = new ScheduleEventAdapter(schedulePresenter.getEvents());
        WrapAdapter wrapAdapter = new WrapAdapter(adapter);
        wrapAdapter.addFooter(getLayoutInflater(savedInstanceState).inflate(R.layout.footer, recyclerViewSchedules, false));
        recyclerViewSchedules.setAdapter(wrapAdapter);

        schedulePresenter.setRouter((ScheduleRouter) getActivity());

        fabSchedule.setOnClickListener(v -> schedulePresenter.addScheduleClicked());
    }

    @Override
    public void onResume() {
        super.onResume();
        schedulePresenter.onResume();
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        schedulePresenter.setView(this);
        schedulePresenter.setRouter((ScheduleRouter) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return schedulePresenter;
    }

    @Override
    public void updateAdapter() {
        recyclerViewSchedules.getAdapter().notifyDataSetChanged();
        updateViewState();
    }

    public void updateViewState() {
        if(recyclerViewSchedules.getAdapter().getItemCount()>0)
            tvScheduleEmptyState.setVisibility(View.GONE);
        else
            tvScheduleEmptyState.setVisibility(View.VISIBLE);
    }
}
