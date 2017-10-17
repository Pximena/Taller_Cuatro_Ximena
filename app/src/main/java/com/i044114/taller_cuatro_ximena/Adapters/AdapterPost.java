package com.i044114.taller_cuatro_ximena.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i044114.taller_cuatro_ximena.CommentsActivity;
import com.i044114.taller_cuatro_ximena.Models.Post;
import com.i044114.taller_cuatro_ximena.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 16/10/2017.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {

    List<Post> postList = new ArrayList<>();
    Context context;
    // Constructor de la clase
    public AdapterPost(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }
    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Configuracion del ViewAdapter
// Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
// Pasar la vista (item.xml) al ViewHolder
        AdapterPost.ViewHolder viewHolder = new AdapterPost.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPost.ViewHolder holder, int position) {
// Encargado de trabajar con el item.xml y sus componentes
        String[] lista1;

        lista1 = new String[5];
        lista1[0]= "https://i.pinimg.com/originals/66/c2/05/66c2054f5ebd2accf20532e2eff8efdf.jpg";
        lista1[1] = "https://i.pinimg.com/originals/f7/b6/0e/f7b60e8e4377da7855719e51b48369de.jpg";
        lista1[2] = "https://i.pinimg.com/originals/9f/6a/2a/9f6a2a0a29dd85d9bb1fea97c4d6d18d.jpg";
        lista1[3] = "https://i.pinimg.com/originals/1c/87/e8/1c87e8aed89d12775b1e51e268c2e1e2.jpg";
        lista1[4] = "https://i.pinimg.com/originals/02/11/b4/0211b40233493c18f3b1bb9a52d5225f.jpg";

        int random = (int) (Math.random()*5);

        Picasso.with(context).load(lista1[random]).into(holder.imageView);

        holder.textViewuserid.setText(Integer.toString(postList.get(position).getUserid()));
        holder.textViewid.setText(Integer.toString(postList.get(position).getId()));
        holder.textViewtitle.setText(postList.get(position).getTitle());
        holder.textViewbody.setText(postList.get(position).getBody());
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewuserid;
        TextView textViewid;
        TextView textViewtitle;
        TextView textViewbody;
        ImageView imageView;


        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);
            textViewuserid = (TextView) item.findViewById(R.id.tv_user_id);
            textViewid = (TextView) item.findViewById(R.id.tv_id);
            textViewtitle = (TextView) item.findViewById(R.id.tv_title);
            textViewbody = (TextView) item.findViewById(R.id.tv_body);
            imageView = (ImageView) item.findViewById(R.id.img2);

        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, CommentsActivity.class);
            intent.putExtra("pantallapost", postList.get(getLayoutPosition()).getId());
            context.startActivity(intent);

        }
    }

}
