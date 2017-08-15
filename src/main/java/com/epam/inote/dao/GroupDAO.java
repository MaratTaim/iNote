package com.epam.inote.dao;

import com.epam.inote.exception.INoteException;
import com.epam.inote.model.User;
import com.epam.inote.model.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Created by User on 01.08.2017.
 *
 */
public class GroupDAO extends AbstractDao<User> {
    private final Logger logger = Logger.getLogger("GroupDAO");
    private final String SELECT_ALL_GROUP = "SELECT gname FROM group_name";
    private final String SEARCH_GROUP = "SELECT u.id, surname, u.name, address, birthday, ct.type, c.value, gn.gname " +
            "FROM user u LEFT JOIN contact c ON u.id=c.user_id LEFT JOIN cont_type ct ON c.type_id=ct.id " +
            "LEFT JOIN `group` g ON g.user_id=u.id LEFT JOIN group_name gn ON g.name_id=gn.id WHERE gn.gname=?";
    private final String SELECT = "SELECT gname FROM `group` g JOIN group_name gn ON g.name_id=gn.id WHERE g.user_id=?";
    private final String SELECT_ID = "SELECT id FROM group_name WHERE gname=?";
    private final String DELETE = "DELETE FROM `group` WHERE user_id=?";
    private final String INSERT = "INSERT INTO group_name (gname) VALUES (?)";
    private final String INSERT_UID_NID = "INSERT INTO `group`(user_id, name_id) VALUES (?,?)";
    private Connection connection;

    public GroupDAO(Connection connection){
        this.connection = connection;
    }

    public void update(User user){
        delete(user);
        insert(user);
    }

    @Override
    public void delete(User user){
        try(PreparedStatement st = connection.prepareStatement(DELETE)){
            st.setInt(ONE,user.getId());
            st.execute();
            commit(connection);
        } catch (SQLException e) {
            logger.error(DELETE_ERR,e);
        }
    }

//  его исп как при update так и insert command
    //TODO разьбить этот метод на несколько
    @Override
    public void insert(User user){
        //   если группа есть, то берет список всех групп(можно делать проверку в коммандах)
        List<String> set = selectAll();
        Iterator<String> iterator = user.getGroup().iterator();
        while (iterator.hasNext() && user.getGroup().size()!=0){
            int i = 0;
            String gname = iterator.next();
            // потом проверяет, если есть такая группа, то возьмет id и вставит в i
            if(set.contains(gname)){
                try(PreparedStatement pst = connection.prepareStatement(SELECT_ID)){
                    pst.setString(ONE,gname);
                    ResultSet rs = pst.executeQuery();
                    rs.next();
                    i = rs.getInt(ONE);
                    commit(connection);
                } catch (SQLException e) {
                    logger.error(INSERT_ERR+"/"+SELECT_ERR,e);
                }
            // если нет, то вставит(group_name) и возьмет авто id и вставит в i
            }else {
                try (PreparedStatement st = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    st.setString(ONE, gname);
                    st.execute();
                    ResultSet rs = st.getGeneratedKeys();
                    rs.next();
                    i = rs.getInt(ONE);
                    commit(connection);
                } catch (SQLException e) {
                    logger.error(INSERT_ERR+"/"+INSERT_ERR,e);
                }
            }
            // потом в цикле вставит в таблицу-переходник group
            try(PreparedStatement st = connection.prepareStatement(INSERT_UID_NID)){
                st.setInt(ONE,user.getId());
                st.setInt(TWO,i);
                st.execute();
                commit(connection);
            } catch (SQLException e) {
                logger.error(INSERT_ERR+"/"+INSERT_ERR,e);
            }
        }
    }

    //  принимает Map и для каждого Юзера вставляет Группы
    public Map<Integer,User> addGroupToUser(Map<Integer,User> map){
        for (Map.Entry<Integer, User> e : map.entrySet()) {
            select(e.getValue());
        }
        return map;
    }

    //  всталяет для одного Юзера
    @Override
    public User select(User user) {
        try(PreparedStatement ps = connection.prepareStatement(SELECT)){
            ps.setInt(ONE, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String value = rs.getString(GNAME);
                if (!Util.isEmpty(value)) {
                    user.setGroup(value);
                }
            }
            commit(connection);
        } catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return user;
    }

    //  все группы для выпадающего тега select по которому будет поиск
    public List<String> selectAll(){
        List<String> list = new ArrayList<>();
        try(PreparedStatement st = connection.prepareStatement(SELECT_ALL_GROUP)){
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(rs.getString(GNAME));
            }
            commit(connection);
        }catch (SQLException e) {
            logger.error(SELECT_ERR,e);
        }
        return list;
    }

    //  поиск Юзеров по их Группам, возвращает rs для передачи его в UserDAO(return Map<Integer,User>)
    public ResultSet searchGroup(String gname){
        try {
            PreparedStatement st = connection.prepareStatement(SEARCH_GROUP);
            st.setString(ONE, gname);
            return st.executeQuery();

        } catch (SQLException e) {
            logger.error(GROUPS+" "+SELECT_ERR,e);
            throw new INoteException(GROUPS+" "+SELECT_ERR,e);
        }
    }
}