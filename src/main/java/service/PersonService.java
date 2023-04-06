package service;

import model.Person;

import java.util.List;

public interface PersonService {
    /**
     * Сохранение пользователя в базу данных.
     */
    boolean create(String username, String password);

    /**
     * Получение пользователя по логину.
     */
    Person getByUsername(String username);

    /**
     * Получение пользователя по ID.
     */
    Person getById(Long id);

    /**
     * Получение всех пользователей.
     */
    List<Person> getAllPersons();

    /**
     * Обновление пользователя по ID.
     */
    boolean updatePersonById(Long id, Person person);

    /**
     * Удаление пользователя по ID из базы данных.
     */
    boolean deleteById(Long id);
    /**
     * Вход пользователя в систему.
     */
    Person logIn();
    /**
     * Вывод всех пользователей на экран
     */
    public void showAllPersons();
}
