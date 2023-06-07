// package ru.geekbrains.lesson3.task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Program {

    static Random random = new Random();

    /**
     * TODO: Переработать метод generateEmployee в рамках домашнего задания,
     *  метод должен генерировать рабочих (Employee) разных типов.
     *
     * @return
     */
    static Employee generateEmployee() {
        String[] names = new String[]{"Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман"};
        String[] surnames = new String[]{"Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов"};
        String[] roles = new String[]{"Фотограф", "Программист", "Иллюстратор", "Редактор"};
        String randomName = names[random.nextInt(2)];
        String randomSurname = surnames[random.nextInt(surnames.length)];
        int randomAge = random.nextInt(20, 65);
        String randomRole = roles[random.nextInt(roles.length)];
        if (random.nextInt(2) == 0) {
            int salary = random.nextInt(20000, 80000);
            return new Worker(randomSurname, randomName, salary, randomAge, randomRole);
        } else {
            int salary = random.nextInt(500, 1000);
            int workedHours = random.nextInt(20 * 5, 20 * 8);
            return new Freelancer(randomSurname, randomName, salary, randomAge, randomRole, workedHours);
        }
    }


    /**
     * TODO: Придумать новые состояния для наших сотрудников
     *   Придумать несколько Comparator'ов для сортировки сотрудников
     *   по фамилии + имени или по возрасту + уровню зп.
     *
     * @param args
     */
    public static void main(String[] args) {

        Employee[] employees = generateEmployeesArray(10);
        boolean check = true;
        while (check){
            System.out.println("Выберете, что вы хотите сделать:");
            System.out.println("1 - Вывести сотрудников с сортировкой по зарплате");
            System.out.println("2 - Вывести сотрудников с сортировкой по фамилии и имени");
            System.out.println("3 - Вывести сотрудников с сортировкой по возрасту");
            System.out.println("4 - Вывести сотрудников с сортировкой по должности");
            System.out.println("0 - Выход");
            Scanner scanner = new Scanner(System.in);
            int customerChoiceInt = scanner.nextInt();
            switch (customerChoiceInt) {
                case 1 -> showEmployeesBySalary(employees);
                case 2 -> showEmployeesBySurnameAndName(employees);
                case 3 -> showEmployeesByAge(employees);
                case 4 -> showEmployeesByRole(employees);
                case 0 -> check = false;
                default -> System.out.println("Введите корректную цифру");
            }
        System.out.println("-----------");
        }
    }

    private static Employee[] generateEmployeesArray(int size) {
        Employee[] employees = new Employee[size];
        for (int i = 0; i < size; i++) {
            employees[i] = generateEmployee();
        }
        return employees;
    }

    private static void showEmployeesBySalary(Employee[] employees) {
        Arrays.sort(employees, new SalaryComparator());
//        2ой способ
//        Arrays.sort(employees, Comparator.comparingDouble(Employee::getSalary));

        printEmployees(employees);
    }

    private static void showEmployeesBySurnameAndName(Employee[] employees) {
        Arrays.sort(employees);
        printEmployees(employees);
    }

    private static void showEmployeesByAge(Employee[] employees) {
        Arrays.sort(employees, new AgeComparator());
        printEmployees(employees);
    }
    private static void showEmployeesByRole(Employee[] employees) {
        Arrays.sort(employees, Comparator.comparing(o -> o.role));
        printEmployees(employees);
    }

    private static void printEmployees(Employee[] employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }


}