package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.ParkPlace;
import com.bilgeadam.utility.MyFactoryRepository;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ParkPlaceRepository extends MyFactoryRepository<ParkPlace,Long> {


    public ParkPlaceRepository() {
        super(new ParkPlace());
    }

    public List<ParkPlace> findByIdAndIsParked(Long id) {
        CriteriaQuery<ParkPlace> criteria=getCriteriaBuilder().createQuery(ParkPlace.class);
        Root<ParkPlace> root=criteria.from(ParkPlace.class);
        criteria.select(root);
        Predicate isParked=getCriteriaBuilder().equal(root.get("isParked"),false);
        Predicate equlaId= getCriteriaBuilder().equal(root.get("parkArea").get("id"),id);
        criteria.where(getCriteriaBuilder().and(equlaId,isParked));
        return getEntityManager().createQuery(criteria).getResultList();


    }
}
