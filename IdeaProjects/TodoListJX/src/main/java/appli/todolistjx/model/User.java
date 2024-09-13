package appli.todolistjx.model;

import java.util.Objects;

public class User {
    private int id;
    private String mail;
    private String password;

    public User(int id, String mail, String password){
        this.id = id;
        this.mail = mail;
        this.password = password;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getMail(){
        return this.mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(mail, user.mail) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, password);
    }
}
