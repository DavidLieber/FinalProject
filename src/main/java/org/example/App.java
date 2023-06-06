package org.example;

import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class App
{

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Subject.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    /*private static void generateCars() throws Exception {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 + random.nextInt(19));
            session.save(car);

 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.

            session.flush();
        }
    }*/

    /*private static List<Car> getAllCars() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        query.from(Car.class);
        List<Car> data = session.createQuery(query).getResultList();
        return data;
    }*/

    /*private static void printAllCars() throws Exception {
        List<Car> cars = getAllCars();
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');
        }
    }*/

    public static void main( String[] args ) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();


            Subject cs = new Subject("Computer Science");
            session.save(cs);
            session.flush();
            // TODO: javafx
            //Question q1 = new Question("choose 1,2,3,4", "1", "2", "3", "4", 1, cs);
            //session.save(q1);
            //session.flush();

            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}

