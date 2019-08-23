package com.example.mygotolunch.activities.api;

import com.example.mygotolunch.activities.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class UserHelper {

    private static final String COLLECTION_NAME = "users";

    //COLLECTION REFERENCE
    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    //CREATE
    public static Task<Void> createUser(String uid, String username, String urlPicture){
        User userToCreate = new User(uid, username, urlPicture);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }

    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }
    public static Task<Void> updateUsername(String username, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateHasMadeHisChoice(String uid, Boolean hasMadeHisChoice) {
        return UserHelper.getUsersCollection().document(uid).update("hasMAdeHisChoice", hasMadeHisChoice);
    }
    // --- DELETE ---

    public static Task<Void> deleteUser(String uid) {
        return UserHelper.getUsersCollection().document(uid).delete();
    }

    // ---QUERY---
    public static Query getAllUser(){
        return UserHelper.getUsersCollection()
                .document("users")
                .collection("users")
                .limit(50);
    }
}
