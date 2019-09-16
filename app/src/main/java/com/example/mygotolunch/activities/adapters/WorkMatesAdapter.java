package com.example.mygotolunch.activities.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.example.mygotolunch.R;
import com.example.mygotolunch.activities.fragments.WorkMatesFragment;
import com.example.mygotolunch.activities.models.User;
import com.example.mygotolunch.activities.views.WorkMatesViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;

public class WorkMatesAdapter extends FirestoreRecyclerAdapter<User, WorkMatesViewHolder> {

    private final RequestManager glide;
    private final String idCurrentUser;

    public interface Listener{
        void onDataChanged();
    }

    private Listener callback;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * com.firebase.ui.firestore.FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public WorkMatesAdapter(@NonNull FirestoreRecyclerOptions<User> options, RequestManager glide, Listener callback, String idCurrentUser) {
        super(options);
        this.glide = glide;
        this.idCurrentUser = idCurrentUser;
        this.callback = callback;

    }

    @Override
    protected void onBindViewHolder(@NonNull WorkMatesViewHolder workMatesViewHolder, int i, @NonNull User user) {
        workMatesViewHolder.updateLayout1(user, glide);
    }

    @NonNull
    @Override
    public WorkMatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkMatesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workmates, parent, false));
    }

    @Override
    public void onDataChanged(){
        super.onDataChanged();
        this.callback.onDataChanged();
    }
}
