package net.tofweb.service;

import java.util.Collection;

import net.tofweb.model.Widget;

public interface WidgetService {

	/**
	 * Persist the provided Widget. Doing so appends an id to the Widget.
	 * 
	 * @param widget
	 * @return the persisted Widget
	 */
	Widget save(Widget widget);

	/**
	 * Find a Widget by the specified id
	 * 
	 * @param widgetId
	 * @return Widget
	 */
	Widget findById(Integer widgetId);

	/**
	 * Get all the Widgets in the database
	 * 
	 * @return Collection<Widget>
	 */
	Collection<Widget> findAll();

	/**
	 * Delete the Widget indicated by the specified id
	 * 
	 * @param widget
	 */
	void delete(Integer widgetId);
}
