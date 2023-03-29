package dattvph16984.fpoly.dattvph16984_asm_network.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.TruyenDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.DetailTruyen;
import dattvph16984.fpoly.dattvph16984_asm_network.R;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.ViewHolder> {
    private ArrayList<TruyenDTO> list;
    private Context mContext;
    public void setData(ArrayList list) {
        this.list = list;
        notifyDataSetChanged();
    }
        public TruyenAdapter(Context mContext, ArrayList<TruyenDTO> list) {
        this.list = list;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public TruyenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_truyen, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenAdapter.ViewHolder holder, int position) {
        TruyenDTO truyenDTO = list.get(position);
        holder.tv_idTruyen.setText("ID: "+truyenDTO.getId());
        holder.tv_tenTruyen.setText("Tên truyện: "+truyenDTO.getTenTruyen());
        holder.tv_moTa.setText("Mô tả: "+truyenDTO.getMoTaNgan());
        holder.tv_tenTG.setText("Tên tác giả: "+truyenDTO.getTenTacGia());
        holder.tv_namSX.setText("Năm sản xuất: "+truyenDTO.getNamSanXat());

        Glide.with(mContext)
                .load(truyenDTO.getAnhBia())
                .centerCrop()
                .into(holder.img_anhBia);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailTruyen.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("truyen", truyenDTO);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        public TextView tv_tenTruyen,tv_moTa,tv_tenTG,tv_namSX,tv_idTruyen;
        ImageView img_anhBia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idTruyen = itemView.findViewById(R.id.tv_idTruyen);
            tv_tenTruyen = itemView.findViewById(R.id.tv_tenTruyen);
            tv_moTa = itemView.findViewById(R.id.tv_mota);
            tv_tenTG = itemView.findViewById(R.id.tv_tenTG);
            tv_namSX = itemView.findViewById(R.id.tv_namSX);
            img_anhBia = itemView.findViewById(R.id.img_anhBia);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }
}
