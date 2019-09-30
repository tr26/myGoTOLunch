package com.example.mygotolunch.activities.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.adapters.WorkMatesAdapter;
import com.example.mygotolunch.activities.api.UserHelper;
import com.example.mygotolunch.activities.models.User;
import com.example.mygotolunch.activities.views.WorkMatesViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkMatesFragment extends Fragment implements WorkMatesAdapter.Listener {

    @BindView(R.id.recyclerViewWorkMates)
    RecyclerView recyclerView;

    private WorkMatesAdapter mWorkMatesAdapter;


    public WorkMatesFragment() {
        // Required empty public constructor
    }

    public static WorkMatesFragment newInstance() {
        return (new WorkMatesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_work_mates, container, false);

        ButterKnife.bind(this, v);

        //this.mWorkMatesAdapter = new WorkMatesAdapter()
        configureRecyclerView();


        return v;
    }

    @Override
    public void onDataChanged() {

    }

    private void configureRecyclerView(){

        this.mWorkMatesAdapter = new WorkMatesAdapter(generateOptionsForAdapter(
                        UserHelper.getUsersCollection()),
                        Glide.with(this),
                this,
                        this.getCurrentUser().getUid());

        mWorkMatesAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                recyclerView.smoothScrollToPosition(mWorkMatesAdapter.getItemCount()); // Scroll to bottom on new messages
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mWorkMatesAdapter);
    }

    private FirestoreRecyclerOptions<User> generateOptionsForAdapter(Query query){
        return new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .setLifecycleOwner(this)
                .build();
    }

    protected OnFailureListener mOnFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show();
            }
        };
    }

    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }
}
