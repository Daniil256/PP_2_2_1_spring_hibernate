package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    List<User> listUsersWithCar(String model);

    List<User> listUsersWithCarSerias(int serias);
}
