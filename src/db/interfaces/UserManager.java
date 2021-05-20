package db.interfaces;

import java.util.List;

import db.pojos.users.Role;
import db.pojos.users.User;

public interface UserManager {
	
	public void connect();
	public void disconnect();
	public void newUser(User u);
	public void newRole(Role r);
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String email, String password);
	public Boolean checkEmail(String email);
	public void deleteUser(String mail, String password);
	public void updateUserMail(String newMail, String oldMail, String password);
	public void updateUserPassword(String mail, String newPassword, String oldPassword);
	void updateUserMailWithoutpass(String newMail, String oldMail);
	boolean updateUserPassword(String mail, String newPassword, String oldPassword, boolean ca);
}
