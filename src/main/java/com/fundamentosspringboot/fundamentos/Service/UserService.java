package com.fundamentosspringboot.fundamentos.Service;

import com.fundamentosspringboot.fundamentos.Entity.User;
import com.fundamentosspringboot.fundamentos.Repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@Transactional: anotación que permite si existe algún error a nivel del método insert hace
     un rollbacx de todas las transacciones, y si encuentra un error mientras que otros están bien,
     entonces no los inserta en la tabla*/
    @Transactional
    public void saveTransactional(List<User>users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado"+ user)) //peex sirve para mostrar en pantalla lo que se registrá
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void remove(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return
                userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                ).get();

    }

    public List<User> getUsersPages(int page, int size) {
        return userRepository.findAll(PageRequest.of(page,size)).getContent();
    }
}
