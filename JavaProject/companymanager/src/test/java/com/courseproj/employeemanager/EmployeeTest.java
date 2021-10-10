package com.courseproj.employeemanager;

import com.courseproj.employeemanager.appEmployee.model.Employee;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    private Employee employeeNotAdd1;
    private Employee employeeNotAdd2;

    @Before
    public void setUp() throws Exception {
        employee1 = new Employee("Nikita","krav@gmail.com","Java");
        employee2 = new Employee("Kirill","kkv@gmail.com","IOS Developer");
        employee3 = new Employee("Andrew","andrw@gmail.com","Frontend Developer");

        employeeNotAdd1 = new Employee("","","");
        employeeNotAdd2 = new Employee(null,null,null);

    }


    @Test
    public void getAllEmployees() {

        Employee employee1 = new Employee("Nikita","krav@gmail.com","Java");
        Employee employee2 = new Employee("Kirill","kkv@gmail.com","IOS Developer");
        Employee employee3 = new Employee("Andrew","andrw@gmail.com","Frontend Developer");


        List<com.courseproj.employeemanager.appEmployee.Employee> expected = Employee.getAllEmployees();

        List<Employee> actual = new ArrayList<>();
        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetAllEmployees_JOB_TITLE_JAVA() {

        employee1 = new Employee("Nikita","krav@gmail.com","Java");
        employee2 = new Employee("Kirill","kkv@gmail.com","IOS Developer");
        employee3 = new Employee("Andrew","andrw@gmail.com","Frontend Developer");


        List<Employee> expected = Employee.getAllEmployees("Java");

        List<Employee> actual = new ArrayList<>();
        actual.add(employee1);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getHowManyEmployees() {

        employee1 = new Employee("Nikita","krav@gmail.com","Java");
        employee2 = new Employee("Kirill","kkv@gmail.com","IOS Developer");
        employee3 = new Employee("Andrew","andrw@gmail.com","Frontend Developer");

        int expected = Employee.getHowManyEmployees();

        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void GetHowManyEmployees_JOB_TITLE_JAVA(){

        employee1 = new Employee("Nikita","krav@gmail.com","Java");
        employee2 = new Employee("Kirill","kkv@gmail.com","IOS Developer");
        employee3 = new Employee("Andrew","andrw@gmail.com","Frontend Developer");


        int expected = Employee.getHowManyEmployees("Java");

        int actual = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void newEmployee_EMPTY_NAME() {

        employeeNotAdd1 = new Employee("","","");
        employeeNotAdd2 = new Employee(null,null,null);


        for (com.courseproj.employeemanager.appEmployee.Employee employee : Employee.getAllEmployees()){
            if (employee.getName() != null && employee.getName().isEmpty()) {
                Assert.fail("Попытка создания работника с пустым именем");
            }
        }
    }

    @Test
    public void newEmployee_EMPTY_EMAIL() {

        Employee employeeNotAdd1 = new Employee("","","");
        Employee employeeNotAdd2 = new Employee(null,null,null);

        for (com.courseproj.employeemanager.appEmployee.Employee employee : Employee.getAllEmployees()){
            if (employee.getEmail() != null && employee.getEmail().isEmpty()) {
                Assert.fail("Попытка создания работника с пустым имэйлом");
            }
        }
    }

    @Test
    public void newEmployee_EMPTY_JOB_TITLE() {

        Employee employeeNotAdd1 = new Employee("","","");
        Employee employeeNotAdd2 = new Employee(null,null,null);

        for (com.courseproj.employeemanager.appEmployee.Employee employee : Employee.getAllEmployees()){
            if (employee.getJobTitle() != null && employee.getJobTitle().isEmpty()) {
                Assert.fail("Попытка создания работника с пустой рабочей должностью");
            }
        }
    }


    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }

}


/*
Аннотации JUnit :

@Test – определяет что метод method() является тестовым.
@Before – указывает на то, что метод будет выполнятся перед каждым тестируемым методом @Test.
@After – указывает на то что метод будет выполнятся после каждого тестируемого метода @Test
@BeforeClass – указывает на то, что метод будет выполнятся в начале всех тестов,
а точней в момент запуска тестов(перед всеми тестами @Test).
@AfterClass – указывает на то, что метод будет выполнятся после всех тестов.
@Ignore – говорит, что метод будет проигнорирован в момент проведения тестирования.
(expected = Exception.class) – указывает на то, что в данном тестовом методе
вы преднамеренно ожидаете Exception.
(timeout = 100) – указывает, что тестируемый метод не должен занимать больше чем 100 миллисекунд.
 */


/*
Основные методы класса Assert для проверки

fail(String) – указывает на то что бы тестовый метод завалился при этом выводя текстовое сообщение.
assertTrue([message], boolean condition) – проверяет, что логическое условие истинно.
assertsEquals([String message], expected, actual) – проверяет, что два значения совпадают.
Примечание: для массивов проверяются ссылки, а не содержание массивов.
assertNull([message], object) – проверяет, что объект является пустым null.
assertNotNull([message], object) – проверяет, что объект не является пустым null.
assertSame([String], expected, actual) – проверяет, что обе переменные относятся к одному объекту.
assertNotSame([String], expected, actual) – проверяет, что обе переменные относятся к разным объектам.
 */