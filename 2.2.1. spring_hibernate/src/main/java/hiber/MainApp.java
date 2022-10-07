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
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("Lada", 2106));
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("BMW", 3));
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(new Car("Peugeot", 307));
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(new Car("Mazda", 5));


      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());

         System.out.println();
      }
      System.out.println("Ищем пользователя по машине (Lada 2106)");
      System.out.println("Пользователь, владеющий этой машиной: " + userService.getUserByCar("Lada", 2106));

      context.close();
   }
}
