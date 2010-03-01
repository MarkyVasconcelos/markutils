package mark.utils.login;

public interface LoginListener {
	public User login(String user, String pass) throws CannotLoginException;
	public void close();
}
