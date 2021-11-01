package com.example.kiddies;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//class NumberAdapter extending recycler view adapter
public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {

    //object of NumberList
    NumberList[] myLists;

    //declaring variable pos
    private int pos;

    //NumberAdapter constructor
    public NumberAdapter(NumberList[] numberLists, int pos) {

        //initialising values
        this.myLists = numberLists;
        this.pos = pos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating numbers.xml to MAinActivity
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.numbers, parent, false);

        //calling ViewHolder constructor and returning it
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //creating new object of NumberList
        NumberList list = myLists[pos];

        //setting data by calling getter methods
        holder.imageView.setImageResource(list.getImgId());
        holder.textView.setText(list.getNum());


    }

    @Override
    public int getItemCount() {
        //returning 1 because we need only one data at a time
        return 1;
    }

    //ViewHolder inner class which extends recycler
    public class ViewHolder extends RecyclerView.ViewHolder {

        //Creating objects
        ImageView imageView;
        TextView textView;

        //constructor of ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //referring numbers.xml using View object
            this.imageView = itemView.findViewById(R.id.image);
            this.textView = itemView.findViewById(R.id.textData);
        }
    }
}
