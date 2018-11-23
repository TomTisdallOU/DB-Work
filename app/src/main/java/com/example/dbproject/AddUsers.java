package com.example.dbproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class AddUsers extends AsyncTask {
    private Context context = null;
    private int howManyUsers = 0;
    private static final String TAG = AddUsers.class.getSimpleName();
    private GamePickerDatabase gamePickerDatabase;

    public AddUsers(Context context, int howManyUsers) {
        this.context = context;
        this.howManyUsers = howManyUsers;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Adding " + howManyUsers + " users.", Toast.LENGTH_LONG).show();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        gamePickerDatabase = GamePickerDatabase.getInstance(context);
        User user = new User();
        int firstNameIndex;
        int lastNameIndex;
        String fullName;

        String[] firstNames = {"Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Oliver", "Jacob", "Lucas",
                                "Michael", "Alexander", "Ethan", "Daniel", "Matthew", "Aiden", "Henry", "Joseph", "Jackson", "Samuel",
                                "Sebastian", "David", "Carter", "Wyatt", "Emma", "Olivia", "Sophia", "Isabella", "Ava", "Mia", "Emily",
                                "Abigail", "Madison", "Charlotte", "Harper", "Sofia", "Avery", "Elizabeth", "Amelia", "Evelyn", "Ella",
                                "Chloe", "Victoria", "Aubrey", "Grace", "Zoey", "Natalie", "Addison", "Lillian"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson", "Martinez",
                                "Anderson", "Taylor", "Thomas", "Hernandez", "Moore", "Martin", "Jackson", "Thompson", "White", "Lopez", "Lee",
                                "Gonzalez", "Harris", "Clark", "Lewis", "Robinson", "Walker", "Perez", "Hall"};

        for(int i = 0;i< howManyUsers; i++){

            firstNameIndex = (int) Math.floor(Math.random() * firstNames.length);
            lastNameIndex = (int) Math.floor(Math.random() * lastNames.length);
            fullName = firstNames[firstNameIndex] + " " + lastNames[lastNameIndex];

            user = new User(0, fullName, "temp", fullName, "");

            gamePickerDatabase.getUserDao().insert(user);

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        Toast.makeText(context, "Added " + howManyUsers + " users.", Toast.LENGTH_LONG).show();
    }
}
