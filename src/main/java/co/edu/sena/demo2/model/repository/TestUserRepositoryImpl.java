package co.edu.sena.demo2.model.repository;


import co.edu.sena.demo2.User;

import java.sql.SQLException;

public class TestUserRepositoryImpl {

    public static void main(String[] args) throws SQLException {
        UserRepositoryImpl repository = new UserRepositoryImpl();

        System.out.println("");
        User userInsert = new User();
        userInsert.setUser_firstname("oscar");
        userInsert.setUser_firstname("laverde");
        userInsert.setUser_email("oscarla@gmail.com");
        userInsert.setUser_password("Daniel1025@");
        repository.saveObj(userInsert);
        userInsert.setUser_firstname("emilio");
        userInsert.setUser_firstname("lara");
        userInsert.setUser_email("emiliolara@gmail.com");
        userInsert.setUser_password("Emilio1365@");
        repository.saveObj(userInsert);

        System.out.println("");
        repository.listAllObj().forEach(System.out::println);
        System.out.println();

        System.out.println("");
        System.out.println(repository.byIdObj(1));
        System.out.println();

        System.out.println("");
        User userUpdate = new User();
        userUpdate.setUser_id(2);
        userUpdate.setUser_firstname("julian");
        userUpdate.setUser_firstname("escobar");
        userUpdate.setUser_email("julianes@.com");
        userUpdate.setUser_password("Julian1865@");
        repository.saveObj((userInsert));
        repository.listAllObj().forEach(System.out::println);
        System.out.println();

        System.out.println("");
        repository.deleteObj(2);
        repository.listAllObj().forEach(System.out::println);
    }
}

