package dontbefa.hcmut.dontbefa;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

/**
 * Created by danhk on 29-Oct-16.
 */
public class Server {
    private static final String FIREBASE_ADDRESS = "https://dont-be-fa.firebaseio.com/";

    private FirebaseAuth auth;

    public Server(){
        //
    }
    public void register(String email, String password){
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {


                }
                else
                {

                }

            }
        });

    }

    public void signIn(String email, String password)
    {
        final boolean[] result = {false};
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                }
                else
                {

                }
            }
        });


    }

    public boolean checkUserSession()
    {
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            return true;
        }
        return false;
    }
    public void signOut(){
        auth = FirebaseAuth.getInstance();
        auth.signOut();
    }
    public void pushData( float lg,float lat,int old, int description,Bitmap hinhAnh)
    {

        //cần có long, lat, old, des, hinh

        //User cần hình,
        auth = FirebaseAuth.getInstance();


        String uid = auth.getCurrentUser().getUid();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("list").child(uid).child("long").setValue(lg);
        database.getReference("list").child(uid).child("lat").setValue(lat);
        database.getReference("list").child(uid).child("old").setValue(old);
        database.getReference("list").child(uid).child("description").setValue(description);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        hinhAnh.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference(uid);
        storageReference.putBytes(byteArray);



    }





}
