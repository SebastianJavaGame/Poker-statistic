package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sebastian.scislak.pokerstatistics.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by User on 2018-01-30.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public static final String[] MONTH = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    public static final String TAG = "MyAdapter";
    private List<SessionItem> itemList;
    private SimpleDateFormat simpleDateFormat;

    public MyAdapter(List<SessionItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SessionItem session = itemList.get(position);

        String date = session.getDate();

        holder.date_item.setText(date.substring(0, 2) + " " + MONTH[Integer.valueOf(date.substring(3, 5))]);
        holder.id_item.setText(String.valueOf(session.getId()));
        holder.name_item.setText(session.getName());
        holder.profit.setText("Profit: " + String.format("%, .2f", session.getProfit()) +"$");
        holder.averageProfit.setText("Avg. profit: " + String.format("%, .2f", session.getAverageProfit()) + "$");
        holder.tables.setText("Tables: " + session.getTables());
        holder.maxPlayer.setText("Max seat: " + session.getMaxPlayerAtTheTable());
        holder.lengthSession.setText("Length: " + session.getLengthSession() + "m");
        holder.playedHands.setText("Hands: " + session.getPlayedHands());
        holder.goingToFlop.setText("See flop: " + session.getGoingToFlop());
        holder.goingToFlopWithoutBlinds.setText("Out blinds: " + session.getGoingToFlopWithoutBlinds());
        holder.winning.setText("Winning: " + session.getWinning());
        holder.winningWithoutShow.setText("Out show: " + session.getWinningWithoutShows());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView date_item;
        public TextView id_item;
        public TextView name_item;
        public TextView profit;
        public TextView averageProfit;
        public TextView tables;
        public TextView maxPlayer;
        public TextView lengthSession;
        public TextView playedHands;
        public TextView goingToFlop;
        public TextView goingToFlopWithoutBlinds;
        public TextView winning;
        public TextView winningWithoutShow;

        public ViewHolder(View itemView) {
            super(itemView);

            date_item = itemView.findViewById(R.id.date_item);
            id_item = itemView.findViewById(R.id.id_item);
            name_item = itemView.findViewById(R.id.name_item);
            profit = itemView.findViewById(R.id.profit_item);
            averageProfit = itemView.findViewById(R.id.average_profit_item);
            tables = itemView.findViewById(R.id.tables_item);
            maxPlayer = itemView.findViewById(R.id.max_player_item);
            lengthSession = itemView.findViewById(R.id.length_item);
            playedHands = itemView.findViewById(R.id.max_player_item);
            goingToFlop = itemView.findViewById(R.id.going_to_flop_item);
            goingToFlopWithoutBlinds = itemView.findViewById(R.id.going_to_flop_without_blinds_item);
            winning = itemView.findViewById(R.id.winning_item);
            winningWithoutShow = itemView.findViewById(R.id.winning_without_show_item);
        }
    }
}
