package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Lada", 2114);
        Car car2 = new Car("Moskvich", 412);
        Car car3 = new Car("Lada", 2107);
        Car car4 = new Car("Moskvich", 2140);
        User user1 = new User("Vasya", "Lomtev", "user1@mail.org");
        User user2 = new User("Lena", "Sizova", "user2@mail.com");
        User user3 = new User("Lexa", "Siviy", "user2@mail.ru");
        User user4 = new User("Sanya", "Marchuk", "user2@mail.by");
        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        showUsers(userService.listUsers(), "All users");
        showUsers(userService.listUsersWithCar("Moskvich", 412), "People using a Moskvich 412");

        context.close();
    }

    public static void showUsers(List<User> users, String message) {
        System.out.println(message);
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
    }
}