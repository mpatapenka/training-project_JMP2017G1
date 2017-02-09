package by.epam.jmp.app.tradesystem.dataprovider.inmemory;

abstract class AbstractIMProvider {

    private long counter = 0;

    long generateId() {
        return counter++;
    }

}