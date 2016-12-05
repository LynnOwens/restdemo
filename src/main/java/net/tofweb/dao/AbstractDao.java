package net.tofweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public abstract class AbstractDao {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Returns the table name for a given entity type in the
	 * {@link EntityManager}.
	 * 
	 * @param em
	 * @param entityClass
	 * @return
	 */
	public <T> String getTableName(Class<T> entityClass) {
		/*
		 * Check if the specified class is present in the metamodel. Throws
		 * IllegalArgumentException if not.
		 */
		Metamodel meta = entityManager.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);

		// Check whether @Table annotation is present on the class.
		Table t = entityClass.getAnnotation(Table.class);

		String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();

		return tableName;
	}
}
