package practice_lld.movieticketbookingsystem;

import practice_lld.movieticketbookingsystem.entities.*;
import practice_lld.movieticketbookingsystem.strategy.payment.PaymentStrategy;
import practice_lld.movieticketbookingsystem.strategy.pricing.PricingStrategy;
import practice_lld.movieticketbookingsystem.SeatLockManger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MovieBookingService {
    private static volatile MovieBookingService instance;

    private final SeatLockManger seatLockManager;
    private final BookingManager bookingManager;


    private final Map<String, Cinema> cinemas;
    private final Map<String, Movie> movies;
    private final Map<String, City> citiess;
    private final Map<String, Show> shows;
    private final Map<String, User> users;

    private MovieBookingService() {
        this.cinemas = new ConcurrentHashMap<>();
        this.movies = new ConcurrentHashMap<>();
        this.citiess = new ConcurrentHashMap<>();
        this.shows = new ConcurrentHashMap<>();
        this.users = new ConcurrentHashMap<>();

        this.seatLockManager = new SeatLockManger();
        this.bookingManager = new BookingManager(seatLockManager);
    }
    public static MovieBookingService getInstance(){
        if(instance == null){
            synchronized (MovieBookingService.class) {
                if(instance ==  null){
                   instance = new MovieBookingService();
                }
            }
        }
        return instance;
    }

    //Data management method
    public City addCity(String id, String name){
        City city = new City(id, name);
        citiess.put(id, city);
        return city;
    }

    public User createUser(String name, String email){
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Cinema addCinema(String id, String name, String cityId, List<Screen> screens){
        City city = citiess.get(cityId);
        Cinema cinema = new Cinema(id, name, city, screens);
        cinemas.put(id, cinema);
        return cinema;
    }

    public void addMovie(Movie movie){
        movies.put(movie.getId(),  movie);
    }
    public Show addShow(String id, Movie movie, Screen screen, LocalDateTime startTime, PricingStrategy pricingStrategy){
        Show show = new Show(id, movie, screen,startTime,pricingStrategy);
        shows.put(id, show);
        return show;
    }

    // search functionality
    public List<Show> findShows(String movieName, String city){
        List<Show> result = new ArrayList<>();

        shows.values().stream().filter(show -> show.getMovie().getTitle().equalsIgnoreCase(movieName))
                .filter(show -> {
                    Cinema cinema = findCinemaForShow(show);
                    return cinema!=null && cinema.getCity().getName().equalsIgnoreCase(city);
                })
                .forEach(show -> result.add(show));

        return result;
    }
    public Cinema findCinemaForShow(Show show){
        return cinemas.values().stream().filter(cinema -> cinema.getScreenList().contains(show.getScreen())).findFirst().orElse(null);
    }

    public void shutdown() {
        this.seatLockManager.shutdown();
        System.out.println("MovieTicketBookingSystem has been shut down.");
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public Optional<Booking> bookTickets(String userId, String showId, List<Seat> desiredSeats, PaymentStrategy paymentStrategy) {
        return bookingManager.createBooking(
                users.get(userId),
                desiredSeats,
                shows.get(showId),
                paymentStrategy
        );
    }

}
