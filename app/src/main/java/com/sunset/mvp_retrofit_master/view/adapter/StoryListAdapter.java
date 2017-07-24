package com.sunset.mvp_retrofit_master.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.StoryEntity;
import com.sunset.mvp_retrofit_master.view.ui.StoryDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Story List Adapter
 * Created by weiwei on 2017/6/19.
 */
@SuppressWarnings("all")
public class StoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<StoryEntity> mList;

    public StoryListAdapter(Context mContext) {
        this.context = mContext;
        this.mList = new ArrayList<>();
    }

    public StoryListAdapter(List<StoryEntity> list) {
        this.mList = list;
    }

    public void setmList(List<StoryEntity> mList) {
        if (mList == null || mList.size() == 0) {
            return;
        }
        this.mList = mList;
        notifyDataSetChanged();
    }

    /**
     * 绑定页面
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new GankMeiZhiViewHolder(rootView);
    }

    /**
     * 绑定持有者、数据处理
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankMeiZhiViewHolder) {
            GankMeiZhiViewHolder gankMeiZhiViewHolder = (GankMeiZhiViewHolder) holder;
            gankMeiZhiViewHolder.bindItem(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mList == null || mList.size() == 0) ? 0 : mList.size();
    }

    class GankMeiZhiViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_story)
        CardView card_story;
        @Bind(R.id.item_story_img)
        ImageView item_story_img;
        @Bind(R.id.item_story_title)
        TextView item_story_title;

        public GankMeiZhiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(final StoryEntity storyEntity) {
            item_story_title.setText(storyEntity.getTitle());
            Glide.with(context).load(storyEntity.getImages().get(0)).centerCrop().into(item_story_img);

            List<String> images = storyEntity.getImages();
            if (images != null && images.size() > 0) {
                Glide.with(context).load(storyEntity.getImages().get(0)).centerCrop().into(item_story_img);
            }

            // 点击card
            card_story.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", storyEntity.getId());

                    Intent intent = new Intent(context, StoryDetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
