package com.example.benjaminlouis.tp12app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        //bouton valider
        Button valider=(Button)findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = ((EditText)findViewById(R.id.nom)).getText().toString();
                String prenom = ((EditText)findViewById(R.id.prenom)).getText().toString();
                int age =0;
                age=Integer.parseInt(((EditText)findViewById(R.id.age)).getText().toString());
                String metier=((EditText)findViewById(R.id.metier)).getText().toString();
                if(!nom.equals("")&&!prenom.equals("")&&age!=0&&!metier.equals("")){
                User user=new User(null,nom,prenom,age,metier);

                UsersDataSources dataSource = new UsersDataSources(Ajout.this);
                UserDAO userDAO = new UserDAO(dataSource);

                user = userDAO.create(user);}
                Intent intent = new Intent(Ajout.this,UserList.class);
                startActivity(intent);

            }
        });

        //bouton annuler
        Button annuler=(Button)findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajout.this,UserList.class);
                startActivity(intent);
            }
        });
    }
}
