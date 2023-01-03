package utils.helpers.elementHelper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dev.morphia.annotations.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementResponse {

    @Id
    public String id;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("value")
    @Expose
    public String value;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("parent")
    @Expose
    public String parent;
    @SerializedName("projectId")
    @Expose
    public String projectId;

    public ElementResponse() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}