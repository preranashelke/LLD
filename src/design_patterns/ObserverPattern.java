package design_patterns;

import java.util.ArrayList;
import java.util.List;

interface Subject{
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String message);

}

class NewsAgency implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void setNews(String news) {
        this.news = news;
        notifyObservers(news);
    }

    @Override
    public void addObserver(Observer o){
        observers.add(o);

    }
    @Override
    public void removeObserver(Observer o){
        observers.remove(o);
    }
    @Override
    public void notifyObservers(String message){
        for (Observer o: observers){
            o.update(message);
        }

    }
}

interface Observer {
    void update(String news);
}

class NewsChannel implements Observer{
    private String channelName;

    public NewsChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void update(String news) {
        System.out.println(channelName + " received news: "+news);
    }
}

public class ObserverPattern {
    public static void main(String[] args){
        NewsAgency agency = new NewsAgency();

        NewsChannel channel1 = new NewsChannel("Channel alpha");
        NewsChannel channel2 = new NewsChannel("Channel beta");
        NewsChannel channel3 = new NewsChannel("Channel gamma");

        agency.addObserver(channel1);
        agency.addObserver(channel2);

        agency.setNews("new company launched !!!!");

        agency.removeObserver(channel1);
        agency.addObserver(channel3);
        agency.setNews("Market trends indicate growth.");


    }

}
