package db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import db.interfaces.UserManager;
import db.pojos.users.Role;
import db.pojos.users.User;

public class JPAUserManagment implements UserManager{

	private EntityManager em;
	
	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("user-login").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
		List<Role> existingRoles = this.getRoles();
		if(existingRoles.size() < 3) {
			this.newRole(new Role("doctor"));
			this.newRole(new Role("laboratory"));
			this.newRole(new Role("administration"));
		}
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void newUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void newRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(int id) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE id = ?", Role.class);
		q.setParameter(1, id);
		return (Role) q.getSingleResult();
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		return (List<Role>) q.getResultList();
	}

	@Override
	public User checkPassword(String email, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
			q.setParameter(1, email);
			q.setParameter(2, hash);
			return (User) q.getSingleResult();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch(NoResultException nre) {
			return null;
		}
		return null;
	}

	@Override
	public Boolean checkEmail(String email) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ?", User.class);
			q.setParameter(1, email);
			User temp = (User)q.getSingleResult();
			if(temp.getEmail().equalsIgnoreCase(email)) {
				return true;
			}else {
				return false;
			}
		}catch(NoResultException nre) {
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUser(String mail, String password) {
		try {
			System.out.println("el usuario");

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
			q.setParameter(1, mail);
			q.setParameter(2, hash);
			if (q.getResultList().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Wrong user name or password.");

			}else {
				User u = (User) q.getSingleResult();

				em.getTransaction().begin();
				em.remove(u);
				em.getTransaction().commit();	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUserMail(String newMail, String oldMail, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? ", User.class);
			q.setParameter(1, oldMail);
			q.setParameter(2, hash);
			User u = (User) q.getSingleResult();
			
			em.getTransaction().begin();
			u.setEmail(newMail);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}@Override
	public void updateUserMailWithoutpass(String newMail, String oldMail) {
		try {
			
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? ", User.class);
			q.setParameter(1, oldMail);
			User u = (User) q.getSingleResult();
			
			em.getTransaction().begin();
			u.setEmail(newMail);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
 	public boolean updateUserPassword(String mail, String newPassword, String oldPassword,boolean catchdone) {
 		try {
 			MessageDigest md = MessageDigest.getInstance("MD5");
 			md.update(oldPassword.getBytes());
 			byte[] hash = md.digest();
 			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
 			q.setParameter(1, mail);
 			q.setParameter(2, hash);
 			User u = (User) q.getSingleResult();
 			MessageDigest md2 = MessageDigest.getInstance("MD5");
 			md2.update(newPassword.getBytes());
 			byte[] hash2 = md2.digest();

 			em.getTransaction().begin();
 			u.setPassword(hash2);
 			em.getTransaction().commit();
 		}catch(Exception e) {
 			catchdone=true;
 		    JOptionPane.showMessageDialog(null, "The current password is not correct");

 		}
 		return catchdone;


 	}

	@Override
	public void updateUserPassword(String mail, String newPassword, String oldPassword) {
		// TODO Auto-generated method stub
		
	}

}
