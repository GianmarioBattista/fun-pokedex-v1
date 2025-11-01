package com.pokedex.command;

public abstract class BaseCommand<Output> {

    public BaseCommand() {}

    protected boolean canExecute() {
        return true;
    }

    protected Output doExecute() {
        return null;
    }

    public Output execute() {
        if (canExecute()) {
            return doExecute();
        } else {
            throw new RuntimeException("Command input validation error");
        }
    }
}
