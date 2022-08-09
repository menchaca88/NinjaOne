package rmm.ninjaone.buildingblocks.domain.contracts;

import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

public interface CustomizedActions<T extends AggregateRoot> {
    <S extends T> S save(S entity);
    <S extends T> S saveAndFlush(S entity);
    void delete(T entity);
}