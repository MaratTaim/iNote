package com.epam.inote.command.type;

import com.epam.inote.command.CreateCommand;
import com.epam.inote.command.ViewCommand;
import com.epam.inote.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    BIRTHDAY{
        {
            this.command = new BirthdayCommand();
        }
    },
    GROUP{
        {
            this.command = new GroupCommand();
        }
    },
    SEARCH{
        {
            this.command = new SearchCommand();
        }
    },
    DELETE{
        {
            this.command = new DeleteCommand();
        }
    },
    CREATE{
        {
            this.command = new CreateCommand();
        }
    },
    VIEW{
        {
            this.command = new ViewCommand();
        }
    },
    EDIT{
        {
            this.command = new EditCommand();
        }
    },
    SHOW{
        {
            this.command = new ShowCommand();
        }
    },
    ADD{
        {
            this.command = new AddCommand();
        }
    },
    UPDATE{
        {
            this.command = new UpdateCommand();
        }
    },
    LANGUAGE{
        {
            this.command = new LanguageCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
