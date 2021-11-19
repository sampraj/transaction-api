package api.rest.dao;

import api.rest.dto.Account;
import api.rest.dto.Operation;
import api.rest.dto.Player;

import java.util.Hashtable;
import java.util.List;

/**
 * Persist the in-memory data during the runtime.
 */
public class DAOUtil {
    /**
     * AccountId --> Account
     */
    public static Hashtable<String, Account> accounts = new Hashtable<String,Account>();

    /**
     * PlayerId --> Player
    * */
    public static Hashtable<String , Player> players = new Hashtable<String, Player>();

    /**
     * PlayerId --> Transactions Operations
     */
    public static Hashtable<String , List<Operation>> opertions = new Hashtable<String, List<Operation>>();

    public static Hashtable<String, Account> getAccounts() {
        return accounts;
    }

    public static Hashtable<String, Player> getPlayers() {
        return players;
    }


    public static Hashtable<String, List<Operation>> getOpertions() {
        return opertions;
    }

}
