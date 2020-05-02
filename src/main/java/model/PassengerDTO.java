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
public class PassengerDTO {
    public String name;

    public String surname;

    public String email;

}
