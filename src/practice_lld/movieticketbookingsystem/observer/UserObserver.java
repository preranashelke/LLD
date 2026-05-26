package practice_lld.movieticketbookingsystem.observer;

import practice_lld.movieticketbookingsystem.entities.Movie;
import practice_lld.movieticketbookingsystem.entities.User;

public class UserObserver implements MovieObserver{

    private final User user;

    public UserObserver(User user){
        this.user = user;
    }

    @Override
    public void update(Movie movie) {
        System.out.printf("Notification for %s (%s): Movie '%s' is available for booking!%n", user.getName(), user.getId(), movie.getTitle());
    }
}
