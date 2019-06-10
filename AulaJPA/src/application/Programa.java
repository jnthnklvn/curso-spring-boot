package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Pessoa;

public class Programa {

	public static void main(String[] args) {

		/*Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
		Pessoa p2 = new Pessoa(null, "Maria da Silva", "maria@gmail.com");
		Pessoa p3 = new Pessoa(null, "Joana da Silva", "joana@gmail.com");*/

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();

		//Persistir
		/*em.getTransaction().begin();
		
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		
		em.getTransaction().commit();*/
		
		//Consulta
		Pessoa p = em.find(Pessoa.class, 1);
		
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		
		System.out.println("Pronto");
		em.close();
		emf.close();
	}
}
