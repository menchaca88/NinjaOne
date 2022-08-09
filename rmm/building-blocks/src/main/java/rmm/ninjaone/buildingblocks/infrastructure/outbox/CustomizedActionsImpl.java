package rmm.ninjaone.buildingblocks.infrastructure.outbox;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;

import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.contracts.CustomizedActions;

public class CustomizedActionsImpl<T extends AggregateRoot> implements CustomizedActions<T> {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public <S extends T> S save(S entity) {
        saveEventsToOutbox(entity);
        
        @SuppressWarnings("unchecked")
        var entityType = (Class<S>)entity.getClass();

        var entityInformation = JpaEntityInformationSupport
            .getEntityInformation(entityType, entityManager);

        if (entityInformation.isNew(entity))
            entityManager.persist(entity);
        else
            entityManager.merge(entity);

        return entity;
    }

    @Transactional
    public <S extends T> S saveAndFlush(S entity) {
        var result = save(entity);
        
        entityManager.flush();
        return result;
    }

    @Transactional
    public void delete(T entity) {
        entity.setDeleted();

        saveEventsToOutbox(entity);
        entityManager.remove(entity);
    }

    private void saveEventsToOutbox(T entity) {
        //var events = entity.dispatchEvents();

        //TODO: save to outbox table.
    }
}
