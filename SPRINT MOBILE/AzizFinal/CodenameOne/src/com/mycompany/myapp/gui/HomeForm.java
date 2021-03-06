/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Saison");
        Button btnListTasks = new Button("List Saison");
        Button btnListEvenement = new Button("List Evenement");
        
        btnAddTask.addActionListener(e -> new AddSaisonForm(current).show());
        btnListTasks.addActionListener(e -> new ListSaisonForm(current).show());
        btnListEvenement.addActionListener(e -> new ListEvenementForm(current).show());
        //System.out.println(ServiceInscription.getInstance().getAllInscriptions());
        addAll(btnAddTask, btnListTasks,btnListEvenement);

    }

}
