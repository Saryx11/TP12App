package com.example.benjaminlouis.tp12app;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class UserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        //Bouton ajouter pour passer à l'activité ajout
        Button ajouter = (Button)findViewById(R.id.ajout);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ajout=new Intent(UserList.this,Ajout.class);
                startActivity(ajout);
            }
        });

        //création/ouverture de la base de données
        UsersDataSources source = new UsersDataSources(this);
        UserDAO dao=new UserDAO(source);
        
        //création de la liste des utilisateurs pour l'affichage
        List<User> users=dao.readAll();
        User[] userTab=new User[users.size()];
        int i =0;
        for(User u: users){
            userTab[i]=users.get(i);
            i++;
        }
        //création dynamique de chaque ligne correspondant aux utilisateurs dans la base
        TableLayout table=(TableLayout)findViewById(R.id.table);
        for( i=0;i<userTab.length;i++) {

            //création d'une TableRow
            TableRow row = new TableRow(this);

            //création des TextView
            TextView name = new TextView(this);
            TextView pre = new TextView(this);
            TextView age = new TextView(this);
            TextView metier = new TextView(this);

            //remplissage des TextViews
            name.setText(userTab[i].getNom().toString());
            pre.setText(userTab[i].getPrenom().toString());
            age.setText(userTab[i].getAge().toString());
            metier.setText(userTab[i].getMetier().toString());

            //Ajout des TextViews à la TableRow avec les paramètres
            row.addView(name,new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1));
            row.addView(pre,new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1));
            row.addView(age,new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1));
            row.addView(metier,new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1));

            //ajout de la TableRow au TableLayout
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
        }

    }
}
