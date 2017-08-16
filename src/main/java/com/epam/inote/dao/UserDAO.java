package com.epam.inote.dao;

import com.epam.inote.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by User on 01.08.2017.
 * Работет только с User
 */
public class UserDAO extends AbstractDao<String> {
    private final Logger logger = Logger.getLogger("UserDAO");
    private final String SELECT_ALL = "SELECT * FROM user ORDER BY surname";
    private final String SELECT = "SELECT * FROM user where id=?";
    private final String SEARCH = "SELECT * FROM user where surname LIKE ? OR name LIKE ? OR address LIKE ?";
    private final String UPDATE = "UPDATE user SET surname=?, name=?, address=?, birthday=? WHERE id=?";
    private final String DELETE = "DELETE FROM user WHERE id=?";
    private final String INSERT = "INSERT INTO user (surname, name, address, birthday) VALUES(?,?,?,?)";
    private final String SELECT_BD = "SELECT * FROM user  WHERE MONTH(birthday)>=? AND DAY(birthday)>? ORDER BY MONTH(birthday)";
    private final String SELECT_NOT_BD = "SELECT * FROM user  WHERE NOT MONTH(birthday)>=? AND DAY(birthday)>? ORDER BY MONTH(birthday)";

    private Connection connection;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    //  вставляет Юзера и сразу присваевае ему авто_сгенерированное id
    @Override
    public void insert(User user) {
        try (PreparedStatement st = connection.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(ONE, user.getSurname());
            st.setString(TWO, user.getName());
            st.setString(THREE, user.getAddress());
            st.setDate(FOUR, Date.valueOf(user.getBirthday()));
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(ONE));

            commit(connection);
        } catch (SQLException e) {
            logger.error(INSERT_ERR, e);
        }
    }

    //   загузить одного Юзера для Edit, View
    @Override
    public User select(String id){
        User user = User.EMPTY;
        try (PreparedStatement st = connection.prepareStatement(SELECT)) {
            st.setString(ONE, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                logger.warn(USER+" "+id+" "+SELECT_ERR);
            }
            user = new User(rs.getInt(ID),
                    rs.getString(SURNAME),
                    rs.getString(NAME),
                    rs.getString(ADDRESS),
                    rs.getDate(BIRTHDAY).toLocalDate());

            commit(connection);
        } catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(ONE, user.getSurname());
            st.setString(TWO, user.getName());
            st.setString(THREE, user.getAddress());
            st.setDate(FOUR, Date.valueOf(user.getBirthday()));
            st.setInt(FIVE, user.getId());
            if (st.executeUpdate() == 0) {
                logger.warn(USER+" "+user.getSurname()+" "+user.getName()+" "+UPDATE_ERR);
            }
            commit(connection);
        } catch (SQLException e) {
            logger.error(UPDATE_ERR,e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setString(ONE, id);
            if (ps.executeUpdate() == 0) {
                logger.warn(USER+" "+id+" "+DELETE_ERR);
            }
            commit(connection);
        } catch (SQLException e) {
            logger.error(DELETE_ERR,e);
        }
    }

    //    загрузка Юзеров для главной страницы
    public Map<Integer, User> getAllSorted() {
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            return findAll(st.executeQuery());
        } catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return Collections.emptyMap();
    }

    //  загрузка Юзеров по запросу %...%
    public Map<Integer, User> getSearch(String search) {
        String find = "%"+search+"%";
        try (PreparedStatement st = connection.prepareStatement(SEARCH)) {
            st.setString(ONE, find);
            st.setString(TWO, find);
            st.setString(THREE, find);
            return findAll(st.executeQuery());

        } catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return Collections.emptyMap();
    }

    public Collection<User> getBirthday(int day, int month){
        List<User> users = new ArrayList<>();
        users.addAll(getPart(month,day,SELECT_BD));
        users.addAll(getPart(month,day,SELECT_NOT_BD));
        return users;
    }

    private Collection<User> getPart(int month, int day, String sql) {
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(ONE, month);
            st.setInt(TWO, day);
            return findAll(st.executeQuery()).values();

        } catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return Collections.emptyList();
    }

    //  исп: getAllSorted, getSearch, getPart
    public Map<Integer, User> findAll(ResultSet rs){
        Map<Integer, User> map = new LinkedHashMap<>();
        try {
            while (rs.next()) {
                Integer id = rs.getInt(ID);
                User user = map.get(id);
                if (user == null) {
                    user = new User(id, rs.getString(SURNAME),
                            rs.getString(NAME), rs.getString(ADDRESS),
                            rs.getDate(BIRTHDAY).toLocalDate());
                    map.put(id, user);
                }
            }
            commit(connection);
        }catch (SQLException e){
            logger.error(SELECT_ERR,e);
        }
        return map;
    }
}
