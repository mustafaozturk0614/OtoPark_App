package com.bilgeadam.utility;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

;

public class MyFactoryRepository<T, ID> implements ICrud<T, ID> {

	Session session;
	EntityManager entityManager;
	CriteriaBuilder criteriaBuilder;
	org.hibernate.Transaction transaction;
	private T t;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

	public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
		this.criteriaBuilder = criteriaBuilder;
	}

	public org.hibernate.Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(org.hibernate.Transaction transaction) {
		this.transaction = transaction;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public MyFactoryRepository(T t) {
		entityManager = HibernateUtils.getSessionFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();
		this.t = t;
	}

	public void openSession() {
		if (!entityManager.isOpen()) {
			session=HibernateUtils.getSessionFactory().openSession();

		}else {
			session = entityManager.unwrap(Session.class);
		}

		transaction = session.beginTransaction();

	}

	public void closeSuccessSession() {

		transaction.commit();
		session.close();

	}

	public void closeErrorSession() {
		transaction.rollback();
		session.close();

	}



	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		try {

			openSession();
			session.save(entity);

			closeSuccessSession();
			System.out.println(entity.getClass() + "Kay�t i�lemi Ba�ar�l�");
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			closeErrorSession();
			System.out.println(entity.getClass() + "Kay�t i�lemi Ba�ar�s�z");
		}
		return null;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		try {
			openSession();
			entities.forEach(entity -> {

				session.save(entity);

			});
			closeSuccessSession();

			return entities;
		} catch (Exception e) {
			e.printStackTrace();
			closeErrorSession();
		}
		return null;
	}

	@Override
	public void deleteById(ID id) {
		Optional<T> deleteEntitiy = null;

		try {

			deleteEntitiy = findById(id);

			if (deleteEntitiy.isPresent()) {
				System.out.println("1" + deleteEntitiy);
				openSession();
				session.delete(deleteEntitiy.get());
				System.out.println("2" + deleteEntitiy);
				closeSuccessSession();
			} else {

				System.out.println("id li veri bulunamad�");
			}

		} catch (Exception e) {
			closeErrorSession();
		}

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<T> findById(ID id) {
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteria.from(t.getClass());
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get("id"), id));
		T result = null;
		try {
			result = entityManager.createQuery(criteria).getSingleResult();

			closeSuccessSession();
		} catch (Exception e) {

		}
		return Optional.ofNullable(result);

	}

	@Override
	public boolean existById(ID id) {

		try {
			CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
			Root<T> root = (Root<T>) criteria.from(t.getClass());
			criteria.select(root);
			criteria.where(criteriaBuilder.equal(root.get("id"), id));
			Optional<T> result = (Optional<T>) entityManager.createQuery(criteria).getSingleResult();

			return result.isPresent();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<T> findAll() {
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteria.from(t.getClass());
		criteria.select(root);
		return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public List<T> finByCollumnAndValue(String collumn, Object value) {
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteria.from(t.getClass());
		criteria.select(root);
		criteria.where(criteriaBuilder.equal(root.get(collumn), value));
		return entityManager.createQuery(criteria).getResultList();

	}

	@Override
	public List<T> findByEntity(T entity) {
		List<T> result = new ArrayList<T>();
		Class cl = entity.getClass();

		Field[] fl = cl.getDeclaredFields();

		try {
			CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(entity.getClass());
			Root<T> root = (Root<T>) criteria.from(entity.getClass());
			List<Predicate> list = new ArrayList<Predicate>();
			for (int i = 0; i < fl.length; i++) {
				// reflaction ile eri�ti�imiz alanlar eri�ebilir olamal�

				fl[i].setAccessible(true);
				if (fl[i].get(entity) != null && !fl[i].getName().equals("id")) {
					if (fl[i].getType().isAssignableFrom(String.class)) {// String kontrolu

						list.add(criteriaBuilder.like(root.get(fl[i].getName()), "%" + fl[i].get(entity) + "%"));

					} else {
						list.add(criteriaBuilder.equal(root.get(fl[i].getName()), fl[i].get(entity)));
					}

				}
			}
			criteria.where(list.toArray(new Predicate[] {}));

			result = entityManager.createQuery(criteria).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
