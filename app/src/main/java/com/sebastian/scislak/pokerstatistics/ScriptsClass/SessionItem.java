package com.sebastian.scislak.pokerstatistics.ScriptsClass;

/**
 * Created by User on 2018-01-30.
 */

public class SessionItem {
    private int id;
    private String date;
    private String name;
    private float profit;
    private float averageProfit;
    private int tables;
    private int maxPlayerAtTheTable;
    private int lengthSession;
    private int playedHands;
    private int goingToFlop;
    private int goingToFlopWithoutBlinds;
    private int winning;
    private int winningWithoutShows;

    public SessionItem(int id, String date, String name, float profit, float averageProfit, int tables, int maxPlayerAtTheTable, int lengthSession,
                       int playedHands, int goingToFlop, int goingToFlopWithoutBlinds, int winning, int winningWithoutShows) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.profit = profit;
        this.averageProfit = averageProfit;
        this.tables = tables;
        this.maxPlayerAtTheTable = maxPlayerAtTheTable;
        this.lengthSession = lengthSession;
        this.playedHands = playedHands;
        this.goingToFlop = goingToFlop;
        this.goingToFlopWithoutBlinds = goingToFlopWithoutBlinds;
        this.winning = winning;
        this.winningWithoutShows = winningWithoutShows;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public float getProfit() {
        return profit;
    }

    public float getAverageProfit() {
        return averageProfit;
    }

    public int getTables() {
        return tables;
    }

    public int getMaxPlayerAtTheTable() {
        return maxPlayerAtTheTable;
    }

    public int getLengthSession() {
        return lengthSession;
    }

    public int getPlayedHands() {
        return playedHands;
    }

    public int getGoingToFlop() {
        return goingToFlop;
    }

    public int getGoingToFlopWithoutBlinds() {
        return goingToFlopWithoutBlinds;
    }

    public int getWinning() {
        return winning;
    }

    public int getWinningWithoutShows() {
        return winningWithoutShows;
    }
}
