package next.jwp.model;

public class Label {
    private int id;
    private String labelText;
    private String color;

    public Label() {
        super();
    }
    
    public Label(String labelText, String color) {
        super();
        this.labelText = labelText;
        this.color = color;
    }
    
    public Label(int id, String labelText, String color) {
        super();
        this.id = id;
        this.labelText = labelText;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + id;
        result = prime * result + ((labelText == null) ? 0 : labelText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Label other = (Label) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (id != other.id)
            return false;
        if (labelText == null) {
            if (other.labelText != null)
                return false;
        } else if (!labelText.equals(other.labelText))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Label [id=" + id + ", labelText=" + labelText + ", color=" + color + "]";
    }
    
}
