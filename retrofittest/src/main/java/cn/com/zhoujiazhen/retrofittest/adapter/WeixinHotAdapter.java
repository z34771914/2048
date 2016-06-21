package cn.com.zhoujiazhen.retrofittest.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.com.zhoujiazhen.retrofittest.R;
import cn.com.zhoujiazhen.retrofittest.model.WeixinHotModel;

/**
 * Created by zhoujiazhen on 16/5/26.
 */
public class WeixinHotAdapter extends RecyclerView.Adapter<WeixinHotAdapter.ViewHolder>
        implements View.OnClickListener {
    private final String TAG = "WeixinHotAdapter";

    private List<WeixinHotModel.NewslistEntity> mList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public WeixinHotAdapter(List<WeixinHotModel.NewslistEntity> list) {
        mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weixin_hot, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);

        convertView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeixinHotModel.NewslistEntity entity = mList.get(position);
        holder.tvTitle.setText(entity.getTitle());
        holder.tvTime.setText(entity.getCtime());

        holder.imgIcon.setImageURI(Uri.parse(entity.getPicUrl()));

        holder.itemView.setTag(entity);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.OnItemClick(v, (WeixinHotModel.NewslistEntity) v.getTag());
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, WeixinHotModel.NewslistEntity entity);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;

        public SimpleDraweeView imgIcon;
        public TextView tvTitle;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            imgIcon = (SimpleDraweeView) itemView.findViewById(R.id.news_icon);
            tvTitle = (TextView) itemView.findViewById(R.id.news_title);
            tvTime = (TextView) itemView.findViewById(R.id.news_time);
        }
    }

}
