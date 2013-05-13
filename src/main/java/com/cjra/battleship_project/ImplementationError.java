package com.cjra.battleship_project;

class ImplementationError extends RuntimeException {

    private RuntimeException cause = null;

    public ImplementationError( RuntimeException cause ){
        this.cause = cause;
    }

    public RuntimeException getCause(){
        return this.cause;
    }
}
