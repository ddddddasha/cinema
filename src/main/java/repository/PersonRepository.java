package repository;

import model.Person;

import java.util.List;

public interface PersonRepository {
    /**
     * �������� � ���������� ������������ � ���� ������.
     */
    boolean create(Person person);
    /**
     * ��������� ������������ �� ID.
     */
    Person getById(Long id);
    /**
     * ��������� ������������ �� ������.
     */
    Person getByUsername(String username);
    /**
     * ��������� ���� �������������.
     */
    List<Person> getAllPersons();
    /**
     * ��������� ������������ �� ID.
     */
    Person update(Person person, Long id);
    /**
     * �������� ������������ �� ID.
     */
    boolean deleteById(Long id);

}
