package agg.deepa.productservices.inheritanceexample.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructor")
@DiscriminatorValue(value = "instructor")
public class Instructor extends User{
    private boolean isHandsome;
}
