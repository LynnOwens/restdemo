package net.tofweb.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import net.tofweb.controller.WidgetController;
import net.tofweb.model.Widget;

/**
 * @author Lynn Owens
 *
 *         A WidgetResource is a web representation of a Widget object. Included
 *         in a WidgetResource is the link by which you may get all Widgets and
 *         the link to this specified Widget.
 */
public class WidgetResource extends ResourceSupport {

	private final Widget widget;

	public WidgetResource(Widget widget) {
		this.widget = widget;
		this.add(new Link(widget.getId().toString(), "widget-uri"));
		this.add(linkTo(WidgetController.class).withRel("widgets"));
		this.add(linkTo(methodOn(WidgetController.class).getById(widget.getId())).withSelfRel());
	}

	public Widget getWidget() {
		return widget;
	}
}
