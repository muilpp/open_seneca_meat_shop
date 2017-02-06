package com.meatshop.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.meatshop.BuildConfig;
import com.meatshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewFlipperHelper {
    private final static String TAG = ViewFlipperHelper.class.getName();
    private static float initialX;

    public static ViewFlipper init(final ViewFlipper viewFlipper, View startView, View stopView) {
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float currentX = motionEvent.getX();
                        //right to left
                        if (initialX < currentX) {
                            if (viewFlipper.getDisplayedChild() == 0)
                                break;
                            viewFlipper.setInAnimation(viewFlipper.getContext(), R.anim.in_from_left);
                            viewFlipper.setOutAnimation(viewFlipper.getContext(), R.anim.out_from_right);
                            viewFlipper.showPrevious();
                        }

                        //left to right
                        if (initialX > currentX) {
                            if (viewFlipper.getDisplayedChild() == viewFlipper.getChildCount()-1)
                                break;
                            viewFlipper.setOutAnimation(viewFlipper.getContext(), R.anim.out_from_left);
                            viewFlipper.setInAnimation(viewFlipper.getContext(), R.anim.in_from_right);
                            viewFlipper.showNext();
                        }
                        break;
                }
                return true;
            }
        });

        startView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setAutoStart(true);
                viewFlipper.setFlipInterval(3000);
                viewFlipper.startFlipping();
            }
        });

        stopView.findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.stopFlipping();
            }
        });

        return viewFlipper;
    }

    public static void addTwitterStatus(final ViewFlipper viewFlipper, List<TwitterStatus> statusList) {

        final LayoutInflater inflater = (LayoutInflater) viewFlipper.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (final TwitterStatus status : statusList) {
            final View view = inflater.inflate(R.layout.tweet_item_view, null);
            TextView tweetText = (TextView)view.findViewById(R.id.tweet_text);
            ImageView profileImage = (ImageView)view.findViewById(R.id.tweet_profile_image);
            TextView tweetDate = (TextView)view.findViewById(R.id.tweet_date);
            TextView tweetName = (TextView)view.findViewById(R.id.tweet_name);
            TextView tweetLocation = (TextView)view.findViewById(R.id.tweet_location);

            tweetText.setText(status.getText());
            tweetText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tweetIntent= new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TWITTER_URL+"/"+status.getUser().getScreenName()));
                    viewFlipper.getContext().startActivity(tweetIntent);
                }
            });

            tweetDate.setText(status.getDateCreated().substring(0, status.getDateCreated().lastIndexOf(":")));
            tweetName.setText(status.getUser().getName());
            tweetLocation.setText(status.getUser().getLocation());

            Picasso.with(viewFlipper.getContext())
                .load(status.getUser().getProfileImageURL().replace("_normal",""))
                .resize(200, 200)
                .into(profileImage);

            viewFlipper.addView(view);
        }
   }
}