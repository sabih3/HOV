package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;

import ae.netaq.homesorder_vendor.R;

/**
 * Created by Sabih Ahmed on 10-Dec-17.
 */

public class OrderManagementUtils {


    public static AlertDialog.Builder getAlertDialogBuilder(Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,
                                  R.style.Theme_AppCompat_Light_Dialog);
        builder.setTitle(context.getString(R.string.title_order_management_dialog));

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder;
    }

    public static void showDialogForNew(Context context,
                                        final StageSelectListener stageListener){
        final CharSequence stages[] = new CharSequence[] {"Processing"};

        final AlertDialog.Builder builder = getAlertDialogBuilder(context);

        builder.setItems(stages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int orderStage) {
                switch (orderStage){
                    case 0:
                        stageListener.onStageSelected();
                    break;
                }
            }
        });

        builder.show();
    }


    public static void showDialogForProcessing(Context context,
                                        final StageSelectListener stageSelectListener){
        final CharSequence stages[] = new CharSequence[] {"Ready"};

        final AlertDialog.Builder builder = getAlertDialogBuilder(context);

        builder.setItems(stages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int orderStage) {
                switch (orderStage){
                    case 0:
                        stageSelectListener.onStageSelected();
                    break;


                }
            }
        });
        builder.show();
    }

    public static void showDialogForReady(Context context,
                                          final StageSelectListener stageSelectListener){
        final CharSequence stages[] = new CharSequence[] {"Dispatched"};

        final AlertDialog.Builder builder = getAlertDialogBuilder(context);

        builder.setItems(stages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int orderStage) {
                // the user clicked on colors[which]

                switch (orderStage){
                    case 0:
                        stageSelectListener.onStageSelected();
                    break;


                }
            }
        });
        builder.show();
    }

    public interface StageSelectListener{

        void onStageSelected();
    }




}
