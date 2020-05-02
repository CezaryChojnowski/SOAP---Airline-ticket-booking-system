package DTO;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode
public class CityDTO {
    public String name;
}
