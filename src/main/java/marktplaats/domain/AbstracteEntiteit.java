package marktplaats.domain;

import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class AbstracteEntiteit {
    @Id
    @GeneratedValue
    protected long id;
}
