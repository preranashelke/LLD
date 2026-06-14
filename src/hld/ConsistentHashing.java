package hld;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

public class ConsistentHashing {
    private final int replicas;
    private final Set<String>servers;
    private final TreeMap<Long, String> ring;

    public ConsistentHashing(int replicas, List<String> serversList){
        this.replicas=replicas;
        this.ring= new TreeMap<>();
        this.servers= new HashSet<>();
        for(String server: serversList){
            addServer(server);
        }
    }

    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] digest = md.digest(key.getBytes());

            return Math.abs(new BigInteger(1, digest).longValue());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addServer(String server){
        servers.add(server);
        for(int i=0;i<replicas;i++){
            long hash = hash(server+"-"+i);
            ring.put(hash, server);
        }
    }

    public void removeServer(String server){
        servers.remove(server);
        for(int i=0;i<replicas;i++){
            long hash = hash(server+"-"+i);
            ring.remove(hash);
        }
    }

    public  String getServer(String key){
        if(ring.isEmpty()){
            return "No server is not available";
        }
        long hash = hash(key);

        Map.Entry<Long, String> entry = ring.ceilingEntry(hash);
        if(entry==null){
            entry=ring.firstEntry();
        }

        return  entry.getValue();
    }

    public static void main(String[] args){
        List<String> servers = Arrays.asList("S0","S2","S1");
        ConsistentHashing ch = new ConsistentHashing(10, servers);

        for (Map.Entry<Long, String> e : ch.ring.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        System.out.println("UserA hash = " + ch.hash("UserA"));
        System.out.println("UserB hash = " + ch.hash("UserB"));

        System.out.println("UserA is assign to " + ch.getServer("UserA"));
        System.out.println("UserB is assign to " + ch.getServer("UserB"));


        System.out.println("UserC is assign to " + ch.getServer("UserC"));
        System.out.println("UserD is assign to " + ch.getServer("UserD"));

        ch.addServer("S4");
        System.out.println("UserB is now assign to " + ch.getServer("UserB"));


    }



}
