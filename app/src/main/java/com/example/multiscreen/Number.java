package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.multiscreen.R.layout.list_item;

public class Number extends AppCompatActivity {
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //release the mediaplayer
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mMediaPlayer.start();
                    }
                }
            };
    MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);



        final ArrayList<Word> words = new ArrayList<Word>();//Since arraylist stores only objects Word is the object of the Word class.

        Word w = new Word("one", "lutti", R.drawable.number_one, R.raw.number_one);
        words.add(w);

        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        Log.v("Number", "word at index 0: " + words.get(0));

        //This was the linear layout we used
        // now we try to use array adapter + listview to get the same result

//        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.rootview);
//
//        for(int i=0;i<words.size();i++){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//
//            rootLayout.addView(wordView);
//
//        }

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(this, words, R.color.number_);//we need wordadapter because arrayadapter by default
        //returns a textview but in our case we have more tahn one text view so we also want to return the modified view.
//        ArrayAdapter<String> itemsadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        //
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);
        //final AudioManager.OnAudioFocusChangeListener finalAfChangeListener = afChangeListener;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word a = words.get(position);
                Log.v("Number", "abc" + a.toString());

                //to release media player activity in case it is not been released by the complete listeneer.
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Now we have the audio focus
                    mMediaPlayer = MediaPlayer.create(Number.this, a.getaudioresourceid());
                    mMediaPlayer.start();

                    //to realese the mediaplayer resource when the media is complete
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });

                }


                //Toast.makeText(Number.this, number, Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            //regardless of whether or not we were granted audio focus, abandon it.This also
            //unregisters the audiofocuscjangelistener so we wont get any callback
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);

        }
    }


}
