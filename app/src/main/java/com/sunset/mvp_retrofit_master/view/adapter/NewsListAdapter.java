package com.sunset.mvp_retrofit_master.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunset.mvp_retrofit_master.R;
import com.sunset.mvp_retrofit_master.model.entity.NewsEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressWarnings("ALL")
public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<NewsEntity.Result.Data> list;
    private OnCardItemClickLitener mOnCardItemClickLitener;

    public NewsListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setOnItemClickLitener(OnCardItemClickLitener mOnItemClickLitener) {
        this.mOnCardItemClickLitener = mOnItemClickLitener;
    }

    public void setList(List<NewsEntity.Result.Data> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newslist, parent, false);
        return new MyHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder testViewHolder = (MyHolder) holder;
            testViewHolder.bindItem(position, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_news)
        CardView card_news;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_date)
        TextView tv_date;
        @Bind(R.id.iv)
        ImageView iv;

        private int position;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(final int position, final NewsEntity.Result.Data bean) {
            setTitle(bean.getTitle());
            setDate(bean.getDate());
            setImg(bean.getThumbnail_pic_s());

            // 点击card
            if (mOnCardItemClickLitener != null) {
                card_news.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnCardItemClickLitener.onItemClick(view, position);
                    }
                });
            }
        }

        /**
         * 设置标题
         *
         * @param title 标题
         */
        public void setTitle(String title) {
            if (null == tv_title) return;
            tv_title.setText(title + "\n");
        }

        /**
         * 设置日期
         *
         * @param date 日期
         */
        public void setDate(String date) {
            if (null == tv_date) return;
            tv_date.setText(date);
        }

        /**
         * 设置图标
         *
         * @param imgUrl 图片路径
         */
        public void setImg(String imgUrl) {
            if (null == iv) return;
            Glide.with(context).load(imgUrl)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(iv);
        }
    }

    public interface OnCardItemClickLitener {
        void onItemClick(View view, int position);
    }
}
