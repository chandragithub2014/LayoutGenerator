package layout.layoutgenerator.DTO;

/**
 * Created by CHANDRASAIMOHAN on 2/1/2016.
 */
public class WidgetPropertiesDTO {

    String widgetName;
    String width;
    String height;
    String padding;
    String margin;
    String gravity;
    String widgetId;
    String widgetLabel;
    String widgetDrawable; //for ImageView,ImageButton
    String widgetWeight;
    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetLabel() {
        return widgetLabel;
    }

    public void setWidgetLabel(String widgetLabel) {
        this.widgetLabel = widgetLabel;
    }

    public String getWidgetDrawable() {
        return widgetDrawable;
    }

    public void setWidgetDrawable(String widgetDrawable) {
        this.widgetDrawable = widgetDrawable;
    }

    public String getWidgetWeight() {
        return widgetWeight;
    }

    public void setWidgetWeight(String widgetWeight) {
        this.widgetWeight = widgetWeight;
    }
}
