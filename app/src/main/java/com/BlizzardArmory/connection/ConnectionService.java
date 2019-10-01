package com.BlizzardArmory.connection;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.BlizzardArmory.R;
import java.io.IOException;



public class ConnectionService {

    public static boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 us.battle.net";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

    public static void showNoConnectionMessage(Context context, final Fragment fragment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialogInternetCustom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView titleText = new TextView(context);
        titleText.setText("No Internet Connection");
        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 0, 0, 20);
        titleText.setLayoutParams(layoutParams);

        TextView messageText = new TextView(context);
        messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setPadding(0, 0, 0, 20);
        messageText.setLayoutParams(layoutParams);

        Button button = new Button(context);
        button.setText("OK");
        button.setGravity(Gravity.CENTER_HORIZONTAL);
        button.setLayoutParams(layoutParams);

        final AlertDialog dialog = builder.show();

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(20, 20, 20, 20);

        linearLayout.addView(titleText);
        linearLayout.addView(messageText);
        linearLayout.addView(button);

        dialog.addContentView(linearLayout, layoutParams);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                fragment.getFragmentManager().beginTransaction().remove(fragment).commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
