package service;

import model.Person;

import java.util.List;

public interface PersonService {
    /**
     * ���������� ������������ � ���� ������.
     */
    boolean create(String username, String password);

    /**
     * ��������� ������������ �� ������.
     */
    Person getByUsername(String username);

    /**
     * ��������� ������������ �� ID.
     */
    Person getById(Long id);

    /**
     * ��������� ���� �������������.
     */
    List<Person> getAllPersons();

    /**
     * ���������� ������������ �� ID.
     */
    boolean updatePersonById(Long id, Person person);

    /**
     * �������� ������������ �� ID �� ���� ������.
     */
    boolean deleteById(Long id);
    /**
     * ���� ������������ � �������.
     */
    Person logIn();
    /**
     * ����� ���� ������������� �� �����
     */
    public void showAllPersons();
}
