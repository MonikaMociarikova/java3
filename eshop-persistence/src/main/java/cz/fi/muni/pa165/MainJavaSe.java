package cz.fi.muni.pa165;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Color;
import cz.fi.muni.pa165.entity.Product;
import java.util.Calendar;
import javax.persistence.PersistenceException;

public class MainJavaSe {
	private static EntityManagerFactory emf;

	public static void main(String[] args) throws SQLException {
		// The following line is here just to start up a in-memory database
		new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");

		// BEGIN YOUR CODE
		task08();
		// END YOUR CODE
		emf.close();
	}

	private static void task04() {
		// TODO under this line, persist two categories, one with name
		// Electronics and second with name Musical
		// You must first obtain the Entity manager 
                EntityManager emc = emf.createEntityManager();
		emc.getTransaction().begin();
		Category category1 = new Category();
		category1.setName("Electronics");
                
                Category category2 = new Category();
		category2.setName("Musical");
                
		emc.persist(category1);
                emc.persist(category2);
                
		emc.getTransaction().commit();
		//emc.close();

		// The code below is just testing code do not modify it
		 //emc = emf.createEntityManager();
		emc.getTransaction().begin();
		List<Category> categories = emc.createQuery(
				"select c from Category c order by c.name", Category.class)
				.getResultList();

                if (categories.size() != 2) 
                    throw new RuntimeException("Expected two categories!");

		assertEq(categories.get(0).getName(), "Electronics");
		assertEq(categories.get(1).getName(), "Musical");

		emc.getTransaction().commit();
		emc.close();

		System.out.println("Succesfully found Electronics and Musical!");
	}

	private static void task05() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Category category = new Category();
		category.setName("Electronics");
		em.persist(category);
		em.getTransaction().commit();
		em.close();

		// TODO under this line. create new EM and start new transaction. Merge
		// the detached category
		// into the context and change the name to "Electro"
                EntityManager emm = emf.createEntityManager();
		emm.getTransaction().begin();
                category.setName("Electro");
                emm.merge(category);
                emm.getTransaction().commit();
                emm.close();


		// The code below is just testing code do not modify it
		EntityManager checkingEm = emf.createEntityManager();
		checkingEm.getTransaction().begin();
		Category cat = checkingEm.find(Category.class, category.getId());
		assertEq(cat.getName(), "Electro");
		System.out.println("Name changed successfully to Electro");
		checkingEm.getTransaction().commit();
		checkingEm.close();
	}

	private static void task06() {
		// TODO Map class Product to be an entity with the following attributes:
		// * id - autogenerated @Id using IDENTITY column
		// * name - nonnullable, unique
		// * color - you will have to create new ENUM for this
		// * java.util.Date addedDate - this will be only date field, use @Temporal annotation
		// Then persist exactly one Product with the following values:
		// * name='Guitar'
		// * color=Color.BLACK
		// * dateAdded = 20-01-2011 - to fill java.util.Date, use java.util.Calendar.getTime(). On the Calendar, set only these three fields: YEAR=11, MONTH=0, DAY_OF_MONTH=20
		//
		// Additional task: Change the underlying table of Product entity to be ESHOP_PRODUCTS. After you do this, check this by inspecting console output (the CREATE TABLE statement)
		//
		// To test your code uncomment the commented code at the end of this method.

                Calendar calendar = Calendar.getInstance();
                calendar.set(2011, 0, 20);
                
                Product product = new Product();
                product.setName("Guitar");
                product.setColor(Color.BLACK);
                product.setAddedDate(calendar.getTime());
                
                EntityManager emc = emf.createEntityManager();
		emc.getTransaction().begin();
                emc.persist(product);
                emc.getTransaction().commit();
                emc.close();

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product p = em.createQuery("select p from Product p", Product.class)
				.getSingleResult();
		em.getTransaction().commit();
		em.close();

	// TODO Uncomment the following test code after you are finished!
	 
		assertEq(p.getName(), "Guitar");
		Calendar cal = Calendar.getInstance();
		cal.setTime(p.getAddedDate());
		assertEq(cal.get(Calendar.YEAR), 2011);
		assertEq(cal.get(Calendar.MONTH), 0);
		assertEq(cal.get(Calendar.DAY_OF_MONTH), 20);
		assertEq(cal.get(Calendar.MINUTE), 0);
		assertEq(p.getColor(), Color.BLACK);
		System.out
				.println("Found Guitar with correct values. Starting uniqueness test.");

		em = emf.createEntityManager();
		em.getTransaction().begin();
		Product p2 = new Product();
		p2.setName("Guitar");
		Product p3 = new Product();
		p3.setName("Violin");
		em.persist(p3);
		System.out.println("Successfully persited Violin");
		try {
			em.persist(p2);
			
			throw new RuntimeException(
					"Successfully saved new Product with the same name (Guitar) it should be unique!");
		} catch (PersistenceException ex) {
			System.out
					.println("Unsuccessfully saved second object with name Guitar -> OK");
		}
		em.close();
	

		System.out.println("Task7 ok!");
		
	}
	
	private static void task08() {
		System.out.println("Running TASK 08");
		//Implement business equivalence on Product (equals and hashcode method). Tip: Product.name is nonullable and should have unique values 
		//This is very important concept and you should understand it beyond just "making this method work"
		// see https://developer.jboss.org/wiki/EqualsandHashCode
		
		//TODO after you implement equals nad hashCode, you can uncomment the code below. It will try
		// to check whether you are doing everything correctly. 



		class MockProduct extends Product {
			private boolean getNameCalled = false;
			@Override
			public String getName() {
				getNameCalled = true;
				return super.getName();
			}
		}
		
		Product p = new Product();
		p.setName("X");
		p.setId(2l);
		Product p2 = new Product();
		p2.setName("X");
		p2.setId(4l);
		MockProduct mp = new MockProduct();
		mp.setName("X");
		p.setId(3l);
		
		System.out.println("Your equals and hashcode should work on unique 'name' attribute");
		if (p.equals(p2) && p.hashCode()==p2.hashCode()){
			System.out.println("CORRECT");
		} else System.out.println("INCORRECT!");
		
		
		System.out.println("Your equals should use instanceof and not getClass()==");
		if (p.equals(mp)){
			System.out.println("CORRECT");
		} else
			System.out.println("INCORRECT!");

		System.out.println("Your equals should call getter to get 'name' value on the other object, because other object may be a proxy class instance");
		if (mp.getNameCalled){
			System.out.println("CORRECT");
		} else System.out.println("INCORRECT!");

	
	}

	private static void assertEq(Object obj1, Object obj2) {
		if (!obj1.equals(obj2)) {
			throw new RuntimeException(
					"Expected these two objects to be identical: " + obj1
							+ ", " + obj2);
		} else {
			System.out.println("OK objects are identical");
		}
	}

}
