package appli.model;


// Class singleton pour comprendre un peu le systeme pour voir comment sa fonctionne !
public class Singleton {

    private static Singleton instance;

    private User userConnected;

    private Singleton() {
        this.userConnected = null;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public User getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(User user) {
        this.userConnected = user;
    }

    public boolean isUserConnected() {
        return userConnected != null;
    }

    public void logout() {
        this.userConnected = null;
    }
}
