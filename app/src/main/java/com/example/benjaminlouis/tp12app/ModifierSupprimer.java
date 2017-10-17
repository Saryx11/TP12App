package com.example.benjaminlouis.tp12app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifierSupprimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_supprimer);

        //Récupération de l'id
        Intent intent=getIntent();
        int id=id=intent.getIntExtra("user",-1);

        //ouverture de la base de données
        UsersDataSources source = new UsersDataSources(this);
        UserDAO dao=new UserDAO(source);

        //récupération de l'user
        User user =dao.read(new User(id,null,null,null,null));

        //affichage des informations de l'user
        EditText nom=(EditText)findViewById(R.id.Nom);
        nom.setText(user.getNom());
        EditText prenom=(EditText)findViewById(R.id.Prenom);
        prenom.setText(user.getPrenom());
        EditText age=(EditText)findViewById(R.id.Age);
        age.setText(user.getAge().toString());
        EditText metier=(EditText)findViewById(R.id.Metier);
        metier.setText(user.getMetier());

        //bouton modifier
        Button modifier=(Button)findViewById(R.id.Modifier);
        modifier.setTag(id);
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //récupération de l'user pour pouvoir modifier
                User mod=new User(Integer.parseInt(v.getTag().toString()),null,null,null,null);
                EditText nom=(EditText)findViewById(R.id.Nom);
                mod.setNom(nom.getText().toString());
                EditText prenom=(EditText)findViewById(R.id.Prenom);
                mod.setPrenom(prenom.getText().toString());
                EditText age=(EditText)findViewById(R.id.Age);
                mod.setAge(Integer.parseInt(age.getText().toString()));
                EditText metier=(EditText)findViewById(R.id.Metier);
                mod.setMetier(metier.getText().toString());
                mod.setId((int)v.getTag());
                UsersDataSources dataSource = new UsersDataSources(ModifierSupprimer.this);
                UserDAO userDAO = new UserDAO(dataSource);
                userDAO.update(mod);
                Intent modif = new Intent(ModifierSupprimer.this,UserList.class);
                /*modif.putExtra("modId",mod.getId());
                modif.putExtra("modNom",mod.getNom());
                modif.putExtra("modPrenom",mod.getPrenom());
                modif.putExtra("modAge",mod.getAge());
                modif.putExtra("modMetier",mod.getMetier());*/
                startActivity(modif);
            }
        });

        //bouton supprimer
        Button supprimer=(Button)findViewById(R.id.Supprimer);
        supprimer.setTag(id);
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User sup=new User(Integer.parseInt(v.getTag().toString()),null,null,null,null);
                EditText nom=(EditText)findViewById(R.id.Nom);
                sup.setNom(nom.getText().toString());
                EditText prenom=(EditText)findViewById(R.id.Prenom);
                sup.setPrenom(prenom.getText().toString());
                EditText age=(EditText)findViewById(R.id.Age);
                sup.setAge(Integer.parseInt(age.getText().toString()));
                EditText metier=(EditText)findViewById(R.id.Metier);
                sup.setMetier(metier.getText().toString());
                sup.setId((int)v.getTag());
                UsersDataSources dataSource = new UsersDataSources(ModifierSupprimer.this);
                UserDAO userDAO = new UserDAO(dataSource);
                userDAO.delete(sup);
                Intent del = new Intent(ModifierSupprimer.this,UserList.class);
                startActivity(del);
            }
        });



    }
}
