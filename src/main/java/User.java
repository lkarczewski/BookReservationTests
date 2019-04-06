public class User {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean validateUser(String login, String password) {
        if(login == null || login.length() == 0 || login.length() > 20 || password == null || password.length() == 0 || password.length() < 4) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "'" + login + "'";
    }
}
