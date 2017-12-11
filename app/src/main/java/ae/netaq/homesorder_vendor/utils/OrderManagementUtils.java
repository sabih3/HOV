package ae.netaq.homesorder_vendor.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import ae.netaq.homesorder_vendor.R;

/**
 * Created by Sabih Ahmed on 10-Dec-17.
 */

public class OrderManagementUtils {


    public static void showManagementDialog(Context context,
                                            final orderStageSelection stageSelectionListener){
        final CharSequence stages[] = new CharSequence[] {"Processing", "Ready", "Dispatched"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_order_management_dialog));
        builder.setItems(stages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int orderStage) {
                // the user clicked on colors[which]

                switch (orderStage){
                    case 0:
                        stageSelectionListener.onMoveToProcessing();
                    break;

                    case 1:
                        stageSelectionListener.onMoveToReady();
                    break;

                    case 2:
                        stageSelectionListener.onMoveToDispatch();
                    break;
                }
            }
        });
        builder.show();
    }

    public static void showDialogForNew(Context context, final StageSelectListener stageListener){
        final CharSequence stages[] = new CharSequence[] {"Processing"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_order_management_dialog));
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

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_order_management_dialog));
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

    public static void showDialogForReady(Context context, final StageSelectListener stageSelectListener){
        final CharSequence stages[] = new CharSequence[] {"Dispatched"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.title_order_management_dialog));
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



    public interface orderStageSelection{

        void onMoveToProcessing();
        void onMoveToReady();
        void onMoveToDispatch();
    }

    public interface StageSelectListener{

        void onStageSelected();
    }




}
