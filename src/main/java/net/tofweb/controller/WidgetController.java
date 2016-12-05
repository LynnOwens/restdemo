package net.tofweb.controller;

import java.net.URI;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.tofweb.exception.ResourceNotFoundException;
import net.tofweb.model.Widget;
import net.tofweb.service.WidgetService;

@RestController
@RequestMapping("/widget")
public class WidgetController {

	@Resource
	private WidgetService widgetService;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Widget> get() {
		return widgetService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{widgetId}")
	public Widget getById(@PathVariable Integer widgetId) {
		Widget widget = widgetService.findById(widgetId);

		if (widget == null) {
			throw new ResourceNotFoundException();
		}

		return widget;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Widget widget) {
		Widget result = widgetService.save(widget);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Widget update(@RequestBody Widget widget) {
		return widgetService.save(widget);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{widgetId}")
	public void delete(@PathVariable Integer widgetId) {
		widgetService.delete(widgetId);
	}
}
