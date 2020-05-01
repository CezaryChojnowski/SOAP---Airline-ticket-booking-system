package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Flight {

    public int id;

    public Airport to_AirPort;

    public Airport from_AirPort;

    public Date data_lotu;

    public int price;

}
