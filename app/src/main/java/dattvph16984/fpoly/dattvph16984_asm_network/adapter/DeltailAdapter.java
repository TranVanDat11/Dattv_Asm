package dattvph16984.fpoly.dattvph16984_asm_network.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.net.ParseException;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.BinhLuanDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.DTO.TruyenDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.R;

public class DeltailAdapter extends RecyclerView.Adapter<DeltailAdapter.ViewHolder>{
    private ArrayList<BinhLuanDTO> list;
    private Context mContext;

    public void setData(ArrayList list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public DeltailAdapter(Context mContext, ArrayList<BinhLuanDTO> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DeltailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DeltailAdapter.ViewHolder viewHolder = new DeltailAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_binh_luan, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeltailAdapter.ViewHolder holder, int position) {
        BinhLuanDTO binhLuanDTO = list.get(position);
        holder.tv_cmt.setText("Bình Luận: "+binhLuanDTO.getNoiDung());
        try {
            holder.tv_time_cmt.setText("Thời gian: "+binhLuanDTO.getNgayGioBinhLuan());
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cmt,tv_time_cmt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cmt = itemView.findViewById(R.id.tv_binhLuan);
            tv_time_cmt = itemView.findViewById(R.id.tv_timebinhLuan);
        }
    }
}
