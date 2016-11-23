package de.uulm.vs.gossip;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GossipsActivity extends AppCompatActivity implements AddPostDialogFragment.AddPostDialogListener {

    private GossipApplication app;
    private ArrayList<String> listStrings;
    private PostsAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gossips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new dialog
                AddPostDialogFragment addChallengeDialog = new AddPostDialogFragment();

                addChallengeDialog.setListener(GossipsActivity.this);

                // Show the dialog
                addChallengeDialog.show(getFragmentManager(),
                        "AddPostDialog");
            }
        });

        if (app == null)
            app = (GossipApplication) getApplication();

        // TODO Add this activity as listener

        // TODO Get references to the CRDTs

        listView = (ListView) findViewById(R.id.posts_listview);

        // TODO Load the posts into the ArrayList
        listStrings = new ArrayList<String>() {{
            add("Test post 1");
            add("test post 2");
        }};

        // Instantiate custom adapter
        adapter = new PostsAdapter(listStrings, this);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gossips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Call the Application's onResume */
    @Override
    protected void onResume() {
        super.onResume();
        app.onResume();
    }

    /* Call the Application's onPause */
    @Override
    protected void onPause() {
        super.onPause();
        app.onPause();
    }

    @Override
    public void addPost(String newPost) {

        // TODO Add the post together with its Voter

        // Update the list view
        updateListView();
    }

    private void updateListView() {
        listStrings.clear();

        // TODO Re-add the posts

        this.adapter.notifyDataSetChanged();
    }

    /**
     * Custom ListAdapter for Posts
     */
    private class PostsAdapter extends BaseAdapter implements ListAdapter {
        private ArrayList<String> list = new ArrayList<String>();
        private Context context;

        /**
         * Constructs a new PostAdapter instance with the passed list of strings.
         *
         * @param list    The list of posts as strings
         * @param context The context of the Activity
         */
        public PostsAdapter(ArrayList<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.post_list_item, null);
            }

            // Get TextViews references
            TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
            TextView listItemRatingText = (TextView) view.findViewById(R.id.list_item_rating);

            // Add the post content
            listItemText.setText(list.get(position));

            // TODO Get the total voted value of this post (as String)
            String votedValueTotal = "0";

            listItemRatingText.setText(votedValueTotal);

            // Handle buttons and add onClickListeners
            Button upvoteBtn = (Button) view.findViewById(R.id.upvote_btn);
            Button downvoteBtn = (Button) view.findViewById(R.id.downvote_btn);

            // TODO Get the voted value of the current client (as Integer: 1 = upvoted, -1 = downvoted, 0 = no vote);
            // Adjust the button colors depending on the user's vote
            int votedValue = 0;
            if (votedValue == 1) {
                upvoteBtn.setTextColor(ContextCompat.getColor(context, R.color.button_material_dark));
            } else {
                upvoteBtn.setTextColor(ContextCompat.getColor(context, R.color.button_material_light));
            }
            if (votedValue == -1) {
                downvoteBtn.setTextColor(ContextCompat.getColor(context, R.color.button_material_dark));
            } else {
                downvoteBtn.setTextColor(ContextCompat.getColor(context, R.color.button_material_light));
            }

            upvoteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO Upvote

                    notifyDataSetChanged();
                }
            });
            downvoteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO Downvote

                    notifyDataSetChanged();
                }
            });

            return view;
        }
    }
}
