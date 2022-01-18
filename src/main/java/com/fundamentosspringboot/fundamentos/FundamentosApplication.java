package com.fundamentosspringboot.fundamentos;

import com.fundamentosspringboot.fundamentos.Bean.MyBean;
import com.fundamentosspringboot.fundamentos.Bean.MyBeanWithDependency;
import com.fundamentosspringboot.fundamentos.Bean.MyBeanWithProperties;
import com.fundamentosspringboot.fundamentos.Component.ComponentDependency;
import com.fundamentosspringboot.fundamentos.Entity.User;
import com.fundamentosspringboot.fundamentos.Pojo.UserPojo;
import com.fundamentosspringboot.fundamentos.Repository.UserRepository;
import com.fundamentosspringboot.fundamentos.Service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);


	private ComponentDependency componentDependency ;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  @Qualifier("beanOperationTwo") MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository,
								  UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);

	}

	@Override
	public void run(String... args)  {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
	public void getInformationJpqlFromUser(){
		/*LOGGER.info("Usuario encontrado en el metodo findByUserEmail "+
				userRepository.findByUserEmail("Angie@domain.com").
						orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo Sort "+user));

		userRepository.findByName("Jhon")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method "+user));

		LOGGER.info("Usuario con query method findByEmailAndName "+userRepository.findByEmailAndName("Stiven@domain.com", "Stiven")
				.orElseThrow(()->new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%u%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike "+ user));

		userRepository.findByNameOrEmail(null, "user10@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail "+ user));*/

		userRepository.findByBirthDateBetween(LocalDate.of(2021,01,01),LocalDate.of(2021,06,01))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas "+user));

		userRepository.findByNameContainingOrderByEmailDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con Like y ordenado"+ user));

		LOGGER.info("El usuario a partir del named parameter es "+userRepository.getAllByBirthDateAnAndEmail(LocalDate.of(2021,07,01),
						"Angie@domain.com")
				.orElseThrow(()->
						new RuntimeException("No se encontro el usuario a través del named parameter")));

	}


	//Método para persistir nuestra información
	private void saveUsersInDataBase(){
		User user1 = new User("Angie","Angie@domain.com", LocalDate.of(2021,07,01));
		User user2 = new User("Jhon","Jhon@domain.com", LocalDate.of(2021,04,15));
		User user3 = new User("Jhon","Camilo@domain.com", LocalDate.of(2021,03,12));
		User user4 = new User("Julia","Julia@domain.com", LocalDate.of(2021,02,04));
		User user5 = new User("Stiven","Stiven@domain.com", LocalDate.of(2021,01,06));
		User user6 = new User("Doriz","Doriz@domain.com", LocalDate.of(2021,06,12));
		User user7 = new User("user7","user7@domain.com", LocalDate.of(2021,05,13));
		User user8 = new User("user8","user8@domain.com", LocalDate.of(2021,10,22));
		User user9 = new User("user9","user9@domain.com", LocalDate.of(2021,11,21));
		User user10 = new User("user10","user10@domain.com", LocalDate.of(2021,12,30));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);
	}
	//Método transaccional

	public void saveWithErrorTransactional(){
		User test1 = new User("test1Transactional","test1Transactional@domain.com",LocalDate.now());
		User test2 = new User("test2Transactional","test2Transactional@domain.com",LocalDate.now());
		User test3 = new User("test3Transactional","test3Transactional@domain.com",LocalDate.now());
		User test4 = new User("test4Transactional","test4Transactional@domain.com",LocalDate.now());

		List<User> users = Arrays.asList(test1,test2,test3,test4);
		try{
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una exepcion dentro del metodo transaccional "+e);
		}

		userService.getAllUsers()
				.stream()
				.forEach(user ->
						LOGGER.info("Este es el usuario dentro del metodo transaccional "+user));

	}

	public void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependecy();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"  "+userPojo.getPassword());

		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor "+ value);

		}catch (Exception e){
			LOGGER.error("Esto es un error de la aplicacion al dividir por 0 " + e.getMessage() );
		}
	}
}
