package com.haisun.simple.designpatterns.command;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        Content c = new Content();
        CommandChain commandChain = new CommandChain();
        commandChain.add(new IstCommand(c)).add(new CpyCommand(c));


        System.out.println("======>" + c.msg);
    }
}

interface ICommand {
    void doCommand(Command command, CommandChain chain);
}

class IstCommand implements ICommand {
    Content c;
    String strToInsert = "http://www.mashibing.com";

    public IstCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doCommand(Command command, CommandChain chain) {
        c.msg = c.msg + strToInsert;
        chain.doCommand(command);
        c.msg = c.msg.substring(0, c.msg.length()-strToInsert.length());
    }
}

class CpyCommand implements ICommand {
    Content c;
    public CpyCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doCommand(Command command, CommandChain chain) {
        c.msg = c.msg + c.msg;
        chain.doCommand(command);
        c.msg = c.msg.substring(0, c.msg.length()/2);
    }
}

class CommandChain {
    List<ICommand> filters = new ArrayList<>();
    int index = 0;

    public CommandChain add(ICommand f) {
        filters.add(f);
        return this;
    }

    public void doCommand(Command command) {
        if(index == filters.size()) return;
        ICommand f = filters.get(index);
        index ++;

        f.doCommand(command, this);
    }
}


