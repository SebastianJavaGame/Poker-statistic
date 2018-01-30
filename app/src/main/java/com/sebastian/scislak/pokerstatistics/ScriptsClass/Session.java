package com.sebastian.scislak.pokerstatistics.ScriptsClass;

import android.provider.BaseColumns;

/**
 * Created by User on 2018-01-22.
 */

public class Session implements BaseColumns{
    //Before the session
    public static final String TABLE_NAME = "session";
    public static final String DATA = "data";
    public static final String SESSION_NAME = "table_id";
    public static final String ACCOUNT_BALANCE_BEFORE = "account_balance_before";
    public static final String LENGTH_OF_THE_SESSION = "length_of_the_session";
    public static final String COUNT_MAX_PLAYERS= "count_max_players";
    public static final String COUNT_TABLES= "count_tables";

    //During the end of the session
    public static final String ACCOUNT_BALANCE_AFTER = "account_balance_after";
    public static final String PLAYED_HANDS = "played_hands";
    public static final String GOING_TO_FLOP = "going_to_flop";
    public static final String GOING_TO_FLOP_WITHOUT_BLINDS = "going_to_flop_without_blinds";
    public static final String WINNING = "winning";
    public static final String WINNING_WITHOUT_SHOW_HAND = "winning_without_show_hand";
}
