package pl.sda.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sda.spring.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.firstName = :firstName")
    List<User> findByFirstName(String firstName);

    @Query("select u from User u where u.lastName = :lastName and u.firstName = :firstName")
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select u from User u where u.lastName = :lastName")
    List<User> searchByLastName(@Param("lastName") String lastName);

    @Query(value = "SELECT * FROM USER WHERE ADDRESS = :address", nativeQuery = true)
    List<User> searchByAddress(@Param("address") String address);
    
    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> authenticate(@Param("username") String username, @Param("password") String password);
}
