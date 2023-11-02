package model;

import framework.database.annotation.*;
import framework.database.utilitaire.ObjectBdd;

public class Model extends ObjectBdd {
    @Champs(primarykey=true)
    protected Integer id;

///Getters et setters
    public Integer getId() { return this.id; }
    public void setId( Integer id ) { this.id = id; }

///Constructors
    public Model () {}
    public Model( Integer id ) {
        setId(id);
    }

///Fonction de la classe
}