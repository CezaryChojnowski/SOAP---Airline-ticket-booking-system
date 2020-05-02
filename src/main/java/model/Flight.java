package model;


import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode
public class Flight {

    public int id;

    public Airport to_AirPort;

    public Airport from_AirPort;

    public Date data_lotu;

    public int price;

}
