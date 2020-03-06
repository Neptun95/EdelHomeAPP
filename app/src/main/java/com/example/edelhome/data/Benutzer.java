package com.example.edelhome.data;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Benutzer  {

    private FirebaseUser user;
    private String vorName;
    private String nachName;
    private String telefonNr;
    private String eMail;
    private String userID;
    private String password;

    public Benutzer (){

    }

    public Benutzer (String vorNmae, String nachName,String telefonNr, String password, FirebaseUser user){
//String eMail, String passwort,
        this.vorName = vorNmae;
        this.nachName = nachName;
        this.telefonNr = telefonNr;
        this.eMail = user.getEmail();
        this.password = password;
        this.userID = user.getUid();
        this.user = user;

    }



    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }





    @Override
    public String toString(){
        String s;
        s= "["+vorName.toString()+" "+nachName.toString()+" "+ telefonNr.toString()+" "+eMail.toString()+" "+ " ]";



        return s;
    }


}
