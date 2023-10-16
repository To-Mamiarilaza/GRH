/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionProfile;

import java.util.List;

/**
 *
 * @author Fy Botas
 */
public class BestCritere {
    List<Integer> listeIdWp;
    List<String> listePost;
    List<DiplomeNote> listeDiplomeNote;
    List<AdresseNote> listeAdresseNote;
    List<SexeNote> listeSexeNote;
    List<SalaireNote> listeSalaireNote;
    List<ExperienceNote> listeExperienceNote;

    public BestCritere(List<Integer> listeIdWp, List<String> listePost, List<DiplomeNote> listeDiplomeNote, List<AdresseNote> listeAdresseNote, List<SexeNote> listeSexeNote, List<SalaireNote> listeSalaireNote, List<ExperienceNote> listeExperienceNote) {
        this.listeIdWp = listeIdWp;
        this.listePost = listePost;
        this.listeDiplomeNote = listeDiplomeNote;
        this.listeAdresseNote = listeAdresseNote;
        this.listeSexeNote = listeSexeNote;
        this.listeSalaireNote = listeSalaireNote;
        this.listeExperienceNote = listeExperienceNote;
    }

    public BestCritere() {
    }

    public List<Integer> getListeIdWp() {
        return listeIdWp;
    }

    public void setListeIdWp(List<Integer> listeIdWp) {
        this.listeIdWp = listeIdWp;
    }

    public List<String> getListePost() {
        return listePost;
    }

    public void setListePost(List<String> listePost) {
        this.listePost = listePost;
    }

    public List<DiplomeNote> getListeDiplomeNote() {
        return listeDiplomeNote;
    }

    public void setListeDiplomeNote(List<DiplomeNote> listeDiplomeNote) {
        this.listeDiplomeNote = listeDiplomeNote;
    }

    public List<AdresseNote> getListeAdresseNote() {
        return listeAdresseNote;
    }

    public void setListeAdresseNote(List<AdresseNote> listeAdresseNote) {
        this.listeAdresseNote = listeAdresseNote;
    }

    public List<SexeNote> getListeSexeNote() {
        return listeSexeNote;
    }

    public void setListeSexeNote(List<SexeNote> listeSexeNote) {
        this.listeSexeNote = listeSexeNote;
    }

    public List<SalaireNote> getListeSalaireNote() {
        return listeSalaireNote;
    }

    public void setListeSalaireNote(List<SalaireNote> listeSalaireNote) {
        this.listeSalaireNote = listeSalaireNote;
    }

    public List<ExperienceNote> getListeExperienceNote() {
        return listeExperienceNote;
    }

    public void setListeExperienceNote(List<ExperienceNote> listeExperienceNote) {
        this.listeExperienceNote = listeExperienceNote;
    }

}
