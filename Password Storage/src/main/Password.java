package main;

public class Password {

    private String label;
    private String login;
    private String password;
    private String root;
    private String description;

    public Password(String label, String login, String password, String root, String description) {
        this.label = label;
        this.login = login;
        this.password = password;
        this.root = root;
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRoot() {
        return root;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Password{" +
                "label='" + label + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", root='" + root + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
