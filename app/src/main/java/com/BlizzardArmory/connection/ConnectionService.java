package com.BlizzardArmory.connection;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
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

    public static void showNoConnectionMessage(final Context context, final Fragment fragment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTransparent);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView titleText = new TextView(context);
        titleText.setText("No Internet Connection");
        titleText.setTextSize(20);
        titleText.setGravity(Gravity.CENTER_HORIZONTAL);
        titleText.setPadding(0, 20, 0, 20);
        titleText.setLayoutParams(layoutParams);
        titleText.setTextColor(Color.WHITE);

        TextView messageText = new TextView(context);
        messageText.setText("Make sure that Wi-Fi or mobile data is turned on, then try again.");
        messageText.setGravity(Gravity.CENTER_HORIZONTAL);
        messageText.setPadding(0, 0, 0, 20);
        messageText.setLayoutParams(layoutParams);
        messageText.setTextColor(Color.WHITE);

        Button button = new Button(context);
        button.setText("OK");
        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setWidth(200);
        button.setHeight(100);
        button.setLayoutParams(layoutParams);
        button.setBackground(context.getDrawable(R.drawable.buttonstyle));

        final AlertDialog dialog = builder.show();
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(800, 450);

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
