package ua.lviv.lgs.management;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import ua.lviv.lgs.management.dao.FacultyRepository;
import ua.lviv.lgs.management.dao.RegisteredEntrantRepository;
import ua.lviv.lgs.management.dao.StatementRepository;
import ua.lviv.lgs.management.dao.UserRepository;
import ua.lviv.lgs.management.domain.Faculty;
import ua.lviv.lgs.management.domain.RegisteredEntrant;
import ua.lviv.lgs.management.domain.Statement;
import ua.lviv.lgs.management.domain.Subjects;
import ua.lviv.lgs.management.domain.User;
import ua.lviv.lgs.management.domain.UserRole;
import ua.lviv.lgs.management.service.FacultyService;
import ua.lviv.lgs.management.service.RegisteredEntrantService;
import ua.lviv.lgs.management.service.StatementService;
import ua.lviv.lgs.management.service.UserService;

@SpringBootTest
public class ManagementApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private StatementService statementService;

	@Autowired
	private RegisteredEntrantService registeredEntrantService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private StatementRepository statementRepository;

	@Autowired
	private RegisteredEntrantRepository registeredEntrantRepository;

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("test@mail.com");
		user.setFirstName("Andy");
		user.setLastName("Mask");
		user.setPassword("123");
		user.setPasswordConfirm("123");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDB = users.get(0);
		assertTrue(userFromDB.getEmail().equals(user.getEmail()));
		assertTrue(userFromDB.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDB.getLastName().equals(user.getLastName()));
		assertTrue(userFromDB.getRole().equals(user.getRole()));
		
		userRepository.delete(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
	}

	@Test
	public void testFindUserByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("test@mail.com");
		user.setFirstName("Andy");
		user.setLastName("Mask");
		user.setPassword("123");
		user.setPasswordConfirm("123");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User findByEmail = userService.findByEmail(user.getEmail());
		assertTrue(findByEmail.getEmail().equals(user.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(user.getLastName()));
		assertTrue(findByEmail.getRole().equals(user.getRole()));
		
		userRepository.delete(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
	}

	@Test
	public void testFindUserById() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("test@mail.com");
		user.setFirstName("Andy");
		user.setLastName("Mask");
		user.setPassword("123");
		user.setPasswordConfirm("123");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User findById = userService.findById(users.get(0).getId());
		assertTrue(findById.getEmail().equals(user.getEmail()));
		assertTrue(findById.getFirstName().equals(user.getFirstName()));
		assertTrue(findById.getLastName().equals(user.getLastName()));
		assertTrue(findById.getRole().equals(user.getRole()));
		
		userRepository.delete(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
	}

	@Test
	public void testSaveFaculty() {
		List<Faculty> faculties = facultyRepository.findAll();
		 assertThat(faculties, hasSize(0)); 

		Faculty faculty = new Faculty();
		faculty.setName("Faculty1");
		faculty.setStudents(40);
		faculty.setSubjects(Arrays.asList(Subjects.CHEMISTRY, Subjects.GEOGRAPHY));

		facultyRepository.save(faculty);

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));
		Faculty facultyFromDB = faculties.get(0);
		assertTrue(facultyFromDB.getName().equals(faculty.getName()));
		assertTrue(facultyFromDB.getStudents().equals(faculty.getStudents()));
		
		facultyRepository.delete(faculty);
		faculties = facultyRepository.findAll();
		  assertThat(faculties, hasSize(0));
	}

	@Test
	public void testFindFacultyById() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName("Faculty2");
		faculty.setStudents(35);
		faculty.setSubjects(Arrays.asList(Subjects.HISTORY, Subjects.COMPUTER_SCIENCE, Subjects.MATHEMATICS));

		facultyRepository.save(faculty);

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Faculty findFacultyById = facultyService.findFacultyById(faculties.get(0).getId());
		assertTrue(findFacultyById.getName().equals(faculty.getName()));
		assertTrue(findFacultyById.getStudents().equals(faculty.getStudents()));
		
		facultyRepository.deleteAll();

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
	}

	@Test
	public void testFindAllFaculties() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName("Faculty2");
		faculty.setStudents(35);
		faculty.setSubjects(Arrays.asList(Subjects.HISTORY, Subjects.COMPUTER_SCIENCE, Subjects.MATHEMATICS));

		Faculty faculty2 = new Faculty();
		faculty2.setName("Faculty3");
		faculty2.setStudents(30);
		faculty2.setSubjects(Arrays.asList(Subjects.PHISICS, Subjects.UKRAINE));

		facultyRepository.saveAll(Arrays.asList(faculty, faculty2));

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		List<Faculty> facultiesFromDB = facultyService.findAllFaculties();
		assertTrue(facultiesFromDB.get(0).getName().equals(faculty.getName()));
		assertTrue(facultiesFromDB.get(0).getStudents().equals(faculty.getStudents()));


		assertTrue(facultiesFromDB.get(1).getName().equals(faculty2.getName()));
		assertTrue(facultiesFromDB.get(1).getStudents().equals(faculty2.getStudents()));

		
		facultyRepository.deleteAll();

		
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
	}

	@Test
	public void testSaveStatement() {
		List<Statement> statements = statementRepository.findAll();
		assertThat(statements, hasSize(0));

		Statement statement = new Statement();
		statement.setFacultyId(1);
		statement.setUserId(2);
		statement.setStatementMarks(Arrays.asList(89.0, 90.0, 90.0));

		statementRepository.save(statement);

		statements = statementRepository.findAll();
		assertThat(statements, hasSize(1));

		Statement statementFromDB = statements.get(0);
		assertTrue(statementFromDB.getUserId().equals(statement.getUserId()));
		
		statementRepository.save(statement);

		statements = statementRepository.findAll();
		assertThat(statements, hasSize(1));

		statementRepository.delete(statement);

		statements = statementRepository.findAll();
		assertThat(statements, hasSize(0));
	}

	@Test
	public void testFindAllStatements() {
		List<Statement> statements = statementRepository.findAll();
		assertThat(statements, hasSize(0));

		Statement statement = new Statement();
		statement.setFacultyId(1);
		statement.setUserId(1);
		statement.setStatementMarks(Arrays.asList(88.0, 89.0, 90.0));

		Statement statement2 = new Statement();
		statement2.setFacultyId(3);
		statement2.setUserId(12);
		statement2.setStatementMarks(Arrays.asList(77.0, 40.0, 99.0));

		statementRepository.saveAll(Arrays.asList(statement, statement2));

		statements = statementRepository.findAll();
		assertThat(statements, hasSize(2));

		List<Statement> statementsFromDB = statementService.findAllStatements();
		assertTrue(statementsFromDB.get(0).getUserId().equals(statement.getUserId()));

		assertTrue(statementsFromDB.get(1).getUserId().equals(statement2.getUserId()));
		
		statementRepository.deleteAll();

		statements = statementRepository.findAll();
		assertThat(statements, hasSize(0));
	}
	


	@Test
	public void testSaveRegisteredEntrant() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks = Arrays.asList(88.0, 89.0, 87.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();

		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));

		User user = new User();
		user.setFirstName("Ilon");
		user.setLastName("Mask");
		user.setEmail("test@mail.com");
		user.setPassword("11");
		user.setPasswordConfirm("11");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		Faculty faculty = new Faculty();
		faculty.setName("Faculty1");
		faculty.setStudents(40);
		faculty.setSubjects(Arrays.asList(Subjects.COMPUTER_SCIENCE, Subjects.MATHEMATICS));

		facultyRepository.save(faculty);
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		User userFromDB = users.get(0);
		Faculty facultyFromDB = faculties.get(0);

		RegisteredEntrant registeredEntrant = new RegisteredEntrant(userFromDB, facultyFromDB, marks);
		registeredEntrant.setEncodedEntrantImage("122334");

		registeredEntrantRepository.save(registeredEntrant);
		registeredEntrants = registeredEntrantRepository.findAll();
		assertThat(registeredEntrants, hasSize(1));

		RegisteredEntrant registeredEntrantFromDB = registeredEntrants.get(0);

		assertTrue(registeredEntrantFromDB.getUser().equals(registeredEntrant.getUser()));
		assertTrue(registeredEntrantFromDB.getEncodedEntrantImage().equals(registeredEntrant.getEncodedEntrantImage()));
		

		registeredEntrantService.deleteAll();
		facultyRepository.deleteAll();
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		userRepository.deleteAll();
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
	}

	@Test
	public void testFindRegisteredEntrantById() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks = Arrays.asList(88.0, 89.0, 87.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();

		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));

		User user = new User();
		user.setFirstName("Ilon");
		user.setLastName("Mask");
		user.setEmail("test@mail.com");
		user.setPassword("11");
		user.setPasswordConfirm("11");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);
		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		Faculty faculty = new Faculty();
		faculty.setName("Faculty1");
		faculty.setStudents(40);
		faculty.setSubjects(Arrays.asList(Subjects.COMPUTER_SCIENCE, Subjects.MATHEMATICS));

		facultyRepository.save(faculty);
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		User userFromDB = users.get(0);
		Faculty facultyFromDB = faculties.get(0);

		RegisteredEntrant registeredEntrant = new RegisteredEntrant(userFromDB, facultyFromDB, marks);
		registeredEntrant.setEncodedEntrantImage("122334");

		registeredEntrantRepository.save(registeredEntrant);
		registeredEntrants = registeredEntrantRepository.findAll();
		assertThat(registeredEntrants, hasSize(1));

		RegisteredEntrant registeredEntrantFromDB = registeredEntrantService
				.findById(registeredEntrants.get(0).getId());

		assertTrue(registeredEntrantFromDB.getUser().equals(registeredEntrant.getUser()));
		assertTrue(registeredEntrantFromDB.getEncodedEntrantImage().equals(registeredEntrant.getEncodedEntrantImage()));
		registeredEntrantService.deleteAll();
		facultyRepository.deleteAll();
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		userRepository.deleteAll();
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
		
	}

	@Test
	public void testFindAllRegisteredEntrants() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks1 = Arrays.asList(91.0, 90.0, 93.0);
		List<Double> marks2 = Arrays.asList(80.0, 84.0, 79.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();

		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));

		User user1 = new User();
		user1.setFirstName("Ilon");
		user1.setLastName("Mask");
		user1.setEmail("example@mail.com");
		user1.setPassword("12");
		user1.setPasswordConfirm("12");
		user1.setRole(UserRole.ROLE_USER);

		User user2 = new User();
		user2.setFirstName("Mark");
		user2.setLastName("Zuckerberg");
		user2.setEmail("test@mail.com");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(UserRole.ROLE_USER);

		userRepository.saveAll(Arrays.asList(user1, user2));
		users = userRepository.findAll();
		assertThat(users, hasSize(2));

		Faculty faculty1 = new Faculty();
		faculty1.setName("Facultu1");
		faculty1.setStudents(40);
		faculty1.setSubjects(Arrays.asList(Subjects.GEOGRAPHY, Subjects.CHEMISTRY));

		Faculty faculty2 = new Faculty();
		faculty2.setName("Faculty2");
		faculty2.setStudents(38);
		faculty2.setSubjects(Arrays.asList(Subjects.ENGLISH, Subjects.MATHEMATICS, Subjects.COMPUTER_SCIENCE));

		facultyRepository.saveAll(Arrays.asList(faculty1, faculty2));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		RegisteredEntrant registeredEntrant1 = new RegisteredEntrant(users.get(0), faculties.get(0), marks1);
		registeredEntrant1.setEncodedEntrantImage("142124");

		RegisteredEntrant registeredEntrant2 = new RegisteredEntrant(users.get(1), faculties.get(1), marks2);
		registeredEntrant2.setEncodedEntrantImage("67896");

		registeredEntrantRepository.saveAll(Arrays.asList(registeredEntrant1, registeredEntrant2));
		List<RegisteredEntrant> registeredEntrantsFromDB = registeredEntrantService.findAllRegisteredEntrants();
		assertThat(registeredEntrantsFromDB, hasSize(2));

		assertTrue(registeredEntrantsFromDB.get(0).getUser().equals(registeredEntrant1.getUser()));
		assertTrue(registeredEntrantsFromDB.get(0).getEncodedEntrantImage()
				.equals(registeredEntrant1.getEncodedEntrantImage()));

		assertTrue(registeredEntrantsFromDB.get(1).getUser().equals(registeredEntrant2.getUser()));
		assertTrue(registeredEntrantsFromDB.get(1).getEncodedEntrantImage()
				.equals(registeredEntrant2.getEncodedEntrantImage()));
		
		registeredEntrantService.deleteAll();
		facultyRepository.deleteAll();
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		userRepository.deleteAll();
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
	}

	@Test
	public void testDeleteRegisteredEntrantById() {
		List<User> users = userRepository.findAll();
		List<Faculty> faculties = facultyRepository.findAll();
		List<Double> marks1 = Arrays.asList(89.0, 89.0, 91.0);
		List<Double> marks2 = Arrays.asList(78.0, 57.0, 75.0);
		List<RegisteredEntrant> registeredEntrants = registeredEntrantRepository.findAll();

		assertThat(users, hasSize(0));
		assertThat(faculties, hasSize(0));
		assertThat(registeredEntrants, hasSize(0));

		User user1 = new User();
		user1.setFirstName("Ilon");
		user1.setLastName("Mask");
		user1.setEmail("example@mail.com");
		user1.setPassword("12");
		user1.setPasswordConfirm("12");
		user1.setRole(UserRole.ROLE_USER);

		User user2 = new User();
		user2.setFirstName("Mark");
		user2.setLastName("Zuckerberg");
		user2.setEmail("test@mail.com");
		user2.setPassword("123");
		user2.setPasswordConfirm("123");
		user2.setRole(UserRole.ROLE_USER);

		userRepository.saveAll(Arrays.asList(user1, user2));
		users = userRepository.findAll();
		assertThat(users, hasSize(2));

		Faculty faculty1 = new Faculty();
		faculty1.setName("Facultu1");
		faculty1.setStudents(40);
		faculty1.setSubjects(Arrays.asList(Subjects.GEOGRAPHY, Subjects.CHEMISTRY));

		Faculty faculty2 = new Faculty();
		faculty2.setName("Faculty2");
		faculty2.setStudents(38);
		faculty2.setSubjects(Arrays.asList(Subjects.ENGLISH, Subjects.MATHEMATICS, Subjects.COMPUTER_SCIENCE));

		facultyRepository.saveAll(Arrays.asList(faculty1, faculty2));
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		RegisteredEntrant registeredEntrant1 = new RegisteredEntrant(users.get(0), faculties.get(0), marks1);
		registeredEntrant1.setEncodedEntrantImage("142124");

		RegisteredEntrant registeredEntrant2 = new RegisteredEntrant(users.get(1), faculties.get(1), marks2);
		registeredEntrant2.setEncodedEntrantImage("67896");

		registeredEntrantRepository.saveAll(Arrays.asList(registeredEntrant1, registeredEntrant2));
		List<RegisteredEntrant> registeredEntrantsFromDB = registeredEntrantService.findAllRegisteredEntrants();
		assertThat(registeredEntrantsFromDB, hasSize(2));

		registeredEntrantService.deleteById(registeredEntrantsFromDB.get(0).getId());

		registeredEntrantsFromDB = registeredEntrantRepository.findAll();
		assertThat(registeredEntrantsFromDB, hasSize(1));

		assertTrue(registeredEntrantsFromDB.get(0).getUser().equals(registeredEntrant2.getUser()));
		assertTrue(registeredEntrantsFromDB.get(0).getEncodedEntrantImage()
				.equals(registeredEntrant2.getEncodedEntrantImage()));

		registeredEntrantService.deleteAll();
		facultyRepository.deleteAll();
		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));
		userRepository.deleteAll();
		users = userRepository.findAll();
		assertThat(users, hasSize(0));
	
	}
}