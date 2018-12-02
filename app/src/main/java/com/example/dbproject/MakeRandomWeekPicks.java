package com.example.dbproject;

import android.content.Context;
import android.os.CountDownTimer;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.util.List;

public class MakeRandomWeekPicks {
    private Context context = null;
    private int week = 0;
    private GamePickerDatabase gamePickerDatabase;
    MobileServiceClient mclient = null;
    AzurePicks azurePicks = null;
    MobileServiceTable<AzurePicks> azurePicksTable = null;



    public MakeRandomWeekPicks(Context context) {
        this.context = context;

    }

    public void MakeWeekPicks(int week) {
        gamePickerDatabase = GamePickerDatabase.getInstance(context);
        //TODO Move to save picks button -- and into random creation of picks.
        //  AzureServiceAdapter.Initialize(getActivity());
        mclient = AzureServiceAdapter.getInstance().getClient();
        azurePicksTable = mclient.getTable(AzurePicks.class);

        List<User> users = gamePickerDatabase.getUserDao().getAllUsers();
        List<Game> games = gamePickerDatabase.getGameDao().findGamesForWeek(week);
        Pick pick = new Pick();
        Pick pickTemp;
    //    int pickId = 1;

        for (User user : users) {
            for (Game game : games) {


                pick.setPicksID(0);
                pick.setGameID(game.getGameID());
                pick.setUserID(user.userID);
                azurePicks = null;
              //  pick.setPicksID(pickId);
                pick.setConfidence(0);
                if (Math.random() < 0.5)
                    pick.setTeamPicked(game.getHomeTeam());
                else
                    pick.setTeamPicked(game.getAwayTeam());


                pickTemp = gamePickerDatabase.getPickDao().getPick(pick.getUserID(), pick.getGameID());
                if (pickTemp == null) {
                    gamePickerDatabase.getPickDao().insert(pick);


                            //TODO push to Azure  -- crashing threads would need to figure out bulk insert
                 //           azurePicks = new AzurePicks(user.getUserName(), Integer.toString(pick.getGameID()), pick.getTeamPicked());
                  //          addAzureData(true,azurePicks);

                }else{
                    pick.setPicksID(pickTemp.getPicksID());
                    //Commented out to not change any existing picks
                 //   gamePickerDatabase.getPickDao().update(pick);
                }



              //  pickId++;
            }
        }

    }
    private void addAzureData(boolean adding, final AzurePicks azurePicks) {
        if (adding) {

          //  new CountDownTimer(1000, 1000){
          //      public void onFinish() {
                    //TODO push to Azure

                    azurePicksTable.insert(azurePicks, new TableOperationCallback<AzurePicks>() {
                        @Override
                        public void onCompleted(AzurePicks entity, Exception exception, ServiceFilterResponse response) {
                            if (exception == null) {

                                // Insert succeeded
                            } else {
                                exception.printStackTrace();
                                // Insert failed
                            }
                        }
                    });

          //      }
           //     public void onTick(long millisUntilFinished){

           //     }
         //   }.start();


        } else {
            //TODO Get Azure entry to then update it.
        }
    }
}
