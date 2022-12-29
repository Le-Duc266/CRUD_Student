package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Student> getALl() {
        List<Student> result = new ArrayList<Student>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students");
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
                result.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Student> sortByAge() {
        List<Student> result = new ArrayList<Student>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students ORDER BY age DESC ");
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
                result.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void insert(Student student) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into students(code,name,phone,address,created_at,updated_at,age) values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, student.getCode());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setDate(5, new Date(student.getCreated_at().getTime()));
            preparedStatement.setDate(6, new Date(student.getUpdated_at().getTime()));
            preparedStatement.setInt(7, student.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Student student) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update students set code=?,name =?,phone=?,address=?,created_at=?,updated_at=?, age=? where id=?");
            preparedStatement.setString(1, student.getCode());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setDate(5, new Date(student.getCreated_at().getTime()));
            preparedStatement.setDate(6, new Date(student.getUpdated_at().getTime()));
            preparedStatement.setInt(7, student.getAge());
            preparedStatement.setInt(8, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from students where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Student> getByName(String name) {
        List<Student> result = new ArrayList<Student>();
        Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where  name like ?");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
                result.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Student> getByAddress(String address) {
        List<Student> result = new ArrayList<Student>();
        Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where  address like ?");
            preparedStatement.setString(1, "%" + address + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
                result.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
