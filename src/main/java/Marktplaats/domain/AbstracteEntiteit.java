package Marktplaats.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstracteEntiteit {
    @Id
    @GeneratedValue
    protected long id;
}
