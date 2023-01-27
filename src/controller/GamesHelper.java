/**
 * @author Julie Burger - jaburger
 * CIS175 - Spring 2023
 * Jan 21, 2023
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Games;

public class GamesHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("games");
	/**
	 * @param 
	 */
	public void insertGame(Games game) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(game);
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * @param 
	 */
	public List<Games> showAllGames() {
		EntityManager em = emfactory.createEntityManager();
		List<Games> allGames = em.createQuery("SELECT g FROM Games g").getResultList();
		return allGames;
	}
	/**
	 * @param gameToDelete
	 */
	public void deleteGame(Games gameToDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		if (!em.contains(gameToDelete)) {		// **See note under method for explanation
			gameToDelete = em.merge(gameToDelete);
		}
		
		em.remove(gameToDelete);
		em.getTransaction().commit();
		em.close();
	}
	// Entity must be managed to call remove: Games...
	// try merging the detached and try the remove again.
	// https://stackoverflow.com/questions/29775627/entity-must-be-managed-to-call-remove-when-i-try-to-delete-an-entity
	// suggested adding this code
	/**
	 * @param gameToEdit
	 */
	public void updateItem(Games gameToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(gameToEdit);
		em.getTransaction().commit();
		em.close();
	}
	/**
	 * 
	 */
	public void cleanUp() {
		emfactory.close();
		
	}
	/**
	 * @param name
	 * @return
	 */
	public List<Games> searchGamesByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Games> typedQuery = em.createQuery("SELECT g FROM Games g WHERE g.name = "
				+ ":selectedName", Games.class);
		typedQuery.setParameter("selectedName", name);
		
		List<Games> foundGames = typedQuery.getResultList();
		em.close();
		return foundGames;
	}
	/**
	 * @param type
	 * @return
	 */
	public List<Games> searchGamesByType(String type) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Games> typedQuery = em.createQuery("SELECT g FROM Games g WHERE g.type = "
				+ ":selectedType", Games.class);
		typedQuery.setParameter("selectedType", type);
		
		List<Games> foundGames = typedQuery.getResultList();
		em.close();
		return foundGames;
	}
	/**
	 * @param numOfPlayers
	 * @return
	 */
	public List<Games> searchGamesbyNumber(int numOfPlayers) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Games> typedQuery = em.createQuery("SELECT g FROM Games g WHERE g.numOfPlayers = "
				+ ":selectedNumOfPlayers", Games.class);
		typedQuery.setParameter("selectedNumOfPlayers", numOfPlayers);
		
		List<Games> foundGames = typedQuery.getResultList();
		em.close();
		return foundGames;
	}
	/**
	 * @param idToEdit
	 * @return
	 */
	public Games searchGameByID(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		Games foundGame = em.find(Games.class, id);

		em.close();
		return foundGame;
	}
}
