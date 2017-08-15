package com.epam.inote.constant;

import com.epam.inote.resource.ConfigurationManager;
import com.epam.inote.resource.MessageManager;

/**
 * Created by User on 01.08.2017.
 *
 */
public class Const {
    // Какой по счету стоит символ - ?
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    // column name
    public static final String ID = "id";
    public static final String SURNAME = "surname";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String BIRTHDAY = "birthday";
    public static final String GNAME = "gname";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    // others
    public static final String GROUPS = "groups";
    public static final String COMMA = ",";
    public static final String LIST = "list";
    public static final String USER = "user";
    public static final String LOCAL = "local";
    public static final String LANG = "lang";
    public static final String CLIENT = "client";
    public static final String ADMIN = "admin";
    public static final String SEARCH = "search";
    public static final String RU = "RU";
    public static final String RU_L = "ru";
    public static final String US = "US";
    public static final String EN = "en";
    public static final String CONT_TYPES = "contTypes";
    public static final String CHAR_ENCOD = "characterEncoding";
    public static final String ENCOD_CP = "Cp1251";
    // Commands
    public static final String ADD_COMMAND = "AddCommand";
    public static final String BIRTHDAY_COMMAND = "BirthdayCommand";
    public static final String CREATE_COMMAND = "CreateCommand";
    public static final String DELETE_COMMAND = "DeleteCommand";
    public static final String EDIT_COMMAND = "EditCommand";
    public static final String EMPTY_COMMAND = "EmptyCommand";
    public static final String GROUP_COMMAND = "GroupCommand";
    public static final String LANGUAGE_COMMAND = "LanguageCommand";
    public static final String LOGIN_COMMAND = "LoginCommand";
    public static final String LOGOUT_COMMAND = "LogoutCommand";
    public static final String SEARCH_COMMAND = "SearchCommand";
    public static final String SHOW_COMMAND = "ShowCommand";
    public static final String UPDATE_COMMAND = "UpdateCommand";
    public static final String VIEW_COMMAND = "ViewCommand";

    // Logging info
    public static final String EX_START = "execute start";
    public static final String EX_END = "execute end";
    public static final String EX_ERROR = "execute error ";
    public static final String ADDED = "was added";
    public static final String ERROR = "error";
    public static final String COMMIT = "commit";
    public static final String ROLLBACK = "rollback";
    public static final String INSERT_ERR = "insert error";
    public static final String SELECT_ERR = "select error";
    public static final String DELETE_ERR = "delete error";
    public static final String UPDATE_ERR = "update error";

    // ConnectionPool errors
    public static final String CAN_T_REGISTER = "Can't register JDBC driver";
    public static final String CAN_T_CREATE = "Can't create a new connection for ";
    public static final String CAN_T_CLOSE = "Can't close connection for pool";

    // Errors
    public static final String ERROR_LPM = "errorLoginPass";

    // Pages
    public static final String MAIN_PAGE = ConfigurationManager.getProperty("path.page.main");
    public static final String BIRTHDAY_PAGE = ConfigurationManager.getProperty("path.page.birthday");
    public static final String ADD_PAGE = ConfigurationManager.getProperty("path.page.add");
    public static final String UPDATE_PAGE = ConfigurationManager.getProperty("path.page.update");
    public static final String LOGIN_PAGE = ConfigurationManager.getProperty("path.page.login");
    public static final String GROUP_PAGE = ConfigurationManager.getProperty("path.page.group");
    public static final String REGISTER_PAGE = ConfigurationManager.getProperty("path.page.register");
    public static final String VIEW_PAGE = ConfigurationManager.getProperty("path.page.view");
    public static final String ERROR_PAGE = ConfigurationManager.getProperty("path.page.error");

}
