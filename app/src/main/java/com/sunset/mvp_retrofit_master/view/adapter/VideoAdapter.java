package com.sunset.mvp_retrofit_master.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.config.AppConfig;
import com.sunset.mvp_retrofit_master.model.entity.VideoDetailEntity;
import com.sunset.mvp_retrofit_master.model.entity.VideoEntity;
import com.sunset.mvp_retrofit_master.view.ui.VideoDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author  weiwei
 * Description video列表adapter
 */
@SuppressWarnings("ALL")
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context context;

    private List<VideoEntity.Data.DataBean> list;

    public VideoAdapter() {
        super();
    }

    public VideoAdapter(List<VideoEntity.Data.DataBean> data) {
        this.list = data;
    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        VideoAdapter.VideoViewHolder holder = new VideoAdapter.VideoViewHolder(view);
        view.setTag(holder);
        return new VideoAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.VideoViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refresh(List<VideoEntity.Data.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.layout_video)
        FrameLayout videoLayout;
        @Bind(R.id.showview)
        RelativeLayout showView;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.from)
        TextView from;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.image_bg)
        ImageView iv;

        VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void update(final int position) {
            final VideoEntity.Data.DataBean.GroupBean group = list.get(position).getGroup();
            if (group != null) {
                title.setText(group.getText());
                type.setText(group.getCategory_name());
                from.setText(group.getPlay_count() + "次播放");
                if (group.getLarge_cover().getUrl_list().get(0).getUrl().contains("http")) {
                    Glide.with(context).load(group.getLarge_cover().getUrl_list().get(0).getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                } else {
                    Glide.with(context).load(AppConfig.url + group.getLarge_cover().getUrl_list().get(0).getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                }
                showView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VideoDetailEntity entity = new VideoDetailEntity();
                        entity.path = group.getMp4_url();
                        entity.name = group.getText();
                        Intent intent = new Intent(context, VideoDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", entity);
                        intent.putExtras(bundle);
                        ((Activity) context).startActivity(intent);
                    }
                });
            }
        }
    }
}
