package component;

/**
 * Created by mw on 09-08-2016.
 */
public class Component<T> {

    public static final int SIMPLE_FEED = 0;

    public static final int POLL_FEED = 1;

    T object;

    final int id;

    final int viewType;

    FeedItem feedItem;

    public Component(int id, int viewType, T object) {
        this.id = id;
        this.object = object;
        this.viewType = viewType;
    }

    public T getObject() {
        return object;
    }

    public void setFeedItem(FeedItem feedItem) {
        this.feedItem = feedItem;
    }

    public int getViewType() {
        return viewType;
    }

    public FeedItem getFeedItem() {
        return feedItem;
    }

    public int getId() {
        return id;
    }

}
