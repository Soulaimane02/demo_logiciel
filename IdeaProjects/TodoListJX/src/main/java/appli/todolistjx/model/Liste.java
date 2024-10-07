package appli.todolistjx.model;

public class Liste {

    private int id_liste;
    private String name;
    private String tache;
    private String description;
    private String completed;

    public Liste(int id, String name, String tache, String description, String completed) {
        this.id_liste = id;
        this.name = name;
        this.tache = tache;
        this.description = description;
        this.completed = completed;
    }

    public int getId_liste() {
        return id_liste;
    }

    public void setId_liste(int id_liste) {
        this.id_liste = id_liste;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompleted() {
        return completed; // Modifier le type de retour à String
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
