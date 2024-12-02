 package es.upm.dit.apsv.transportationorderserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransportationOrder {
   
    @Id
    private String toid;
    private String truck;
    private Long originDate;
    private Double originLat;
    private Double originLong;
    private Long dstDate;
    private Double dstLat;
    private Double dstLong;
    private Long lastDate;
    private Double lastLat;
    private Double lastLong;
    private int st;

    // Constructor con todos los campos
    public TransportationOrder(String toid, String truck, Long originDate, Double originLat, Double originLong,
                               Long dstDate, Double dstLat, Double dstLong, Long lastDate, Double lastLat,
                               Double lastLong, int st) {
        this.toid = toid;
        this.truck = truck;
        this.originDate = originDate;
        this.originLat = originLat;
        this.originLong = originLong;
        this.dstDate = dstDate;
        this.dstLat = dstLat;
        this.dstLong = dstLong;
        this.lastDate = lastDate;
        this.lastLat = lastLat;
        this.lastLong = lastLong;
        this.st = st;
    }

    // Método para calcular la distancia hasta el destino
    public double distanceToDestination() {
        return Math.sqrt(Math.pow(this.dstLat - this.lastLat, 2) + Math.pow(this.dstLong - this.lastLong, 2));
    }
}
