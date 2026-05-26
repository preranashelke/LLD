package practice_lld.movieticketbookingsystem.observer;

import practice_lld.movieticketbookingsystem.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public abstract class MovieSubject {
    private final List<MovieObserver> observerList = new ArrayList<>();

    public void addObserver(MovieObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(MovieObserver observer){
        observerList.remove(observer);
    }

    public void notifyObservers(){
        for(MovieObserver o: observerList){
            o.update((Movie) this);
        }
    }
}
