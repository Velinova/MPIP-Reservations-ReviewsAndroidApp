package com.example.reservationreview;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.reservationreview.domain.Reservation;
import com.example.reservationreview.domain.Review;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReviewFragment extends Fragment {
    DatabaseReference reference;
    private Map<String, Object> reviews = new HashMap<String, Object>();
    Button pickFromGallery;
    Button takePhoto;
    Button addReview;
    ImageView reviewImage;
    RatingBar stars;
    EditText review;
    private static final int PICK_FROM_GALLERY_CODE = 123;
    private static final int TAKE_PHOTO_CODE = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_review, container, false);
        pickFromGallery = rootView.findViewById(R.id.pick_from_gallery);
        takePhoto = rootView.findViewById(R.id.take_photo);
        reviewImage = rootView.findViewById(R.id.review_image);
        addReview = rootView.findViewById(R.id.add_review);
        pickFromGallery.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick image"),PICK_FROM_GALLERY_CODE);
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
                }
            }
        });

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference();
                stars = rootView.findViewById(R.id.rating);
                review = rootView.findViewById(R.id.ratingComment);
                String reviewData = review.getText().toString();
                String starsData = String.valueOf(stars.getRating());
                Review newReview = new Review(reviewData, starsData);
                reviews.put(UUID.randomUUID().toString(), newReview);
                reference.child("/reviews").updateChildren(reviews);
                stars.setRating(0);
                review.setText("");
                reviewImage.setImageResource(R.drawable.ic_photo);
                Toast.makeText(getContext(), "You added a review!", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FROM_GALLERY_CODE && resultCode == Activity.RESULT_OK && data !=null){
            Uri imageData = data.getData();
            reviewImage.setImageURI(imageData);
        }
        if (requestCode == TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            reviewImage.setImageBitmap(photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
            else
            {
            }
        }
    }
    //super.onActivityResult(requestCode, resultCode, data);

}
