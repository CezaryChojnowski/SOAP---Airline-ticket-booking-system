package model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode
public class Ticket {

    public int id;

    public Flight flight;

    public Passenger passenger;

}
