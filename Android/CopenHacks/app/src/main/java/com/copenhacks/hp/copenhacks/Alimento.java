package com.copenhacks.hp.copenhacks;

public class Alimento {

    public String name;
    public String foto;
    public String propietari;
    public int id;
    public long data;
    //TODO: mirar datacaducitat com es guarda

    public Alimento(){
    }

    public Alimento(String name) {
        this.name = name;
        this.id = 0;
        this.foto ="http://ichef.bbci.co.uk/news/976/cpsprodpb/11211/production/_96716107_lazytown2.jpg";
        this.propietari ="Adrià";
    }

    /*public Alimento GetAlimento(long data){
        this.data = data;
        return this;
    }*/

    public String getName(){
        return this.name;
    }

    public void SetName(String name){
        this.name = name;
    }

    public void Setfoto(String foto){
        this.foto = foto;
    }

    public String getFoto(){
        return this.foto;
    }

    public int getId() {
        return this.id;
    }
}
