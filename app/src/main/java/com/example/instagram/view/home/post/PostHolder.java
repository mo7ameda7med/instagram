package com.example.instagram.view.home.post;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.R;
import com.example.instagram.models.Post;
import com.squareup.picasso.Picasso;

public class PostHolder extends RecyclerView.ViewHolder {

    private ImageView imgAccount;
    private TextView tvName;
    private ImageView imgPost;
    private TextView tvTitle;
    private TextView tvDate;

    public ImageView imgLike;
    private TextView tvNumberOfLikes;

    public PostHolder(@NonNull View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        imgAccount = itemView.findViewById(R.id.itemPostImageProfile);
        tvName = itemView.findViewById(R.id.itemPostName);
        imgPost = itemView.findViewById(R.id.itemPostImage);
        tvTitle = itemView.findViewById(R.id.itemPostTitle);
        tvDate = itemView.findViewById(R.id.itemPostDate);

        imgLike = itemView.findViewById(R.id.itemPostLike);
        tvNumberOfLikes = itemView.findViewById(R.id.itemPostNumberOfLike);
    }

    void bindView(Post post) {
//        Picasso.get()
//                .load(post.getUser().getImage())
//                .placeholder(R.drawable.img_placeholder)
//                .into(imgAccount);

        Picasso.get()
                .load(post.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imgPost);

        tvName.setText(post.getUser().getName());
        tvTitle.setText(post.getTitle());
        tvDate.setText(post.getDate());

//        String likes = post.getNumberOfLikes() + " Persons";
//        tvNumberOfLikes.setText(likes);
    }
}
