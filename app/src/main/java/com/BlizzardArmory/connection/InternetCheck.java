package com.BlizzardArmory.connection;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * The type Internet check.
 */
public class InternetCheck extends AsyncTask<Void,Void,Boolean> {

    private Consumer mConsumer;

    /**
     * The interface Consumer.
     */
    public interface Consumer {
        /**
         * Accept.
         *
         * @param internet the internet
         */
        void accept(Boolean internet);
    }

    /**
     * Instantiates a new Internet check.
     *
     * @param consumer the consumer
     */
    public  InternetCheck(Consumer consumer) { mConsumer = consumer; execute(); }

    @Override protected Boolean doInBackground(Void... voids) { try {
        Socket sock = new Socket();
        sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
        sock.close();
        return true;
    } catch (IOException e) { return false; } }

    @Override protected void onPostExecute(Boolean internet) { mConsumer.accept(internet); }


}