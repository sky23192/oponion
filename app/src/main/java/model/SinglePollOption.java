package model;

/**
 * Created by mw on 13-08-2016.
 */
public class SinglePollOption {

    public String getTotalPercentage() {
        return totalPercentage;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    String totalPercentage;
    String id;
    String label;

    public SinglePollOption(String id, String label, String totalPercentage) {
        this.id = id;
        this.totalPercentage = totalPercentage;
        this.label = label;
    }

}

