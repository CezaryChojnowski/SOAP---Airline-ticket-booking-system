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
@AllArgsConstructor
public class Ticket {

    public Flight flight;

    public PassengerDTO passenger;

    public Integer code;

}
