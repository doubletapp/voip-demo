package ru.doubletapp.voipdemo.userlist.presentation;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.doubletapp.voipdemo.R;
import ru.doubletapp.voipdemo.userlist.data.model.UserModel;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    @NonNull
    private List<UserModel> mUsers = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final Context context = parent.getContext();
        return new UserViewHolder(LayoutInflater.from(context).inflate(
                R.layout.recycler_item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.bind(mUsers.get(i));
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setUsers(@NonNull List<UserModel> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_user_avatar_initials)
        TextView mInitialsView;

        @BindView(R.id.item_user_name)
        TextView mNameView;

        @BindView(R.id.item_user_status)
        ImageView mStatusView;

        @BindDrawable(R.drawable.user_status_online)
        Drawable mOnlineStatus;

        @BindDrawable(R.drawable.user_status_offline)
        Drawable mOfflineStatus;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull UserModel model) {
            mInitialsView.setText(getInitials(model.getName()));
            mNameView.setText(model.getName());
            mStatusView.setBackground(model.isOnline() ? mOnlineStatus : mOfflineStatus);
        }

        private String getInitials(String name) {
            Pattern p = Pattern.compile("((^| )[A-Za-z])");
            Matcher m = p.matcher(name);
            StringBuilder initials = new StringBuilder();
            while (m.find()) {
                initials.append(m.group().trim());
            }
            return initials.toString();
        }
    }
}
