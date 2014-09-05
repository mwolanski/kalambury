package com.example.maciekwski.kalambury;

import android.app.Activity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Maciej Wolański
 * maciekwski@gmail.com
 * on 2014-08-18.
 */
public class DataExchangeManager {
    private static final DataExchangeManager instance = new DataExchangeManager();
    private boolean mode;

    private DataExchangeManager() {
    }

    public static DataExchangeManager getInstance() {
        return instance;
    }

    public boolean register(String email, String username) {
        //przywitaj serwer
        //wyślij dane
        //jak dobre to tru
        return false;
    }

    public boolean logIn() {
        return false;
    }

    public void saveUser(String email, String username, Activity a) {
        File root = new File(a.getFilesDir(), "username");
        root.mkdirs();
        File f = new File(root, "un.bin");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(email);
            dos.writeUTF(username);
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
