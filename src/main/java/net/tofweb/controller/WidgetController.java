package net.tofweb.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.tofweb.exception.ResourceNotFoundException;
import net.tofweb.model.Widget;
import net.tofweb.resource.WidgetResource;
import net.tofweb.service.WidgetService;

@RestController
@RequestMapping("/widget")
public class WidgetController {

	private static final Logger logger = LogManager.getLogger(WidgetController.class);

	@Resource
	private WidgetService widgetService;

	/**
	 * Get all the Widgets
	 * 
	 * @return Collection<Widget>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Resources<WidgetResource> get() {
		Collection<Widget> widgets = widgetService.findAll();

		// Stream the collection of widgets through two maps
		// The first map casts the object to a Widget - needed because steams
		// work on Objects
		// The second map builds a WidgetResource with the specified Widget
		// Finally the steam is turned back into a list for output
		List<WidgetResource> widgetResourceList = widgets.stream().map(Widget.class::cast).map(WidgetResource::new)
				.collect(Collectors.toList());

		return new Resources<WidgetResource>(widgetResourceList);
	}

	/**
	 * Get a Widget by its id.
	 * 
	 * If the Widget is not found, throw a 404
	 * 
	 * @param widgetId
	 * @return Widget
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{widgetId}")
	public WidgetResource getById(@PathVariable Integer widgetId) {
		Widget widget = widgetService.findById(widgetId);

		if (widget == null) {
			throw new ResourceNotFoundException();
		}

		return new WidgetResource(widget);
	}

	/**
	 * Add the specified Widget
	 * 
	 * @param widget
	 * @return The location of the created Widget
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Widget widget) {
		Widget result = widgetService.save(widget);
		Link widgetLink = new WidgetResource(result).getLink("self");

		return ResponseEntity.created(URI.create(widgetLink.getHref())).build();
	}

	/**
	 * Update an existing Widget with the specified values
	 * 
	 * @param widget
	 * @return Widget
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Widget update(@RequestBody Widget widget) {
		return widgetService.save(widget);
	}

	/**
	 * Delete the Widget specified by the id.
	 * 
	 * If that Widget does not exist to delete, then throw a 404
	 * 
	 * @param widgetId
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{widgetId}")
	public void delete(@PathVariable Integer widgetId) {
		try {
			widgetService.delete(widgetId);
		} catch (HibernateOptimisticLockingFailureException ex) {
			// Honestly, logging this is optional
			logger.info("Unable to delete Widget", ex);
			throw new ResourceNotFoundException(ex);
		}
	}
}
