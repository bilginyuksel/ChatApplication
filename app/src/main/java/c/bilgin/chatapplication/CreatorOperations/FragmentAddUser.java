package c.bilgin.chatapplication.CreatorOperations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import c.bilgin.chatapplication.R;

public class FragmentAddUser extends Fragment {

    private static FragmentAddUser instance = new FragmentAddUser();
    private Context mContext;
    @SuppressLint("ValidFragment")
    private FragmentAddUser(){}
    public static FragmentAddUser getInstance(){return instance;}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context); this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout lin1 = (LinearLayout)inflater.inflate(R.layout.fragment_add_user,null);
        return lin1;
    }
}
