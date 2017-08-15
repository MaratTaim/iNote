package com.epam.inote.dao;

import com.epam.inote.model.ContactType;
import com.epam.inote.model.User;
import com.epam.inote.model.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;

/**
 * Created by User on 01.08.2017.
 *
 */
public class ContactDAO extends AbstractDao<User> {
    private final Logger logger = Logger.getLogger("ContactDAO");
    private final String INSERT = "INSERT INTO contact (user_id, type_id, value) VALUES (?,?,?)";
    private final String SELECT = "SELECT t.type, value FROM contact c JOIN cont_type t ON c.type_id=t.id WHERE user_id=?";
    private final String DELETE = "DELETE FROM contact WHERE user_id=?";
    private Connection connection;

    public ContactDAO(Connection connection){
        this.connection = connection;
    }

    //  вставляет контакты Юзера в таблицу contact (к ContactType добвлено id)
    @Override
    public void insert(User user) {
        try (PreparedStatement st = connection.prepareStatement(INSERT)) {
            for (Map.Entry<ContactType, String> e : user.getContacts().entrySet()) {
                st.setInt(ONE, user.getId());
                st.setInt(TWO, e.getKey().getId());
                st.setString(THREE, e.getValue());
                st.addBatch();
            }
            st.executeBatch();
            commit(connection);
        }
        catch (SQLException e){
            logger.error(INSERT_ERR,e);
        }
    }

    //  если нужен добавить Контакты только для одного Юзера
    @Override
    public User select(User user){
        try(PreparedStatement ps = connection.prepareStatement(SELECT)){
            ps.setInt(ONE, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String value = rs.getString(VALUE);
                if (!Util.isEmpty(value)) {
                    ContactType type = ContactType.valueOf(rs.getString(TYPE));
                    user.setContact(type, value);
                }
            }
            commit(connection);
        } catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return user;
    }

    @Override
    public void update(User user){
        delete(user);
        insert(user);
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement st = connection.prepareStatement(DELETE)) {
            st.setInt(ONE, user.getId());
            st.execute();
            commit(connection);
        } catch (SQLException e) {
            logger.error(DELETE_ERR,e);
        }
    }

    //  принимает map с Юзерами и добавляет каждому его Контаты
    public Map<Integer,User> addContactToUser(Map<Integer,User> map) {
        for (Map.Entry<Integer, User> e : map.entrySet()) {
            select(e.getValue());
        }
        return map;
    }

}

