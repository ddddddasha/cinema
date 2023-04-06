package repository;

import model.Person;

import java.util.List;

public interface PersonRepository {
    /**
     * Создание и сохранение пользователя в базу данных.
     */
    boolean create(Person person);
    /**
     * Получение пользователя по ID.
     */
    Person getById(Long id);
    /**
     * Получение пользователя по логину.
     */
    Person getByUsername(String username);
    /**
     * Получение всех пользователей.
     */
    List<Person> getAllPersons();
    /**
     * Изменение пользователя по ID.
     */
    Person update(Person person, Long id);
    /**
     * Удаление пользователя по ID.
     */
    boolean deleteById(Long id);

}
