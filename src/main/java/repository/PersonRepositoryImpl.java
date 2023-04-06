package repository;

import model.Person;
import util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{

    @Override
    public boolean create(Person person) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO person (username, password, role) VALUES (?,?,?)");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());
            statement.setString(3, person.getRole());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Person getById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT username, password, role FROM person WHERE id=?");//�������� - ?
            statement.setLong(1, id);//�������� ��� �������
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");///�������� ��� �������� ���
                String password = resultSet.getString("password");///�������� ��� �������� ���
                String role = resultSet.getString("role");
                return new Person(id, username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;//���������� �� ���� ����� �������������
    }

    @Override
    public Person getByUsername(String username) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT id, password, role FROM person WHERE username=?");//�������� - ?
            statement.setString(1, username);//�������� ��� �������
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String password = resultSet.getString("password");///�������� ��� �������� ���
                String role = resultSet.getString("role");
                return new Person(id, username, password, role);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;//���������� �� ���� ����� �������������
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person"); //���������� � ��
            ResultSet resultSet = statement.executeQuery();//��������� ������ ���� ResultSet �� ��
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                Person person = new Person();//���� ����� �����������
                person.setId(id);
                person.setUsername(username);
                person.setPassword(password);
                person.setRole(role);
                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            //�����������
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person update(Person person, Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE person SET username=?, password=?, role=? WHERE id=?");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());
            statement.setString(3, person.getRole());
            statement.setLong(4, id);
            statement.executeUpdate();
            return person;//���������� � ����� ������ false ���������
        } catch (SQLException e) {
            //�����������
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE id=?");
            statement.setLong(1, id);
            return statement.execute();//���������� � ����� ������ false ���������
        } catch (SQLException e) {
           //�����������
            throw new RuntimeException();
        }
    }
}
