package practice_lld.movieticketbookingsystem.entities;

import java.util.List;

public class Cinema {
    private final String id;
    private final String name;
    private final City city;
    private final List<Screen> screenList;

    public Cinema(String id, String name, City city, List<Screen> screenList) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screenList = screenList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }
}
