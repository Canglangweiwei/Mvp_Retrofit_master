package com.sunset.mvp_retrofit_master.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.GankEntity;
import com.sunset.mvp_retrofit_master.view.ui.GankDetailActivity;
import com.sunset.mvp_retrofit_master.view.ui.PictureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * GankEntity List Adapter
 * Created by weiwei on 2017/6/19.
 */
@SuppressWarnings("all")
public class GankListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<GankEntity> mList;

    public GankListAdapter(Context mContext) {
        this.context = mContext;
        this.mList = new ArrayList<>();
    }

    public GankListAdapter(List<GankEntity> list) {
        this.mList = list;
    }

    public void setmList(List<GankEntity> mList) {
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
                .inflate(R.layout.item_gank, parent, false);
        return new GankViewHolder(rootView);
    }

    /**
     * 绑定持有者、数据处理
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankViewHolder) {
            GankViewHolder gankViewHolder = (GankViewHolder) holder;
            gankViewHolder.bindItem(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mList == null || mList.size() == 0) ? 0 : mList.size();
    }

    class GankViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_meizhi)
        CardView card_meizhi;
        @Bind(R.id.iv_meizhi)
        ImageView iv_meizhi;
        @Bind(R.id.tv_meizhi_title)
        TextView tv_meizhi_title;
        @Bind(R.id.tv_meizhi_desc)
        TextView tv_meizhi_desc;

        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(final GankEntity gank) {
            tv_meizhi_title.setText(gank.getWho());
            tv_meizhi_desc.setText(gank.getDesc());
            Glide.with(context).load(gank.getUrl()).centerCrop().into(iv_meizhi);

            //点击图片
            iv_meizhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = newIntent(context, gank.getUrl(), gank.getDesc());
                    ActivityOptionsCompat optionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                                    iv_meizhi, PictureActivity.TRANSIT_PIC);
                    // Android 5.0+
                    try {
                        ActivityCompat.startActivity((Activity) context, intent, optionsCompat.toBundle());
                    } catch (Exception e) {
                        context.startActivity(intent);
                    }
                }
            });

            // 点击card
            card_meizhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GankDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("gank_detail_info", gank);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    private static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.IMG_URL, url);
        intent.putExtra(PictureActivity.IMG_DESC, desc);
        return intent;
    }
}
