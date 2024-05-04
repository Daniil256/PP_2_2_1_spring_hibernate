package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Lada", 2114);
        Car car2 = new Car("Moskvich", 2140);
        User user1 = new User("Vasya", "Lomtev", "user1@mail.org");
        User user2 = new User("Lena", "Sizova", "user2@mail.com");
        user1.setCar(car1);
        user2.setCar(car2);
        userService.add(user1);
        userService.add(user2);

        List<User> usersModelCar = userService.listUsersWithCar("Moskvich");
        List<User> usersSeriasCar = userService.listUsersWithCarSerias(2140);

        List<User> users = userService.listUsers();
        for (User user : usersSeriasCar) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Car = " + user.getCar().getModel()+ " "+ user.getCar().getSeries());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
