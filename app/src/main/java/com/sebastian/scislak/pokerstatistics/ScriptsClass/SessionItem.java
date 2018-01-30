package com.sebastian.scislak.pokerstatistics.ScriptsClass;

/**
 * Created by User on 2018-01-30.
 */

public class SessionItem {
    private int id;
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

    public SessionItem(int id, String name, float profit, float averageProfit, int tables, int maxPlayerAtTheTable, int lengthSession,
                       int playedHands, int goingToFlop, int goingToFlopWithoutBlinds, int winning, int winningWithoutShows) {
        this.id = id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getAverageProfit() {
        return averageProfit;
    }

    public void setAverageProfit(float averageProfit) {
        this.averageProfit = averageProfit;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getMaxPlayerAtTheTable() {
        return maxPlayerAtTheTable;
    }

    public void setMaxPlayerAtTheTable(int maxPlayerAtTheTable) {
        this.maxPlayerAtTheTable = maxPlayerAtTheTable;
    }

    public int getLengthSession() {
        return lengthSession;
    }

    public void setLengthSession(int lengthSession) {
        this.lengthSession = lengthSession;
    }

    public int getPlayedHands() {
        return playedHands;
    }

    public void setPlayedHands(int playedHands) {
        this.playedHands = playedHands;
    }

    public int getGoingToFlop() {
        return goingToFlop;
    }

    public void setGoingToFlop(int goingToFlop) {
        this.goingToFlop = goingToFlop;
    }

    public int getGoingToFlopWithoutBlinds() {
        return goingToFlopWithoutBlinds;
    }

    public void setGoingToFlopWithoutBlinds(int goingToFlopWithoutBlinds) {
        this.goingToFlopWithoutBlinds = goingToFlopWithoutBlinds;
    }

    public int getWinning() {
        return winning;
    }

    public void setWinning(int winning) {
        this.winning = winning;
    }

    public int getWinningWithoutShows() {
        return winningWithoutShows;
    }

    public void setWinningWithoutShows(int winningWithoutShows) {
        this.winningWithoutShows = winningWithoutShows;
    }
}
