package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressDaoTest
{
    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    @Test
    public void testShouldFindAddressById() {
        // given
        // when
        AddressEntity addressEntity = addressDao.findOne(1L);
        // then
        assertThat(addressEntity).isNotNull();
        assertThat(addressEntity.getPostalCode()).isEqualTo("62-030");
    }

    @Test
    public void testShouldSaveAddress() {
        // given
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");
        long entitiesNumBefore = addressDao.count();

        // when
        final AddressEntity saved = addressDao.save(addressEntity);

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(addressDao.count()).isEqualTo(entitiesNumBefore+1);
    }

    @Transactional
    @Test
    public void testShouldSaveAndRemoveAddress() {
        // given
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");

        // when
        final AddressEntity saved = addressDao.save(addressEntity);
        assertThat(saved.getId()).isNotNull();
        final AddressEntity newSaved = addressDao.findOne(saved.getId());
        assertThat(newSaved).isNotNull();

        addressDao.delete(saved.getId());

        // then
        final AddressEntity removed = addressDao.findOne(saved.getId());
        assertThat(removed).isNull();
    }

    @Test
    public void testShoultThrowOptimisticLockException() {
        //given
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        EntityTransaction transaction1 = entityManager1.getTransaction();
        transaction1.begin();

        AddressEntity address = new AddressEntity();
        address.setCity("city");
        address.setAddressLine1("line1");
        address.setAddressLine2("line2");
        address.setPostalCode("12-345");
        entityManager1.persist(address);

        transaction1.commit();
        entityManager1.clear();

        EntityTransaction transaction2 = entityManager2.getTransaction();
        transaction2.begin();

        AddressEntity addressTransaction2 = entityManager2.find(AddressEntity.class, address.getId());

        transaction1.begin();
        AddressEntity addressTransaction1 = entityManager1.find(AddressEntity.class, address.getId());
        addressTransaction1.setCity("updatedCity");
        transaction1.commit();

        //when
        addressTransaction2.setCity("updatedCity2");

        //then
        try {
            transaction2.commit();
        } catch (RollbackException e) {
            assertThat(e.getCause()).isInstanceOf(OptimisticLockException.class);
        } finally {
            entityManager1.close();
            entityManager2.close();
        }
    }
}
