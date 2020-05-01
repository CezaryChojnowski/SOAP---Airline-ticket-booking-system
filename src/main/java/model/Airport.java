package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class Airport {
    public int id;

    public String country;

    public String city;

    public String name;

    public String street;

    public int numer_of_building;

}
